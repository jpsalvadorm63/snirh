@artifact.package@class @artifact.name@ {

  // campos de dominio aquí

  static constraints = {
  }

  static dbtable = '????'

  static mapping = {
    table   "${dbtable}"
    version true
    cache   false

    id           column : "${dbtable}__id"
  }

  static void fillData() {

  }

  String toString() { 'que es esto ?' }
  String toString2() { 'que va aquí ?' }

  static entityName = "@artifact.name@"

  static entityNamePlural = "@artifact.name@"

}
