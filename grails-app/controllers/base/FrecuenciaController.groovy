package base

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class FrecuenciaController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    [maxDisplay: params.max, frecuenciaInstanceList: Frecuencia.list(params), frecuenciaInstanceTotal: Frecuencia.count(), entityName: Frecuencia.entityName, entityNamePlural: Frecuencia.entityNamePlural]
  }

  def create() {
    [frecuenciaInstance: new Frecuencia(params), entityName: Frecuencia.entityName, entityNamePlural: Frecuencia.entityNamePlural]
  }

  def save() {
    def frecuenciaInstance = new Frecuencia(params)
    if (!frecuenciaInstance.save(flush: true)) {
      render(view: "create", model: [frecuenciaInstance: frecuenciaInstance, entityName: Frecuencia.entityName, entityNamePlural: Frecuencia.entityNamePlural])
      return
    }

    String descripcion = "'${frecuenciaInstance.toString()}'"
    flash.message = message(code: 'default.created.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), descripcion])
    redirect(action: "show", id: frecuenciaInstance.id)
  }

  def show(Long id) {
    def frecuenciaInstance = Frecuencia.get(id)
    if (!frecuenciaInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
      redirect(action: "list")
      return
    }

    [frecuenciaInstance: frecuenciaInstance, entityName: Frecuencia.entityName, entityNamePlural: Frecuencia.entityNamePlural]
  }

  def edit(Long id) {
    def frecuenciaInstance = Frecuencia.get(id)
    if (!frecuenciaInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
      redirect(action: "list")
      return
    }

    [frecuenciaInstance: frecuenciaInstance, entityName: Frecuencia.entityName, entityNamePlural: Frecuencia.entityNamePlural]
  }

  def update(Long id, Long version) {
    def frecuenciaInstance = Frecuencia.get(id)
    if (!frecuenciaInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (frecuenciaInstance.version > version) {
        frecuenciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
            [message(code: 'frecuencia.label', default: 'Frecuencia')] as Object[],
            "Another user has updated this Frecuencia while you were editing")
        render(view: "edit", model: [frecuenciaInstance: frecuenciaInstance])
        return
      }
    }

    frecuenciaInstance.properties = params

    if (!frecuenciaInstance.save(flush: true)) {
      render(view: "edit", model: [frecuenciaInstance: frecuenciaInstance])
      return
    }

    String descripcion = "'${frecuenciaInstance.toString()}'"
    flash.message = message(code: 'default.updated.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), descripcion])
    redirect(action: "show", id: frecuenciaInstance.id, entityName: Frecuencia.entityName, entityNamePlural: Frecuencia.entityNamePlural)
  }

  def delete(Long id) {
    def frecuenciaInstance = Frecuencia.get(id)
    if (!frecuenciaInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
      redirect(action: "list")
      return
    }

    String descripcion = "'${frecuenciaInstance.toString()}'"
    try {
      frecuenciaInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), descripcion])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
      redirect(action: "show", id: id)
    }
  }
}
