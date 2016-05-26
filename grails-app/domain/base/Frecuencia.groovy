package base

class Frecuencia {

  String codigo
  String frecuencia

  static constraints = {
    codigo(nullable: false, maxSize: 16, unique: true)
    frecuencia(nullable: false, maxSize: 64, unique: true)
  }

  static dbtable = 'frec'

  static mapping = {
    table "frec"
    version true
    cache false

    id column: "frec__id"
    codigo column: "freccdgo"
    frecuencia column: "frectipo"
  }

  static void fillData() {
    if(!Frecuencia.findByCodigo("evt")) new Frecuencia(codigo: 'evt', frecuencia:'eventual').save(flush: true)
    if(!Frecuencia.findByCodigo("hor")) new Frecuencia(codigo: 'hor', frecuencia:'horaria').save(flush: true)
    if(!Frecuencia.findByCodigo("bihor")) new Frecuencia(codigo: 'bihor', frecuencia:'bi-horaria').save(flush: true)
    if(!Frecuencia.findByCodigo("dia")) new Frecuencia(codigo: 'dia', frecuencia:'diaria').save(flush: true)
    if(!Frecuencia.findByCodigo("sem")) new Frecuencia(codigo: 'sem', frecuencia:'semanal').save(flush: true)
    if(!Frecuencia.findByCodigo("men")) new Frecuencia(codigo: 'men', frecuencia:'mensual').save(flush: true)
    if(!Frecuencia.findByCodigo("anu")) new Frecuencia(codigo: 'anu', frecuencia:'anual').save(flush: true)
    if(!Frecuencia.findByCodigo("?")) new Frecuencia(codigo:'?', frecuencia:'Desconocida').save(flush: true)
  }

  String toString() { frecuencia }

  String toString2() { "$frecuencia ($codigo)" }

  static entityName = "Frecuencia"

  static entityNamePlural = "Frecuencias"

}
