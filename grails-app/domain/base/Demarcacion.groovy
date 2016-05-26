package base

import  com.vividsolutions.jts.geom.MultiPolygon
import org.hibernatespatial.GeometryUserType

class Demarcacion {

  String codigo
  String nombre
  MultiPolygon geom
  Double minx
  Double miny
  Double maxx
  Double maxy


  static constraints = {
    codigo(nullable:false, size:1..16, blank:false, columnName:'Código')
    nombre(nullable:false, size:1..64, blank:false, columnName:'Nombre')
    minx(nullable:true)
    miny(nullable:true)
    maxx(nullable:true)
    maxy(nullable:true)
  }

  static dbtable = 'demr'

  static mapping = {
    table "${base.Demarcacion.dbtable}"
    version true
    cache true

    id column: "${base.Demarcacion.dbtable}__id"
    codigo column: "${base.Demarcacion.dbtable}cdgo"
    nombre column: "${base.Demarcacion.dbtable}nmbr"
    geom type:GeometryUserType, sqlType:"Geometry", column: "geom"
  }

  static void fillData() {
  }

  String toString() { nombre }

  String toString2() { "$nombre ($codigo)" }

  static entityName = "Demarcación"

  static entityNamePlural = "Demarcaciones"

}
