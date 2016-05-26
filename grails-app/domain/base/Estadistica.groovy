package base

class Estadistica {

  String codigo
  String estadistica
  String descripcion

  static constraints = {
    codigo(nullable: false, maxSize: 16, unique: true)
    estadistica(nullable: false, maxSize: 64, unique: true)
    descripcion(nullable: true, maxSize: 128)
  }

  static dbtable = 'stad'

  static mapping = {
    table "${base.Estadistica.dbtable}"
    version true
    cache false

    id          column:"${base.Estadistica.dbtable}__id"
    estadistica column:"${base.Estadistica.dbtable}stad"
    descripcion column:"${base.Estadistica.dbtable}desc"
  }

  static void fillData() {
    if(!Estadistica.findByCodigo("max"))  new Estadistica(codigo: 'max',  estadistica:'Máximo').save(flush: true)
    if(!Estadistica.findByCodigo("min"))  new Estadistica(codigo: 'min',  estadistica:'Mínimo').save(flush: true)
    if(!Estadistica.findByCodigo("prom")) new Estadistica(codigo: 'prom', estadistica:'Promedio').save(flush: true)
    if(!Estadistica.findByCodigo("med"))  new Estadistica(codigo: 'med',  estadistica:'Media').save(flush: true)
    if(!Estadistica.findByCodigo("tot"))  new Estadistica(codigo: 'tot',  estadistica:'Total').save(flush: true)
    if(!Estadistica.findByCodigo("?"))    new Estadistica(codigo: '?',    estadistica: 'Desconocida').save(flush: true)
  }

  String toString() { estadistica }

  String toString2() { "$estadistica ($codigo)" }

  static entityName = "Estadistica"

  static entityNamePlural = "Estadisticas"

}
