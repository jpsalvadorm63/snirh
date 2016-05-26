package base

class EstadoEstacion {

  String codigo
  String estado

  static constraints = {
    codigo(nullable:false, size:1..8, unique: true)
    estado(nullable:false, size:1..16, unique: true)
  }

  static mapping = {
    table 'espo'
    version true
    cache true

    id       column: 'espo__id'
    codigo   column: 'espocdgo'
    estado   column: 'esponmbr'
  }

  static void fillData() {
    if(!EstadoEstacion.findByCodigo("BUENA")) new EstadoEstacion(codigo:'BUENA', estado:'Bueno').save(flush: true)
    if(!EstadoEstacion.findByCodigo("MALO")) new EstadoEstacion(codigo:'MALO', estado:'Malo').save(flush: true)
    if(!EstadoEstacion.findByCodigo("REGULAR")) new EstadoEstacion(codigo:'REGULAR', estado:'Reguloa').save(flush: true)
    if(!EstadoEstacion.findByCodigo("?")) new EstadoEstacion(codigo:'?', estado:'Desconocido').save(flush: true)

  }

  String toString() { estado }

  String toString2() { '$estado ($codigo)' }

  static entityName = "Estado de Estacion"

  static entityNamePlural = "Estados de Estacion"

}
