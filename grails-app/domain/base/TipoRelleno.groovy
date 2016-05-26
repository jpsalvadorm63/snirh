package base

class TipoRelleno {

  String codigo
  String tipoRelleno
  String color
  String descripcion

  static constraints = {
    codigo(unique:true, size: 1..16, nullable: false, blank: false)
    tipoRelleno(unique:true, size: 1..128, nullable: false, blank: false)
    color(unique:true, maxSize: 32, nullable: true, blank: true)
    descripcion(maxSize: 128, nullable: true, blank: true)
  }

  static dbtable = 'rell'

  static mapping = {
    table "${base.TipoRelleno.dbtable}"
    version true
    cache true

    id          column:"${base.TipoRelleno.dbtable}__id"
    codigo	    column:"${base.TipoRelleno.dbtable}cdgo"
    tipoRelleno	column:"${base.TipoRelleno.dbtable}tipo"
    color       column:"${base.TipoRelleno.dbtable}colr"
    descripcion	column:"${base.TipoRelleno.dbtable}desc"
  }

  static void fillData() {
    new TipoRelleno(color:'Blanco',codigo:'NO',tipoRelleno:'NO RELLENADO',descripcion:'DATOS ORIGINALES').save(flush:true)
    new TipoRelleno(color:'Rosa',codigo:'PMD',tipoRelleno:'PROMEDIO DATOS DIARIOS',descripcion:'claro	DATOS RELLENADOS CON EL PROMEDIO DE DATOS DIARIOS (- 20 DIAS DE OBSERVACIONES)').save(flush:true)
    new TipoRelleno(color:'Amarillo',codigo:'PVC',tipoRelleno:'PROMEDIO DE LOS VALORES CIRCUNDANTES',descripcion:'claro	DATOS RELLENADOS CON EL PROMEDIO DE LOS VALORES CIRCUNDANTES').save(flush:true)
    new TipoRelleno(color:'Azul',codigo:'CSE',tipoRelleno:'CORRELACIÓN SIMPLE CON ESTACIÓN H347',descripcion:'cielo	DATOS RELLENADOS CON CORRELACIÓN SIMPLE CON ESTACIÓN H347').save(flush:true)
    new TipoRelleno(color:'Lavanda',codigo:'VCVR',tipoRelleno:'VALORES CALCULADOS DEL VECTOR REGIONAL',descripcion:'DATOS RELLENADOS CON LOS VALORES CALCULADOS DEL VECTOR REGIONAL').save(flush:true)
    new TipoRelleno(color:'Verde',codigo:'PVC',tipoRelleno:'PROMEDIO DE LOS VALORES CIRCUNDANTES',descripcion:'DATOS RELLENADOS CON EL PROMEDIO DE LOS VALORES CIRCUNDANTES').save(flush:true)
    new TipoRelleno(color:'Anaranjado',codigo:'PDM',tipoRelleno:'PROMEDIO DE LOS DATOS DEL MES',descripcion:'DATOS RELLENADOS CON EL PROMEDIO DE LOS DATOS DEL MES').save(flush:true)
    new TipoRelleno(color:'Gris',codigo:'RELL',tipoRelleno:'DATO RELLENADO',descripcion:'DATO RELLENADO PERO NO ESPECIFICA METODO').save(flush:true)
  }

  String toString() { tipoRelleno }

  String toString2() { "${tipoRelleno} (${codigo})" }

  static entityName = "Tipo de Relleno"

  static entityNamePlural = "Tipos de Relleno"

}
