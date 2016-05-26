package base

import org.springframework.dao.DataIntegrityViolationException
import com.lucastex.grails.fileuploader.UFile
import pl.touk.excel.export.WebXlsxExporter

class UpldFileController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def springSecurityService

    def list(Integer max) {
      params.max = Math.min(max ?:8, 100)
      params.offset = (params.offset)?(params.offset as String).toInteger():0
      [maxDisplay:params.max, upldFileInstanceList: UpldFile.list(params), offset: params.offset, upldFileInstanceTotal: UpldFile.count(),entityName:UpldFile.entityName,entityNamePlural:UpldFile.entityNamePlural]
    }

    def list2(Integer max) {
      flash.message = 'Archivo se subió exitosamente !'
      params.max = Math.min(max ?:8, 100)
      [maxDisplay:params.max, upldFileInstanceList: UpldFile.list(params), upldFileInstanceTotal: UpldFile.count(),entityName:UpldFile.entityName,entityNamePlural:UpldFile.entityNamePlural]
    }

    def create() {
      [upldFileInstance: new UpldFile(params),entityName:UpldFile.entityName,entityNamePlural:UpldFile.entityNamePlural]
    }

    def save() {
      def upldFileInstance = new UpldFile(params)
      if (!upldFileInstance.save(flush: true)) {
        render(view: "create", model: [upldFileInstance: upldFileInstance,entityName:UpldFile.entityName,entityNamePlural:UpldFile.entityNamePlural])
        return
      }

      String descripcion = "'${upldFileInstance.toString()}'"
      flash.message = message(code: 'default.created.message', args: [message(code: 'upldFile.label', default: 'UpldFile'), descripcion])
      redirect(action: "show", id: upldFileInstance.id)
    }

    def show(Long id) {
      def upldFileInstance = UpldFile.get(id)
      if (!upldFileInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'upldFile.label', default: 'UpldFile'), id])
        redirect(action: "list")
        return
      }

      [upldFileInstance: upldFileInstance,entityName:UpldFile.entityName,entityNamePlural:UpldFile.entityNamePlural]
    }

    def edit(Long id) {
        def upldFileInstance = UpldFile.get(id)
        if (!upldFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'upldFile.label', default: 'UpldFile'), id])
            redirect(action: "list")
            return
        }

        [upldFileInstance: upldFileInstance,entityName:UpldFile.entityName,entityNamePlural:UpldFile.entityNamePlural]
    }

    def update(Long id, Long version) {
        def upldFileInstance = UpldFile.get(id)
        if (!upldFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'upldFile.label', default: 'UpldFile'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (upldFileInstance.version > version) {
                upldFileInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'upldFile.label', default: 'UpldFile')] as Object[],
                          "Another user has updated this UpldFile while you were editing")
                render(view: "edit", model: [upldFileInstance: upldFileInstance])
                return
            }
        }

        upldFileInstance.properties = params

        if (!upldFileInstance.save(flush: true)) {
            render(view: "edit", model: [upldFileInstance: upldFileInstance])
            return
        }

        String descripcion = "'${upldFileInstance.toString()}'"
        flash.message = message(code: 'default.updated.message', args: [message(code: 'upldFile.label', default: 'UpldFile'), descripcion])
        redirect(action: "show", id: upldFileInstance.id,entityName:UpldFile.entityName,entityNamePlural:UpldFile.entityNamePlural)
    }

    def delete(Long id) {
      def upldFileInstance = UpldFile.get(id)
      if (!upldFileInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'upldFile.label', default: 'UpldFile'), id])
        redirect(action: "list")
        return
      }

      String descripcion = "'${upldFileInstance.toString()}'"
      try {
        upldFileInstance.delete(flush: true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'upldFile.label', default: 'UpldFile'), descripcion])
        redirect(action: "list")
      }
      catch (DataIntegrityViolationException e) {
        flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'upldFile.label', default: 'UpldFile'), id])
        redirect(action: "show", id: id)
      }
    }

  def uploadFile = {
    // TODO: algo raro
    def auth = springSecurityService.getCurrentUser()
    def props = auth.getProperties()
    accesus.Usuario usuario = accesus.Usuario.findByUsername(props['username'])

    String fileName = UFile.get(params.ufileId).path
    String name = UFile.get(params.ufileId).name
    String[] namesplited = name.split("-")

    if(new File(fileName).exists()) {
      base.UpldFile upldFile = base.UpldFile.findByNombre(name)
      if(!upldFile) {
        upldFile = new  base.UpldFile()
      } else {
        upldFile.nota = 'vuelto a subir'
      }
      upldFile.path = fileName
      upldFile.nombre = name
      upldFile.fechaCarga = new Date()
      upldFile.subido = false
      upldFile.cargado = false
      upldFile.falla = false
      upldFile.usuario = usuario?.username
      upldFile.save(flush: true)
    }

    redirect(action: "list")
    return
  }

  def showData = {
    def line = 1
    params.max = 20
    params.offset = (params.offset)?(params.offset as int):0
    def strsql = "select * from DatoHMG where file.id = ${params['id']}}"
    def lista = DatoHMG.executeQuery("select d from DatoHMG d where d.file.id = ${params['id']}",[max:params.max,offset:params.offset])
    def contador = DatoHMG.executeQuery("select count(d) from DatoHMG d where d.file.id = ${params['id']}")
    render template: "fileData", model:[datoHMGInstanceList: lista, datoHMGInstanceTotal: contador[0], maxDisplay: params.max,file: UpldFile.get(params['id'])  ]
  }

  def deleteData = {
    def fileid = params['id'] as long
    base.DatoHMG.executeUpdate("delete base.DatoHMG d where d.file.id = :fileid", [fileid:fileid])
    base.UpldFile.get(fileid)?.delete()
    redirect action: "list"
  }

  def uploadFileError = {
    flash.message = "Error en subida masiva de datos, archivo nó valido"
    redirect action: "list"
    return
  }

  def generateXls ={
    println "generateXls . . ."
  }

  def genTemplate4Totals = {
    def headers = ['Año','Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic']
    params.from = 1961
    params.from = 2014
    def xlsx = new WebXlsxExporter('/senagua/templates/TOT.xlsx')
    xlsx.with {
      setResponseHeaders(response)
      fillHeader(headers)
      save(response.outputStream)
    }

    redirect action: "list"
  }

}
