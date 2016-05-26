package base

class Dominio {

  String codigo
  String dominio

  static constraints = {
    codigo(nullable:false, size:1..4, unique: true)
    dominio(nullable:false, size:1..16, unique: true)
  }

  static dbtable = 'domn'
  static mapping = {
    table   'domn'
    version true
    cache   true

    id       column : 'domn__id'
    codigo   column : 'domncdgo'
    dominio  column : 'domnnmbr'
  }

  static void fillData() {
    if(!Dominio.findByCodigo("HI")) new Dominio(codigo: 'HI', dominio:'Hidrología').save(flush: true)
    if(!Dominio.findByCodigo("MET")) new Dominio(codigo: 'MET', dominio: 'Meteorología').save flush: true
    if(!Dominio.findByCodigo("HG")) new Dominio(codigo:'HG', dominio: 'Hidrogeología').save(flush: true)
    if(!Dominio.findByCodigo("HQ")) new Dominio(codigo:'hq', dominio: 'Calidad del Agua').save(flush: true)
    if(!Dominio.findByCodigo("?")) new Dominio(codigo:'?', dominio: 'Desconocido').save(flush: true)
  }

  String toString() { dominio }

  String toString2() { "$dominio ($codigo)" }

  static entityName = "Dominio"

  static entityNamePlural = "Dominios"

}
