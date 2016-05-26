package base

class UpldFile {

  String  nombre
  String  path
  Date    fechaCarga
  Boolean subido
  Boolean cargado
  Boolean falla
  String  nota
  String  usuario

  static constraints = {
    nombre      nullable:false, maxSize:64
    path        nullable:false, maxSize:255
    fechaCarga  nullable:false
    subido      nullable:false
    cargado     nullable:false
    falla       nullable:false
    nota        nullable:true, maxSize:64
    usuario     nullable:true, maxSize:128
  }

  static dbtable = 'upld'

  static mapping = {
    table   "${base.UpldFile.dbtable}"
    version true
    cache   false

    id         column:"${base.UpldFile.dbtable}__id"
    nombre     column:"${base.UpldFile.dbtable}file"
    path       column:"${base.UpldFile.dbtable}path"
    fechaCarga column:"${base.UpldFile.dbtable}fech"
    subido     column:"${base.UpldFile.dbtable}subi"
    cargado    column:"${base.UpldFile.dbtable}carg"
    falla      column:"${base.UpldFile.dbtable}fail"
    nota       column:"${base.UpldFile.dbtable}note"
    usuario    column: "usuario"
  }

  static void fillData() {
  }

  String toString() { nombre }

  String toString2() { "$nombre ($fechaCarga)" }

  static entityName = "Archivo de Datos"

  static entityNamePlural = "Archivos de Datos"

}
