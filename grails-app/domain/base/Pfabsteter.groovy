package base

import com.vividsolutions.jts.geom.MultiPolygon
import org.hibernatespatial.GeometryUserType

class Pfabsteter {

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
    codigo(maxSize: 16,nullable:false)
    nivel(inList:["NIVEL1","NIVEL2","NIVEL3","NIVEL4","NIVEL5"],size:1..16,nullable:false)
    nombre(size:1..64,nullable:false)
    dad(nullable:true)
    minx(nullable:true)
    miny(nullable:true)
    maxx(nullable:true)
    maxy(nullable:true)
  }

  static dbtable = 'pfab'

  static mapping = {
    table "${Pfabsteter.dbtable}"
    version true
    cache false

    id        column: "${Pfabsteter.dbtable}__id"
    codigo    column: "${Pfabsteter.dbtable}cdgo"
    nombre    column: "${Pfabsteter.dbtable}nmbr"
    nivel     column: "${Pfabsteter.dbtable}nivl"
    dad       column: "${Pfabsteter.dbtable}_dad"
    geom      type:GeometryUserType, sqlType:"Geometry", column: "geom"
  }

  static void fillData() {

  }

  String toString() { nombre }

  String toString2() { "$nombre ($codigo)" }

  static entityName = "Pfabsteter"

  static entityNamePlural = "Niveles Pfabsteter"

}
