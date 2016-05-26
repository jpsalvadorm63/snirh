-- --------------------------------------------------- --
-- Este script se ejecuta como último paso para la     --
-- conformación de la base de datos y antes de entrar  --
-- en producción                                       --
-- --------------------------------------------------- --

update pohm set demrdemr = null ;
update pohm set sistsist = null ;
update pohm set sistcuen = null ;
update pohm set sistsubc = null ;
update pohm set divppais = null ;
update pohm set divpprov = null ;
update pohm set divpcant = null ;
update pohm set divpparr = null ;

select dropGeometryColumn('public','pohm','geom');
select addGeometryColumn('public','pohm','geom',32717,'POINT',2);
UPDATE pohm SET geom = ST_Transform(ST_GeomFromText('POINT(' || pohm___x || ' ' || pohm___y || ')',32717), 32717);

-- ---------------------- --
-- CARGA DE DEMARCACIONES  --
-- ---------------------- --
delete from demr ;
insert into demr (demr__id, demrcdgo, demrnmbr, geom, "version")
( select
    min(gid),
    codigo_dh,
    nam_dh,
    st_union(geom),
    1
  from
    lh_demarcaciones_hidrograficas_fn
  group by
    codigo_dh,
    nam_dh
) ;

delete from sist ;
select dropGeometryColumn('public','sist','geom');
select addGeometryColumn('public','sist','geom',32717,'MULTIPOLYGON',2);
-- --------------------
-- CARGA DE SISTEMAS --
-- --------------------

insert into sist (sist__id, sistcdgo, sistnmbr, sistnivl, sist_dad, geom, "version")
( select
    min(gid)        as sist__id,
    cod_sist        as sistcdgo,
    sistema         as sistnmbr,
    'SISTEMA'       as sistnivl,
    0               as sist_dad,
    ST_Multi(ST_Union(geom))  as geom,
    1
  from
    lh_subcuencas_proj
  group by
    cod_sist,
    sistema ) ;

-- -------------------
-- CARGA DE CUENCAS --
-- -------------------

DROP FUNCTION cargaCuencas() ;

CREATE FUNCTION cargaCuencas() RETURNS text AS '
DECLARE
  msistema lh_subcuencas_proj.sistema%TYPE;
  mcodcuenca lh_subcuencas_proj.cod_cuenc%TYPE;
  mcuenca lh_subcuencas_proj.cuenca%TYPE;
  n int ;
  rowdata RECORD ;
  texto TEXT := '''' ;
  i int ;
BEGIN
  select into i max(sist__id)+100 from sist ;
  FOR rowdata IN
    SELECT
      sistema,cod_cuenc,cuenca,st_union(geom) as geom1
    FROM
      lh_subcuencas_proj
    GROUP BY
      sistema,cod_cuenc,cuenca
    ORDER BY
      sistema,cod_cuenc,cuenca
  LOOP

    texto := texto ||  rowdata.sistema || '' - '' || rowdata.cuenca || '', '' || i || '' ;'' ;

    insert into sist (sist__id, sistcdgo, sistnmbr, sistnivl, sist_dad, geom, "version")
      values ( i,
               rowdata.cod_cuenc,rowdata.cuenca,
               ''CUENCA'',
               (select sist__id from sist where sistnivl = ''SISTEMA'' and sistnmbr = rowdata.sistema),
               ST_Multi(rowdata.geom1),
               1) ;
    i := i + 1 ;
  END LOOP ;

  RETURN texto ;
END ;
' LANGUAGE 'plpgsql' ;

SELECT cargaCuencas() ;

-- ----------------------
-- CARGA DE SUBCUENCAS --
-- ----------------------

DROP FUNCTION cargaSubCuencas() ;

CREATE FUNCTION cargaSubCuencas() RETURNS text AS '
DECLARE
  msistema lh_subcuencas_proj.sistema%TYPE;
  mcodcuenca lh_subcuencas_proj.cod_cuenc%TYPE;
  mcuenca lh_subcuencas_proj.cuenca%TYPE;
  mcodsubcuenca lh_subcuencas_proj.codigo_sub%TYPE;
  msubcuenca lh_subcuencas_proj.subcuencas%TYPE;
  n int ;
  rowdata RECORD ;
  texto TEXT := '''' ;
  i int ;
BEGIN
  select into i max(sist__id)+100 from sist ;
  FOR rowdata IN
    SELECT
      sistema,cod_cuenc,cuenca,codigo_sub,subcuencas,st_union(geom) as geom1
    FROM
      lh_subcuencas_proj
    GROUP BY
      sistema,cod_cuenc,cuenca,codigo_sub,subcuencas
    ORDER BY
      sistema,cod_cuenc,cuenca,codigo_sub,subcuencas
  LOOP

    texto := texto ||  rowdata.sistema || '' - '' || rowdata.cuenca || '', '' || i || '' ;'' ;

    insert into sist (sist__id, sistcdgo, sistnmbr, sistnivl, sist_dad, geom, "version")
      values ( i,
               rowdata.codigo_sub,
               rowdata.subcuencas,
               ''SUBCUENCA'',
               (select sist__id from sist where sistnivl = ''CUENCA'' and sistcdgo = rowdata.cod_cuenc),
               ST_Multi(rowdata.geom1),
               1) ;
    i := i + 1 ;
  END LOOP ;

  RETURN texto ;
END ;
' LANGUAGE 'plpgsql' ;

SELECT cargaSubCuencas() ;

delete from divp ;

-- ----------- --
-- CARGAR PAIS --
-- ----------- --
insert into divp (divp__id, divpcdgo, divpnmbr, divpnivl, version, divp_dad, geom)
  ( select
      1,
      'EC',
      'ECUADOR',
      'PAIS',
      1,
      0,
      st_union(geom) geom
    from
      nxprovincias
  ) ;
insert into divp (divp__id, divpcdgo, divpnmbr, divpnivl, version, divp_dad, geom)
  ( select
      2,
      'CO',
      'ECUADOR CONTINENTAL',
      'PAIS',
      1,
      0,
      st_union(geom) geom
    from
      nxprovincias
    where
      dpa_provin <> '20'
  ) ;

-- ----------------- --
-- CARGAR PROVINCIAS --
-- ----------------- --
  insert into divp (divp__id, divpcdgo, divpnmbr, divpnivl, version, divp_dad, geom)
  (
    select
      max(gid)+1000,
      dpa_provin,
      dpa_despro,
      'PROVINCIA',
      1,
      1,
      st_union(geom) geom
    from
      nxprovincias
    group by
      dpa_provin,
      dpa_despro
  ) ;


-- --------------- --
-- CARGAR CANTONES --
-- --------------- --
  insert into divp (divp__id, divpcdgo, divpnmbr, divpnivl, version, divp_dad, geom)
  (
    select
      c.gid + 20000,
      c.dpa_canton,
      c.dpa_descan,
      'CANTON',
      1,
      p.divp__id,
      st_union(c.geom) geom
    from
      nxcantones c,
      divp p
    where
      c.dpa_provin = p.divpcdgo and p.divpnivl = 'PROVINCIA'
    group by
      c.gid,
      c.dpa_canton,
      c.dpa_descan,
      p.divp__id
    order by
      c.dpa_canton,
      c.dpa_descan
  ) ;


-- ----------------- --
-- CARGAR PARROQUIAS --
-- ----------------- --
insert into divp (divp__id, divpcdgo, divpnmbr, divpnivl, version, divp_dad, geom)
  (
    select
    max(p.gid) + 3000,
      p.dpa_parroq,
      p.dpa_despar,
      'PARROQUIA',
      1,
      c.divp__id,
      st_union(p.geom) geom
    from
      nxparroquias p,
      divp c
    where
      p.dpa_canton = c.divpcdgo and c.divpnivl = 'CANTON'
    group by
      p.dpa_parroq,
      p.dpa_despar,
      c.divp__id
    order by
      c.divp__id,
      p.dpa_parroq,
      p.dpa_despar
  );


-- ----------------- --
-- CARGAR PFABSTETER --
-- ----------------- --
update pohm set pfabniv1 = null;
update pohm set pfabniv2 = null;
update pohm set pfabniv3 = null;
update pohm set pfabniv4 = null;
update pohm set pfabniv5 = null;

delete from pfab where pfabnivl = 'NIVEL5';
delete from pfab where pfabnivl = 'NIVEL4';
delete from pfab where pfabnivl = 'NIVEL3';
delete from pfab where pfabnivl = 'NIVEL2';
delete from pfab where pfabnivl = 'NIVEL1';

insert into pfab(pfab__id,version,pfabcdgo,geom,pfabnivl,pfabnmbr,pfab_dad)
select
  cast(nivel_1 as integer ),
  1,
  nivel_1,
  ST_Union(ST_MakeValid(ST_SnapToGrid(geom, 0.0001))),
  'NIVEL1',
  nombre_1,
  0
from
  pfaf5
group by
  nivel_1, nombre_1 ;

insert into pfab(pfab__id,version,pfabcdgo,geom,pfabnivl,pfabnmbr,pfab_dad)
select
  cast(nivel_2 as integer ),
  1,
  nivel_2,
  ST_Union(ST_MakeValid(ST_SnapToGrid(geom, 0.0001))),
  'NIVEL2',
  nombre_2,
  cast(nivel_1 as integer )
from
  pfaf5
group by
  nivel_2, nombre_2, nivel_1 ;

insert into pfab(pfab__id,version,pfabcdgo,geom,pfabnivl,pfabnmbr,pfab_dad)
select
  cast(nivel_3 as integer ),
  1,
  nivel_3,
  ST_Union(ST_MakeValid(ST_SnapToGrid(geom, 0.0001))),
  'NIVEL3',
  nombre_3,
  cast(nivel_2 as integer )
from
  pfaf5
group by
  nivel_3, nombre_3, nivel_2 ;

insert into pfab(pfab__id,version,pfabcdgo,geom,pfabnivl,pfabnmbr,pfab_dad)
select
  cast(nivel_4 as integer ),
  1,
  nivel_4,
  ST_Union(ST_MakeValid(ST_SnapToGrid(geom, 0.0001))),
  'NIVEL4',
  nombre_4,
  cast(nivel_3 as integer )
from
  pfaf5
group by
  nivel_4, nombre_4, nivel_3 ;

insert into pfab(pfab__id,version,pfabcdgo,geom,pfabnivl,pfabnmbr,pfab_dad)
select
  max(gid) + 10000,
  1,
  nivel_5,
  ST_Union(ST_MakeValid(ST_SnapToGrid(geom, 0.0001))),
  'NIVEL5',
  nombre_5,
  cast(nivel_4 as integer )
from
  pfaf5
group by
  nivel_5, nombre_5, nivel_4 ;

update pohm set demrdemr = (select demr__id from demr where st_within(pohm.geom,demr.geom)) ;
update pohm set sistsist = (select sist__id from sist where sistnivl = 'SISTEMA' and st_within(pohm.geom,sist.geom)) ;
update pohm set sistcuen = (select sist__id from sist where sistnivl = 'CUENCA' and st_within(pohm.geom,sist.geom)) ;
update pohm set sistsubc = (select sist__id from sist where sistnivl = 'SUBCUENCA' and st_within(pohm.geom,sist.geom)) ;
update pohm set divppais = (select divp__id from divp where divpcdgo = 'EC' and st_within(pohm.geom,divp.geom)) ;
update pohm set divpprov = (select divp__id from divp where divpnivl = 'PROVINCIA' and st_within(pohm.geom,divp.geom)) ;
update pohm set divpcant = (select divp__id from divp where divpnivl = 'CANTON' and st_within(pohm.geom,divp.geom)) ;
update pohm set divpparr = (select divp__id from divp where divpnivl = 'PARROQUIA' and st_within(pohm.geom,divp.geom)) ;
update pohm set pfabniv1 = (select pfab__id from pfab where pfabnivl = 'NIVEL1' and st_within(pohm.geom,pfab.geom)) ;
update pohm set pfabniv2 = (select pfab__id from pfab where pfabnivl = 'NIVEL2' and st_within(pohm.geom,pfab.geom)) ;
update pohm set pfabniv3 = (select pfab__id from pfab where pfabnivl = 'NIVEL3' and st_within(pohm.geom,pfab.geom)) ;
update pohm set pfabniv4 = (select pfab__id from pfab where pfabnivl = 'NIVEL4' and st_within(pohm.geom,pfab.geom)) ;
update pohm set pfabniv5 = (select pfab__id from pfab where pfabnivl = 'NIVEL5' and st_within(pohm.geom,pfab.geom)) ;

update demr set minx = st_xmin(geom);
update demr set miny = st_ymin(geom);
update demr set maxx = st_xmax(geom);
update demr set maxy = st_ymax(geom);

update sist set minx = st_xmin(geom);
update sist set miny = st_ymin(geom);
update sist set maxx = st_xmax(geom);
update sist set maxy = st_ymax(geom);

update divp set minx = st_xmin(geom);
update divp set miny = st_ymin(geom);
update divp set maxx = st_xmax(geom);
update divp set maxy = st_ymax(geom);
