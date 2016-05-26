package base

import org.springframework.dao.DataIntegrityViolationException
import com.lucastex.grails.fileuploader.UFile
import grails.plugins.springsecurity.Secured

class DatoHMGController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def springSecurityService

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    [maxDisplay: params.max, datoHMGInstanceList: DatoHMG.list(params), datoHMGInstanceTotal: DatoHMG.count(), entityName: DatoHMG.entityName, entityNamePlural: DatoHMG.entityNamePlural]
  }

  def create() {
    [datoHMGInstance: new DatoHMG(params), entityName: DatoHMG.entityName, entityNamePlural: DatoHMG.entityNamePlural]
  }

  def save() {
    def datoHMGInstance = new DatoHMG(params)
    if (!datoHMGInstance.save(flush: true)) {
      render(view: "create", model: [datoHMGInstance: datoHMGInstance, entityName: DatoHMG.entityName, entityNamePlural: DatoHMG.entityNamePlural])
      return
    }

    String descripcion = "'${datoHMGInstance.toString()}'"
    flash.message = message(code: 'default.created.message', args: [message(code: 'datoHMG.label', default: 'DatoHMG'), descripcion])
    redirect(action: "show", id: datoHMGInstance.id)
  }

  def show(Long id) {
    def datoHMGInstance = DatoHMG.get(id)
    if (!datoHMGInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'datoHMG.label', default: 'DatoHMG'), id])
      redirect(action: "list")
      return
    }

    [datoHMGInstance: datoHMGInstance, entityName: DatoHMG.entityName, entityNamePlural: DatoHMG.entityNamePlural]
  }

  def edit(Long id) {
    def datoHMGInstance = DatoHMG.get(id)
    if (!datoHMGInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'datoHMG.label', default: 'DatoHMG'), id])
      redirect(action: "list")
      return
    }

    [datoHMGInstance: datoHMGInstance, entityName: DatoHMG.entityName, entityNamePlural: DatoHMG.entityNamePlural]
  }

  def update(Long id, Long version) {
    def datoHMGInstance = DatoHMG.get(id)
    if (!datoHMGInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'datoHMG.label', default: 'DatoHMG'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (datoHMGInstance.version > version) {
        datoHMGInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
            [message(code: 'datoHMG.label', default: 'DatoHMG')] as Object[],
            "Another user has updated this DatoHMG while you were editing")
        render(view: "edit", model: [datoHMGInstance: datoHMGInstance])
        return
      }
    }

    datoHMGInstance.properties = params

    if (!datoHMGInstance.save(flush: true)) {
      render(view: "edit", model: [datoHMGInstance: datoHMGInstance])
      return
    }

    String descripcion = "'${datoHMGInstance.toString()}'"
    flash.message = message(code: 'default.updated.message', args: [message(code: 'datoHMG.label', default: 'DatoHMG'), descripcion])
    redirect(action: "show", id: datoHMGInstance.id, entityName: DatoHMG.entityName, entityNamePlural: DatoHMG.entityNamePlural)
  }

  def delete(Long id) {
    def datoHMGInstance = DatoHMG.get(id)
    if (!datoHMGInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'datoHMG.label', default: 'DatoHMG'), id])
      redirect(action: "list")
      return
    }

    String descripcion = "'${datoHMGInstance.toString()}'"
    try {
      datoHMGInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'datoHMG.label', default: 'DatoHMG'), descripcion])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'datoHMG.label', default: 'DatoHMG'), id])
      redirect(action: "show", id: id)
    }
  }

  /*def uploadFile = {
    def auth = springSecurityService.getCurrentUser()
    def props = auth.getProperties()
    accesus.Usuario usuario = accesus.Usuario.findByUsername(props['username'])

    String fileName = UFile.get(params.ufileId).path
    String name = UFile.get(params.ufileId).name
    String[] namesplited = name.split("-")

    if(new File(fileName).exists()) {
      int n = 0
      ExcelBuilderService eb = new ExcelBuilderService(fileName)
      n = (eb.workbook)?eb.workbook.getNumberOfSheets():0
      for (i in (0..n - 1)) {
        def values = [:]
        String pohm = eb.getSheetName(i)
        values << [pohm:"$pohm"]
        eb.eachLine([labels:true, sheet:i]) {
          // falta llenar tipoRelleno
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
              values << [tipoRelleno:"NO"]
              values << [usuario:usuario]
              base.DatoHMG.fillData(values)
            }
          }
        }
      }
    }
    redirect(action: "list")
    return
  }

  def uploadFileError = {
    flash.message = "Error en subida masiva de datos, archivo nó valido"
    redirect(action: "list")
    return
  }*/

}
