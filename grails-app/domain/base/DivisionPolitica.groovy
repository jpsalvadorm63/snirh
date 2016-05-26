package base

import com.vividsolutions.jts.geom.MultiPolygon
import org.hibernatespatial.GeometryUserType

class DivisionPolitica {

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
    nivel(inList:["PAIS","PROVINCIA","CANTON","PARROQUIA"],size:1..16,nullable:false)
    nombre(size:1..128,nullable:false)
    dad(nullable:true)
    minx(nullable:true)
    miny(nullable:true)
    maxx(nullable:true)
    maxy(nullable:true)
  }

  static dbtable = 'divp'

  static mapping = {
    table "${base.DivisionPolitica.dbtable}"
    version true
    cache false

    id      column: "${base.DivisionPolitica.dbtable}__id"
    codigo  column: "${base.DivisionPolitica.dbtable}cdgo"
    nombre  column: "${base.DivisionPolitica.dbtable}nmbr"
    nivel   column: "${base.DivisionPolitica.dbtable}nivl"
    dad     column: "${base.DivisionPolitica.dbtable}_dad"
    geom    type: GeometryUserType, sqlType:"Geometry", column: "geom"
  }

  static void fillData() {

  }

  String toString() { nombre }

  String toString2() { "$nombre ($codigo)" }

  static entityName = "División Política"

  static entityNamePlural = "Divisiones Políticas"

}
