package base

import org.springframework.dao.DataIntegrityViolationException
import com.lucastex.grails.fileuploader.UFile
import grails.plugins.springsecurity.Secured

class PohmController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
  static fileUploderConfig = 'estaciones'

  def index() {
    session.pohmDominio = null
    session.pohmTipoEstacion = null

    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 16, 100)
    params.offset = (params.offset)?params.offset:0
    params.sort = (params.sort)?params.sort:"nombre"
    params.order = (params.order)?params.order:"asc"

    def lista = null
    def contador = 0
    if( (session.pohmDominio != null) || (session.pohmTipoEstacion != null) ) {

      String sql = "from base.Pohm"
      if( (session.pohmDominio != null) && (session.pohmTipoEstacion != null) )
        sql += " where dominio.id = ${session.pohmDominio.id} and tipoEstacion.id = ${session.pohmTipoEstacion.id}"
      else
      if(session.pohmDominio != null)
        sql += " where dominio.id = ${session.pohmDominio.id}"
      else
      if(session.pohmTipoEstacion != null)
        sql += " where tipoEstacion.id = ${session.pohmTipoEstacion.id}"
      lista = Pohm.findAll(sql,[max:params.max, offset:params.offset])
      contador = (Pohm.findAll(sql)).size()
    } else {
      lista = Pohm.list(params)
      contador = Pohm.count()
    }


    [maxDisplay: params.max, pohmInstanceList: lista, pohmInstanceTotal: contador, entityName: Pohm.entityName, entityNamePlural: Pohm.entityNamePlural, fileUploderConfig:fileUploderConfig]
  }

  def create() {
    [pohmInstance: new Pohm(params), entityName: Pohm.entityName, entityNamePlural: Pohm.entityNamePlural]
  }

  def save() {
    def pohmInstance = new Pohm(params)
    if (!pohmInstance.save(flush: true)) {
      render(view: "create", model: [pohmInstance: pohmInstance, entityName: Pohm.entityName, entityNamePlural: Pohm.entityNamePlural])
      return
    }
    String descripcion = "'${pohmInstance.toString()}'"
    flash.message = message(code: 'default.created.message', args: [message(code: 'pohm.label', default: 'Pohm'), descripcion])
    redirect(action: "show", id: pohmInstance.id)
  }


  def show(Long id) {
    def pohmInstance = Pohm.get(id)
    if (!pohmInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'pohm.label', default: 'Pohm'), id])
      redirect(action: "list")
      return
    }

    [pohmInstance: pohmInstance, entityName: Pohm.entityName, entityNamePlural: Pohm.entityNamePlural]
  }

  def edit(Long id) {
    def pohmInstance = Pohm.get(id)
    if (!pohmInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'pohm.label', default: 'Pohm'), id])
      redirect(action: "list")
      return
    }

    [pohmInstance: pohmInstance, entityName: Pohm.entityName, entityNamePlural: Pohm.entityNamePlural]
  }

  def update(Long id, Long version) {
    def pohmInstance = Pohm.get(id)
    if (!pohmInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'pohm.label', default: 'Pohm'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (pohmInstance.version > version) {
        pohmInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
            [message(code: 'pohm.label', default: 'Pohm')] as Object[],
            "Another user has updated this Pohm while you were editing")
        render(view: "edit", model: [pohmInstance: pohmInstance])
        return
      }
    }
    pohmInstance.properties = params

    if (!pohmInstance.save(flush: true)) {
      render(view: "edit", model: [pohmInstance: pohmInstance])
      return
    }

    String descripcion = "'${pohmInstance.toString()}'"
    flash.message = message(code: 'default.updated.message', args: [message(code: 'pohm.label', default: 'Pohm'), descripcion])
    redirect(action: "show", id: pohmInstance.id, entityName: Pohm.entityName, entityNamePlural: Pohm.entityNamePlural)
  }

  def delete(Long id) {
    def pohmInstance = Pohm.get(id)
    if (!pohmInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'pohm.label', default: 'Pohm'), id])
      redirect(action: "list")
      return
    }

    String descripcion = "'${pohmInstance.toString()}'"
    try {
      pohmInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'pohm.label', default: 'Pohm'), descripcion])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pohm.label', default: 'Pohm'), id])
      redirect(action: "show", id: id)
    }
  }

  def uploadFile = {
    def fileName = UFile.get(params.ufileId).path
    if(new File(fileName).exists()) {
      new ExcelBuilderService(fileName,'estaciones').eachLine([labels:true]) {
        def values = [:]
        values << [dominio:"${cell(0)}"]
        values << [institucion:"${cell(1)}"]
        values << [codigo:"${cell(2)}"]
        values << [nombre:"${cell(3)}"]
        values << [tipoEstacion:"${cell(4)}"]
        values << [operativo:"${cell(5)}"]
        values << [estadoEstacion:"${cell(6)}"]
        values << [latitud:"${cell(7)}"]
        values << [longitud:"${cell(8)}"]
        values << [elevacion:"${cell(9)}"]
        values << [x:"${cell(10)}"]
        values << [y:"${cell(11)}"]
        base.Pohm.fillData(values)

        println " ${it.rowNum} = $codigo"
      }
    }
    redirect(action: "list")
    return
  }

  def uploadFileError = {
    flash.message = "Error en subida masiva de datos, archivo nó valido"
    redirect(action: "list")
    return
  }

  def selDominio = {
    session.pohmDominio = (params.id != '*') ? Dominio.get(params.id) : null
    session.pohmTipoEstacion = null
    if(session.pohmDominio != null) {
      def tipoEstacionList = (session.pohmDominio != null)?TipoEstacion.findAllByDominio(session.pohmDominio):TipoEstacion.list()
      // render "<label for='selTipoEstacion'>Por tipo de estación:</label>" + g.select(optionKey: 'id', from: tipoEstacionList, id: 'selTipoEstaciòn', name: "tipoEstacion", noSelection:['':'Todo tipo de estación'])
      String str = '<option value="">Todo tipo de estación</option>'
      tipoEstacionList.each {
        str += "<option value='${it.id}'>${it.tipoEstacion}</option>"
      }
      render str
    } else {
      render ''
    }
  }

  def selTipoEstacion = {
    session.pohmTipoEstacion = (params.id != '') ? TipoEstacion.get(params.id) : null
  }

  def filtrar = {
    redirect(action: "list", params: params)
  }

}
