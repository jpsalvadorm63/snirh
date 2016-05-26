package base

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class OperativoController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    [maxDisplay: params.max, operativoInstanceList: Operativo.list(params), operativoInstanceTotal: Operativo.count(), entityName: Operativo.entityName, entityNamePlural: Operativo.entityNamePlural]
  }

  def create() {
    [operativoInstance: new Operativo(params), entityName: Operativo.entityName, entityNamePlural: Operativo.entityNamePlural]
  }

  def save() {
    def operativoInstance = new Operativo(params)
    if (!operativoInstance.save(flush: true)) {
      render(view: "create", model: [operativoInstance: operativoInstance, entityName: Operativo.entityName, entityNamePlural: Operativo.entityNamePlural])
      return
    }

    String descripcion = "'${operativoInstance.toString()}'"
    flash.message = message(code: 'default.created.message', args: [message(code: 'operativo.label', default: 'Operativo'), descripcion])
    redirect(action: "show", id: operativoInstance.id)
  }

  def show(Long id) {
    def operativoInstance = Operativo.get(id)
    if (!operativoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'operativo.label', default: 'Operativo'), id])
      redirect(action: "list")
      return
    }

    [operativoInstance: operativoInstance, entityName: Operativo.entityName, entityNamePlural: Operativo.entityNamePlural]
  }

  def edit(Long id) {
    def operativoInstance = Operativo.get(id)
    if (!operativoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'operativo.label', default: 'Operativo'), id])
      redirect(action: "list")
      return
    }

    [operativoInstance: operativoInstance, entityName: Operativo.entityName, entityNamePlural: Operativo.entityNamePlural]
  }

  def update(Long id, Long version) {
    def operativoInstance = Operativo.get(id)
    if (!operativoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'operativo.label', default: 'Operativo'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (operativoInstance.version > version) {
        operativoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
            [message(code: 'operativo.label', default: 'Operativo')] as Object[],
            "Another user has updated this Operativo while you were editing")
        render(view: "edit", model: [operativoInstance: operativoInstance])
        return
      }
    }

    operativoInstance.properties = params

    if (!operativoInstance.save(flush: true)) {
      render(view: "edit", model: [operativoInstance: operativoInstance])
      return
    }

    String descripcion = "'${operativoInstance.toString()}'"
    flash.message = message(code: 'default.updated.message', args: [message(code: 'operativo.label', default: 'Operativo'), descripcion])
    redirect(action: "show", id: operativoInstance.id, entityName: Operativo.entityName, entityNamePlural: Operativo.entityNamePlural)
  }

  def delete(Long id) {
    def operativoInstance = Operativo.get(id)
    if (!operativoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'operativo.label', default: 'Operativo'), id])
      redirect(action: "list")
      return
    }

    String descripcion = "'${operativoInstance.toString()}'"
    try {
      operativoInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'operativo.label', default: 'Operativo'), descripcion])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'operativo.label', default: 'Operativo'), id])
      redirect(action: "show", id: id)
    }
  }
}
