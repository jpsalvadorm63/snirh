package base

import com.vividsolutions.jts.geom.MultiPolygon
import org.hibernatespatial.GeometryUserType

class Sistema {

  String codigo
  String nombre
  String nivel
  int dad
  MultiPolygon geom

  Double minx
  Double miny
  Double maxx
  Double maxy

  static constraints = {
    codigo(size:1..16,nullable:false)
    nivel(inList:["SISTEMA","CUENCA","SUBCUENCA","MICROCUENCA"],size:1..16,nullable:false)
    nombre(size:1..32,nullable:false)
    dad(nullable:true)
    minx(nullable:true)
    miny(nullable:true)
    maxx(nullable:true)
    maxy(nullable:true)
  }

  static dbtable = 'sist'

  static mapping = {
    table "${base.Sistema.dbtable}"
    version true
    cache false

    id      column: "${base.Sistema.dbtable}__id"
    codigo  column: "${base.Sistema.dbtable}cdgo"
    nombre  column: "${base.Sistema.dbtable}nmbr"
    nivel   column: "${base.Sistema.dbtable}nivl"
    dad     column: "${base.Sistema.dbtable}_dad"
    geom    type:GeometryUserType, sqlType:"Geometry", column: "geom"
  }

  static void fillData() {

  }

  String toString() { nombre }

  String toString2() { "$nombre ($codigo)" }

  static entityName = "Sistema"

  static entityNamePlural = "Sistemas, cuencas y subcuencas"

}
