package base

import  com.vividsolutions.jts.geom.Point
import org.hibernatespatial.GeometryUserType
import com.vividsolutions.jts.geom.GeometryFactory
import com.vividsolutions.jts.geom.PrecisionModel
import com.vividsolutions.jts.geom.Coordinate

class Pohm {
  Dominio dominio
  String codigo
  String codigoSENAGUA
  String nombre
  Date desde
  Date hasta
  Operativo operativo
  TipoEstacion tipoEstacion
  Institucion institucion
  EstadoEstacion estadoEstacion
  float latitud
  float longitud
  Point puntoxy

  // GEOGRAFIA !!
  Demarcacion demarcacion
  Sistema sistema
  Sistema cuenca
  Sistema subcuenca
  DivisionPolitica pais
  DivisionPolitica provincia
  DivisionPolitica canton
  DivisionPolitica parroquia
  Pfabsteter pfabsteter1
  Pfabsteter pfabsteter2
  Pfabsteter pfabsteter3
  Pfabsteter pfabsteter4
  Pfabsteter pfabsteter5

  double x
  double y
  double area
  int elevacion
  String observaciones

  static constraints = {
    dominio(nullable:false, columnName:'Dominio')
    codigo(nullable:false, size:1..16, blank:false, columnName:'Código')
    codigoSENAGUA(nullable:true, size:1..16, blank:false, columnName:'Cód.SENAGUA')
    nombre(nullable:false, size:1..128, blank:false, columnName:'Nombre')
    desde(nullable:true, columnName:'Desde')
    hasta(nullable:true, columnName:'Hasta')
    operativo(nullable:true, columnName:'Operativo')
    tipoEstacion(nullable:false, columnName:'Tipo')
    institucion(nullable:false, columnName:'Institución')
    estadoEstacion(nullable:false, columnName:'Estado')
    latitud(nullable:true, columnName:'Latitud')
    longitud(nullable:true, columnName:'Longitud')
    x(nullable:true, columnName:'X')
    y(nullable:true, columnName:'Y')
    area(nullable:true, columnName:'Area')
    elevacion(nullable:true, columnName:'Elevación')
    observaciones(nullable:true, size:1..128, blank:false, columnName:'Observaciones')
    puntoxy(nullable:true)
    demarcacion(nullable:true)
    sistema(nullable:true)
    cuenca(nullable:true)
    subcuenca(nullable:true)
    pais(nullable:true)
    provincia(nullable:true)
    canton(nullable:true)
    parroquia(nullable:true)
    pfabsteter1(nullable:true)
    pfabsteter2(nullable:true)
    pfabsteter3(nullable:true)
    pfabsteter4(nullable:true)
    pfabsteter5(nullable:true)
  }

  static dbtable = 'pohm'

  static mapping = {
    table "${Pohm.dbtable}"
    version true
    cache true

    dominio             column:"domn__id"
    id                  column:"${Pohm.dbtable}__id"
    codigo              column:"${Pohm.dbtable}cdgo"
    codigoSENAGUA       column:"${Pohm.dbtable}cdgs"
    nombre              column:"${Pohm.dbtable}nmbr"
    desde               column:"${Pohm.dbtable}desd"
    hasta               column:"${Pohm.dbtable}hast"
    tipoEstacion        column:"ties__id"
    operativo           column:"oprt__id"
    institucion         column:"inst__id"
    estadoEstacion      column:"espo__id"
    latitud             column:"${Pohm.dbtable}lati"
    longitud            column:"${Pohm.dbtable}long"
    x                   column:"${Pohm.dbtable}___x"
    y                   column:"${Pohm.dbtable}___y"
    area                column:"${Pohm.dbtable}area"
    elevacion           column:"${Pohm.dbtable}elev"
    observaciones       column:"${Pohm.dbtable}obsr"
    puntoxy             type:GeometryUserType, sqlType:"Geometry", column:"geom"
    demarcacion         column:"demrdemr"
    sistema             column:"sistsist"
    cuenca              column:"sistcuen"
    subcuenca           column:"sistsubc"
    pais                column:"divppais"
    provincia           column:"divpprov"
    canton              column:"divpcant"
    parroquia           column:"divpparr"
    pfabsteter1         column:"pfabniv1"
    pfabsteter2         column:"pfabniv2"
    pfabsteter3         column:"pfabniv3"
    pfabsteter4         column:"pfabniv4"
    pfabsteter5         column:"pfabniv5"
  }

  static void fillData() {}

  static void fillData(params) {
    String obs = ""
    params['operativo'] = (params['operativo'] == 'OP')?'SI':params['operativo']
    params['operativo'] = (params['operativo'] == 'NOOP')?'NO':params['operativo']
    def domn = Dominio.findByCodigo(params['dominio'])
    def inst = Institucion.findByCodigo(params['institucion'])
    def ties = TipoEstacion.findByCodigo(params['tipoEstacion'])
    def espo = EstadoEstacion.findByCodigo(params['estadoEstacion'])
    def oprt = Operativo.findByCodigo(params['operativo'])
    if(!domn) {
      obs += "Dominio desconocido:${params['dominio']} ;"
      domn = Dominio.findByCodigo("?")
    }
    if(!inst) {
      obs += "[Institución desconcocida:${params['institucion']}]"
      inst = Institucion.findByCodigo("?")
    }
    if(!ties) {
      obs += "[Tipo estación desconocida:${params['tipoEstacion']}]"
      ties = TipoEstacion.findByCodigo("?")
    }
    if(!espo) {
      obs += "[Estado de la Estación desconocido:${params['estadoEstacion']}]"
      oprt = Operativo.findByCodigo("?")
    }
    if(!oprt) {
      obs += "[Operatividad desconocida:${params['operativo']}]"
      espo = EstadoEstacion.findByCodigo("?")
    }
    def pohm = base.Pohm.findByCodigo(params['codigo'])
    if(!pohm ) {
      pohm = new Pohm()
      pohm.codigo = params.codigo
    }
    pohm.nombre = params.nombre
    pohm.dominio = domn
    pohm.institucion = inst
    pohm.tipoEstacion = ties
    pohm.estadoEstacion = espo
    pohm.operativo = oprt
    pohm.latitud = (params.latitud != 'null')?(params.latitud.toFloat()):null
    pohm.longitud = (params.longitud != 'null')?(params.longitud.toFloat()):null
    //pohm.elevacion = (params.elevacion != 'null')?(params.elevacion.toInt):null
    pohm.x = (params.x != 'null')?(params.x.toFloat()):null
    pohm.y = (params.y != 'null')?(params.y.toFloat()):null
    pohm.puntoxy = null
    pohm.demarcacion = null
    pohm.sistema = null
    pohm.cuenca = null
    pohm.subcuenca = null
    pohm.pais = null
    pohm.provincia = null
    pohm.canton = null
    pohm.parroquia = null
    if(obs != "")
      pohm.observaciones = (obs.size()>125)?obs.substring(1,125):obs

    try {
      pohm.save(flush:true)
    } catch (ex) {
      println ex
    }
  }

  String toString() { "${codigo}" }

  String toString2() { "${codigo} - ${nombre}" }

  static entityName = "Punto de Observación"

  static entityNamePlural = "Puntos de Observación"

  static GeometryFactory gf = null

  def beforeSave() {
    updateGeom()
  }

  def beforeUpdate() {
    updateGeom()
  }

      def updateGeom() {
        if(base.Pohm.gf == null)
          base.Pohm.gf = new GeometryFactory(new PrecisionModel(),4326 )
        if( (x != null) && (y != null) ) {
          puntoxy = base.Pohm.gf.createPoint(new Coordinate(x,y))
        }
        else
          puntoxy = null
      }

}
