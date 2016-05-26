package base

class Operativo {

  String codigo
  String descripcion

  static constraints = {
    codigo(nullable:false, size:1..8, blank:false, columnName:'Código')
    descripcion(nullable:false, size:1..32, blank:false, columnName:'Descripción')
  }

  static dbtable = 'oprt'

  static mapping = {
    table "${Operativo.dbtable}"
    version true
    cache false

    id            column: "${Operativo.dbtable}__id"
    codigo        column: "${Operativo.dbtable}cdgo"
    descripcion   column: "${Operativo.dbtable}desc"
  }

  static void fillData() {
    if(!Operativo.findByCodigo('SI')) new Operativo([codigo:'SI', descripcion:'Operativo']).save(flush: true)
    if(!Operativo.findByCodigo('NO')) new Operativo([codigo:'NO', descripcion:'NO Operativo']).save(flush: true)
    if(!Operativo.findByCodigo('?')) new Operativo([codigo:'?', descripcion:'NO se conoce']).save(flush: true)
  }

  String toString() { "${codigo}" }

  String toString2() { "${codigo} - ${descripcion}" }

  static entityName = "Operativo"

  static entityNamePlural = "Operativos"

}
