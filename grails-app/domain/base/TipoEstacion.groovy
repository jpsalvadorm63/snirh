package base

class TipoEstacion {

  String codigo
  String tipoEstacion
  Dominio dominio

  static constraints = {
    codigo(nullable:false, size:1..8, unique: true)
    tipoEstacion(nullable:false, size:1..32, unique: true)
    dominio(nullable:false)
  }

  static mapping = {
    table   'ties'
    version true
    cache   true

    id            column : 'ties__id'
    codigo        column : 'tiescdgo'
    tipoEstacion  column : 'tiesnmbr'
    dominio       column : 'domn__id'
  }

  static void fillData() {
    if(!TipoEstacion.findByCodigo('?')) new TipoEstacion(codigo:'?', tipoEstacion:'Desconocido', dominio: Dominio.findByCodigo('?')).save(flush:true)
    if(!TipoEstacion.findByCodigo('LG')) new TipoEstacion(codigo:'LG', tipoEstacion:'Limnigráfica', dominio: Dominio.findByCodigo('HI')).save(flush:true)
    if(!TipoEstacion.findByCodigo('LM')) new TipoEstacion(codigo:'LM', tipoEstacion:'Limnimétrica', dominio: Dominio.findByCodigo('HI')).save(flush:true)
    if(!TipoEstacion.findByCodigo('AU')) new TipoEstacion(codigo:'AU', tipoEstacion:'Automática', dominio: Dominio.findByCodigo('HI')).save(flush:true)
    if(!TipoEstacion.findByCodigo('AP')) new TipoEstacion(codigo:'AP', tipoEstacion:'Agrometeorológica', dominio: Dominio.findByCodigo('MET')).save(flush:true)
    if(!TipoEstacion.findByCodigo('CP')) new TipoEstacion(codigo:'CP', tipoEstacion:'Climatológica Principal', dominio: Dominio.findByCodigo('MET')).save(flush:true)
    if(!TipoEstacion.findByCodigo('CO')) new TipoEstacion(codigo:'CO', tipoEstacion:'Climatológica Ordinaria', dominio: Dominio.findByCodigo('MET')).save(flush:true)
    if(!TipoEstacion.findByCodigo('CE')) new TipoEstacion(codigo:'CE', tipoEstacion:'Climatológica Especial', dominio: Dominio.findByCodigo('MET')).save(flush:true)
    if(!TipoEstacion.findByCodigo('AR')) new TipoEstacion(codigo:'AR', tipoEstacion:'Aeronáutica', dominio: Dominio.findByCodigo('MET')).save(flush:true)
    if(!TipoEstacion.findByCodigo('RS')) new TipoEstacion(codigo:'RS', tipoEstacion:'Radio Sonda', dominio: Dominio.findByCodigo('MET')).save(flush:true)
    if(!TipoEstacion.findByCodigo('PV')) new TipoEstacion(codigo:'PV', tipoEstacion:'Pluviométrica', dominio: Dominio.findByCodigo('MET')).save(flush:true)
    if(!TipoEstacion.findByCodigo('PG')) new TipoEstacion(codigo:'PG', tipoEstacion:'Pluviográfica', dominio: Dominio.findByCodigo('MET')).save(flush:true)
    if(!TipoEstacion.findByCodigo('PC')) new TipoEstacion(codigo:'PC', tipoEstacion:'Plataforma colectora de datos', dominio: Dominio.findByCodigo('MET')).save(flush:true)
    if(!TipoEstacion.findByCodigo('AN')) new TipoEstacion(codigo:'AN', tipoEstacion:'Anemográfica', dominio: Dominio.findByCodigo('MET')).save(flush:true)
    if(!TipoEstacion.findByCodigo('PP')) new TipoEstacion(codigo:'PP', tipoEstacion:'Pozo perforado', dominio: Dominio.findByCodigo('HG')).save(flush:true)
    if(!TipoEstacion.findByCodigo('PE')) new TipoEstacion(codigo:'PE', tipoEstacion:'Pozo excavado', dominio: Dominio.findByCodigo('HG')).save(flush:true)
    if(!TipoEstacion.findByCodigo('VV')) new TipoEstacion(codigo:'VV', tipoEstacion:'Vertiente', dominio: Dominio.findByCodigo('HG')).save(flush:true)
    if(!TipoEstacion.findByCodigo('VT')) new TipoEstacion(codigo:'VT', tipoEstacion:'Vertiente termal', dominio: Dominio.findByCodigo('HG')).save(flush:true)
    if(!TipoEstacion.findByCodigo('Ga')) new TipoEstacion(codigo:'Ga', tipoEstacion:'Galería', dominio: Dominio.findByCodigo('HG')).save(flush:true)
  }

  String toString() { tipoEstacion }

  String toString2() { "$tipoEstacion ($codigo)" }

  static entityName = "Tipo de Estación"

  static entityNamePlural = "Tipos de Estación"

}
