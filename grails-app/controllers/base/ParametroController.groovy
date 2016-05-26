package base

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class ParametroController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    [maxDisplay: params.max, parametroInstanceList: Parametro.list(params), parametroInstanceTotal: Parametro.count(), entityName: Parametro.entityName, entityNamePlural: Parametro.entityNamePlural]
  }

  def create() {
    [parametroInstance: new Parametro(params), entityName: Parametro.entityName, entityNamePlural: Parametro.entityNamePlural]
  }

  def save() {
    def parametroInstance = new Parametro(params)
    if (!parametroInstance.save(flush: true)) {
      render(view: "create", model: [parametroInstance: parametroInstance, entityName: Parametro.entityName, entityNamePlural: Parametro.entityNamePlural])
      return
    }

    String descripcion = "'${parametroInstance.toString()}'"
    flash.message = message(code: 'default.created.message', args: [message(code: 'parametro.label', default: 'Parametro'), descripcion])
    redirect(action: "show", id: parametroInstance.id)
  }

  def show(Long id) {
    def parametroInstance = Parametro.get(id)
    if (!parametroInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'parametro.label', default: 'Parametro'), id])
      redirect(action: "list")
      return
    }

    [parametroInstance: parametroInstance, entityName: Parametro.entityName, entityNamePlural: Parametro.entityNamePlural]
  }

  def edit(Long id) {
    def parametroInstance = Parametro.get(id)
    if (!parametroInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'parametro.label', default: 'Parametro'), id])
      redirect(action: "list")
      return
    }

    [parametroInstance: parametroInstance, entityName: Parametro.entityName, entityNamePlural: Parametro.entityNamePlural]
  }

  def update(Long id, Long version) {
    def parametroInstance = Parametro.get(id)
    if (!parametroInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'parametro.label', default: 'Parametro'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (parametroInstance.version > version) {
        parametroInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
            [message(code: 'parametro.label', default: 'Parametro')] as Object[],
            "Another user has updated this Parametro while you were editing")
        render(view: "edit", model: [parametroInstance: parametroInstance])
        return
      }
    }

    parametroInstance.properties = params

    if (!parametroInstance.save(flush: true)) {
      render(view: "edit", model: [parametroInstance: parametroInstance])
      return
    }

    String descripcion = "'${parametroInstance.toString()}'"
    flash.message = message(code: 'default.updated.message', args: [message(code: 'parametro.label', default: 'Parametro'), descripcion])
    redirect(action: "show", id: parametroInstance.id, entityName: Parametro.entityName, entityNamePlural: Parametro.entityNamePlural)
  }

  def delete(Long id) {
    def parametroInstance = Parametro.get(id)
    if (!parametroInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'parametro.label', default: 'Parametro'), id])
      redirect(action: "list")
      return
    }

    String descripcion = "'${parametroInstance.toString()}'"
    try {
      parametroInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'parametro.label', default: 'Parametro'), descripcion])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'parametro.label', default: 'Parametro'), id])
      redirect(action: "show", id: id)
    }
  }
}
