package base

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class TipoRellenoController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    [maxDisplay: params.max, tipoRellenoInstanceList: TipoRelleno.list(params), tipoRellenoInstanceTotal: TipoRelleno.count(), entityName: TipoRelleno.entityName, entityNamePlural: TipoRelleno.entityNamePlural]
  }

  def create() {
    [tipoRellenoInstance: new TipoRelleno(params), entityName: TipoRelleno.entityName, entityNamePlural: TipoRelleno.entityNamePlural]
  }

  def save() {
    def tipoRellenoInstance = new TipoRelleno(params)
    if (!tipoRellenoInstance.save(flush: true)) {
      render(view: "create", model: [tipoRellenoInstance: tipoRellenoInstance, entityName: TipoRelleno.entityName, entityNamePlural: TipoRelleno.entityNamePlural])
      return
    }

    String descripcion = "'${tipoRellenoInstance.toString()}'"
    flash.message = message(code: 'default.created.message', args: [message(code: 'tipoRelleno.label', default: 'TipoRelleno'), descripcion])
    redirect(action: "show", id: tipoRellenoInstance.id)
  }

  def show(Long id) {
    def tipoRellenoInstance = TipoRelleno.get(id)
    if (!tipoRellenoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoRelleno.label', default: 'TipoRelleno'), id])
      redirect(action: "list")
      return
    }

    [tipoRellenoInstance: tipoRellenoInstance, entityName: TipoRelleno.entityName, entityNamePlural: TipoRelleno.entityNamePlural]
  }

  def edit(Long id) {
    def tipoRellenoInstance = TipoRelleno.get(id)
    if (!tipoRellenoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoRelleno.label', default: 'TipoRelleno'), id])
      redirect(action: "list")
      return
    }

    [tipoRellenoInstance: tipoRellenoInstance, entityName: TipoRelleno.entityName, entityNamePlural: TipoRelleno.entityNamePlural]
  }

  def update(Long id, Long version) {
    def tipoRellenoInstance = TipoRelleno.get(id)
    if (!tipoRellenoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoRelleno.label', default: 'TipoRelleno'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (tipoRellenoInstance.version > version) {
        tipoRellenoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
            [message(code: 'tipoRelleno.label', default: 'TipoRelleno')] as Object[],
            "Another user has updated this TipoRelleno while you were editing")
        render(view: "edit", model: [tipoRellenoInstance: tipoRellenoInstance])
        return
      }
    }

    tipoRellenoInstance.properties = params

    if (!tipoRellenoInstance.save(flush: true)) {
      render(view: "edit", model: [tipoRellenoInstance: tipoRellenoInstance])
      return
    }

    String descripcion = "'${tipoRellenoInstance.toString()}'"
    flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoRelleno.label', default: 'TipoRelleno'), descripcion])
    redirect(action: "show", id: tipoRellenoInstance.id, entityName: TipoRelleno.entityName, entityNamePlural: TipoRelleno.entityNamePlural)
  }

  def delete(Long id) {
    def tipoRellenoInstance = TipoRelleno.get(id)
    if (!tipoRellenoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoRelleno.label', default: 'TipoRelleno'), id])
      redirect(action: "list")
      return
    }

    String descripcion = "'${tipoRellenoInstance.toString()}'"
    try {
      tipoRellenoInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoRelleno.label', default: 'TipoRelleno'), descripcion])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoRelleno.label', default: 'TipoRelleno'), id])
      redirect(action: "show", id: id)
    }
  }
}
