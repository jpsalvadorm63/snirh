package base

class Institucion {

  String codigo
  String nombre
  String contacto
  String numero
  String mail
  String mnemonico

  static constraints = {
    codigo(nullable:false,  size:1..16,  unique: true, blank:false)
    nombre(nullable:false,  size:1..128, unique: true, blank:false)
    contacto(nullable:true, size:1..32,  blank:true)
    numero(nullable:true,   size:1..32,  blank:true)
    mail(nullable:true,     size:1..40,  email:true,   blank:true)
    mnemonico(nullable:false,  size:1..4, unique: true, blank:false)
  }

  static dbtable = 'inst'

  static mapping = {
    table   "${base.Institucion.dbtable}"
    version true
    cache   true

    id         column: "${base.Institucion.dbtable}__id"
    codigo     column: "${base.Institucion.dbtable}cdgo"
    nombre     column: "${base.Institucion.dbtable}nmbr"
    contacto   column: "${base.Institucion.dbtable}cont"
    numero     column: "${base.Institucion.dbtable}nmro"
    mail       column: "${base.Institucion.dbtable}mail"
    mnemonico  column: "${base.Institucion.dbtable}mnem"
  }

  static void fillData() {
    if(!Institucion.findByCodigo('INAMHI')) new Institucion(codigo:'INAMHI', nombre:'Instituto Nacional de Meteorología e Hidrología', mnemonico:'INAM').save(flush: true)
    if(!Institucion.findByCodigo('EMAP')) new Institucion(codigo:'EMAP', nombre:'Empresa Municipal de Agua Potable', mnemonico:'EMAP').save(flush: true)
    if(!Institucion.findByCodigo('SENAGUA')) new Institucion(codigo:'SENAGUA', nombre:'(Demarcaciones Hidrográficas) Secretaría Nacional del Agua', mnemonico:'SNGA').save(flush: true)
    if(!Institucion.findByCodigo('INECEL')) new Institucion(codigo:'INECEL', nombre:'Instituto Ecuatoriano de Electrificación', mnemonico:'INCL').save(flush: true)
    if(!Institucion.findByCodigo('INERHI')) new Institucion(codigo:'INERHI', nombre:'Instituto Ecuatoriano de Recursos Hidráulicos', mnemonico:'INRI').save(flush: true)
    if(!Institucion.findByCodigo('INOCAR')) new Institucion(codigo:'INOCAR', nombre:'Instituto Oceanográfico de la Armada', mnemonico:'INOC').save(flush: true)
    if(!Institucion.findByCodigo('MAGAP')) new Institucion(codigo:'MAGAP', nombre:'Ministerio de Agricultura, Ganadería, Acuacultura y Pesca', mnemonico:'MAGP').save(flush: true)
    if(!Institucion.findByCodigo('CLIRSEN')) new Institucion(codigo:'CLIRSEN', nombre:'Centro de Levantamientos Integrados de Recursos Naturales por Sensores Remotos', mnemonico:'CLIR').save(flush: true)
    if(!Institucion.findByCodigo('SMA')) new Institucion(codigo:'SMA', nombre:'Secretaria de Ambiente Municipio del Distrito Metropolitano de Quito', mnemonico:'SAQ').save(flush: true)
    if(!Institucion.findByCodigo('EPMAPS')) new Institucion(codigo:'EPMAPS', nombre:'Empresa pública metropolitana de Agua Potable y Saneamiento', mnemonico:'EMPS').save(flush: true)
    if(!Institucion.findByCodigo('FONAG')) new Institucion(codigo:'FONAG', nombre:'Fondo de protección del agua', mnemonico:'FNAG').save(flush: true)
    if(!Institucion.findByCodigo("?")) new Institucion(codigo:'?', nombre:'Desconocida', mnemonico:'****').save(flush: true)
  }

  String toString() { codigo }

  String toString2() { "$codigo - $nombre" }

  static entityName = "Institución"

  static entityNamePlural = "Instituciones"

}
