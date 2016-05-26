package base

import org.springframework.dao.DataIntegrityViolationException

class InstitucionController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    [maxDisplay: params.max, institucionInstanceList: Institucion.list(params), institucionInstanceTotal: Institucion.count(), entityName: Institucion.entityName, entityNamePlural: Institucion.entityNamePlural]
  }

  def create() {
    [institucionInstance: new Institucion(params), entityName: Institucion.entityName, entityNamePlural: Institucion.entityNamePlural]
  }

  def save() {
    def institucionInstance = new Institucion(params)
    if (!institucionInstance.save(flush: true)) {
      render(view: "create", model: [institucionInstance: institucionInstance, entityName: Institucion.entityName, entityNamePlural: Institucion.entityNamePlural])
      return
    }

    String descripcion = "'${institucionInstance.toString()}'"
    flash.message = message(code: 'default.created.message', args: [message(code: 'institucion.label', default: 'Institucion'), descripcion])
    redirect(action: "show", id: institucionInstance.id)
  }

  def show(Long id) {
    def institucionInstance = Institucion.get(id)
    if (!institucionInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'institucion.label', default: 'Institucion'), id])
      redirect(action: "list")
      return
    }

    [institucionInstance: institucionInstance, entityName: Institucion.entityName, entityNamePlural: Institucion.entityNamePlural]
  }

  def edit(Long id) {
    def institucionInstance = Institucion.get(id)
    if (!institucionInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'institucion.label', default: 'Institucion'), id])
      redirect(action: "list")
      return
    }

    [institucionInstance: institucionInstance, entityName: Institucion.entityName, entityNamePlural: Institucion.entityNamePlural]
  }

  def update(Long id, Long version) {
    def institucionInstance = Institucion.get(id)
    if (!institucionInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'institucion.label', default: 'Institucion'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (institucionInstance.version > version) {
        institucionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
            [message(code: 'institucion.label', default: 'Institucion')] as Object[],
            "Another user has updated this Institucion while you were editing")
        render(view: "edit", model: [institucionInstance: institucionInstance])
        return
      }
    }

    institucionInstance.properties = params

    if (!institucionInstance.save(flush: true)) {
      render(view: "edit", model: [institucionInstance: institucionInstance])
      return
    }

    String descripcion = "'${institucionInstance.toString()}'"
    flash.message = message(code: 'default.updated.message', args: [message(code: 'institucion.label', default: 'Institucion'), descripcion])
    redirect(action: "show", id: institucionInstance.id, entityName: Institucion.entityName, entityNamePlural: Institucion.entityNamePlural)
  }

  def delete(Long id) {
    def institucionInstance = Institucion.get(id)
    if (!institucionInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'institucion.label', default: 'Institucion'), id])
      redirect(action: "list")
      return
    }

    String descripcion = "'${institucionInstance.toString()}'"
    try {
      institucionInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'institucion.label', default: 'Institucion'), descripcion])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'institucion.label', default: 'Institucion'), id])
      redirect(action: "show", id: id)
    }
  }
}
