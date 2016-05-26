package snirh

import org.springframework.dao.DataIntegrityViolationException
import com.lucastex.grails.fileuploader.UFile

class TestJob {

  def springSecurityService

  static boolean enEjecucion = false

  static triggers = {
    simple repeatInterval: 60001
  }

  def execute() {
    if(!enEjecucion) {
      enEjecucion = true
      base.UpldFile.findAllBySubido(false).each { upldFile ->
        def fileName = upldFile.path
        String name = upldFile.nombre
        String usuario = upldFile.usuario
        // println ">>>>>>>> $fileName - $name - $usuario"
        try {
            upldFile.subido = true
            upldFile.save(flush: true)
            int n = 0
            base.ExcelBuilderService eb = new base.ExcelBuilderService(fileName)
            n = (eb.workbook)?eb.workbook.getNumberOfSheets():0
            for (i in (0..n - 1)) {
              String sheetName = eb.getSheetName(i)
              if(sheetName == 'HIDROMET') {
                eb.eachLine([labels:true, sheet:i]) { dataLine ->
                  def values = [:]
                  def institucion = cell(0)
                  def pohm = cell(1)
                  def parametro = cell(2)
                  def estadistica = cell(3)
                  def frecuencia = cell(4)
                  def fechaHora = cell(5)
                  def datoOriginal = cell(6)
                  def datoRellenado = cell(7)
                  def tipoRelleno = cell(8) ; tipoRelleno = (tipoRelleno == null)?'NO':tipoRelleno
                  def datoSenagua = cell(9)
                  values << [institucion:"$institucion"]
                  values << [pohm:"$pohm"]
                  values << [parametro:"$parametro"]
                  values << [estadistica:"$estadistica"]
                  values << [frecuencia:"$frecuencia"]
                  values << [fechaHora:"$fechaHora"]
                  values << [datoOriginal:"$datoOriginal"]
                  values << [datoRellenado:"$datoRellenado"]
                  values << [tipoRelleno:"$tipoRelleno"]
                  values << [datoSenagua:"$datoSenagua"]
                  values << [usuario:usuario]
                  values << [file:upldFile]
                  println values
                  base.DatoHMG.fillData(values)
                }
                upldFile.subido = true
                upldFile.cargado = true
                upldFile.falla = false
                upldFile.save(flush: true)
                println "-----*"
              }
            }
        } catch (Exception ex) {
            upldFile.cargado = true
            upldFile.falla = true
            upldFile.save(flush: true)
            println "ERROR . . .${ex}"
        }
      }
      enEjecucion = false
    }
  }




  def execute_OLD() {
    if(!enEjecucion) {
      enEjecucion = true
      base.UpldFile.findAllBySubido(false).each { upldFile ->
        def fileName = upldFile.path
        String name = upldFile.nombre
        boolean esRelleno = name.indexOf("-rell") >= 0
        String usuario = upldFile.usuario
        String[] namesplited = name.split("-")
        println fileName
        try {
          upldFile.subido = true
          upldFile.save(flush: true)
          int n = 0
          base.ExcelBuilderService eb = new base.ExcelBuilderService(fileName)
          n = (eb.workbook)?eb.workbook.getNumberOfSheets():0
          for (i in (0..n - 1)) {
            def values = [:]
            String pohm = eb.getSheetName(i)
            values << [pohm:"$pohm"]
            eb.eachLine([labels:true, sheet:i]) {
              String parametro = namesplited[1]
              String frecuencia = namesplited[3]
              String estadistica = namesplited[2]
              values << [parametro:"${parametro}"]
              values << [frecuencia:"${frecuencia}"]
              values << [estadistica:"${estadistica}"]
              ArrayList months = ["01","02","03","04","05","06", "07","08","09","10","11","12"]
              months.each { month ->
                if ("${cell(0)}".isNumber()) {
                  String fechaHora = "1/${month}/${cell(0)}"
                  values << [fechaHora:"${fechaHora}"]
                  values << [datoOriginal:"${cell(months.indexOf(month)+1)}"]
                  values << [datoRellenado:null]
                  values << [datoSenagua:null]
                  values << [tipoRelleno:((esRelleno)?"RELL":"NO")]
                  values << [usuario:usuario]
                  values << [file:upldFile]
                  base.DatoHMG.fillData(values)
                }
              }
            }
          }
          upldFile.subido = true
          upldFile.cargado = true
          upldFile.falla = false
          upldFile.save(flush: true)

        } catch (Exception ex) {
          upldFile.cargado = true
          upldFile.falla = true
          upldFile.save(flush: true)
          println "ERROR . . .${ex}"
        }
      }
      enEjecucion = false
    }
  }

}
