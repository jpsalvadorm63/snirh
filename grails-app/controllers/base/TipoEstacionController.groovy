package base

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class TipoEstacionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [maxDisplay:params.max, tipoEstacionInstanceList: TipoEstacion.list(params), tipoEstacionInstanceTotal: TipoEstacion.count(),entityName:TipoEstacion.entityName,entityNamePlural:TipoEstacion.entityNamePlural]
    }

    def create() {
        [tipoEstacionInstance: new TipoEstacion(params),entityName:TipoEstacion.entityName,entityNamePlural:TipoEstacion.entityNamePlural]
    }

    def save() {
        def tipoEstacionInstance = new TipoEstacion(params)
        if (!tipoEstacionInstance.save(flush: true)) {
            render(view: "create", model: [tipoEstacionInstance: tipoEstacionInstance,entityName:TipoEstacion.entityName,entityNamePlural:TipoEstacion.entityNamePlural])
            return
        }

        String descripcion = "'${tipoEstacionInstance.toString()}'"
        flash.message = message(code: 'default.created.message', args: [message(code: 'tipoEstacion.label', default: 'TipoEstacion'), descripcion])
        redirect(action: "show", id: tipoEstacionInstance.id)
    }

    def show(Long id) {
        def tipoEstacionInstance = TipoEstacion.get(id)
        if (!tipoEstacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEstacion.label', default: 'TipoEstacion'), id])
            redirect(action: "list")
            return
        }

        [tipoEstacionInstance: tipoEstacionInstance,entityName:TipoEstacion.entityName,entityNamePlural:TipoEstacion.entityNamePlural]
    }

    def edit(Long id) {
        def tipoEstacionInstance = TipoEstacion.get(id)
        if (!tipoEstacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEstacion.label', default: 'TipoEstacion'), id])
            redirect(action: "list")
            return
        }

        [tipoEstacionInstance: tipoEstacionInstance,entityName:TipoEstacion.entityName,entityNamePlural:TipoEstacion.entityNamePlural]
    }

    def update(Long id, Long version) {
        def tipoEstacionInstance = TipoEstacion.get(id)
        if (!tipoEstacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEstacion.label', default: 'TipoEstacion'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tipoEstacionInstance.version > version) {
                tipoEstacionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoEstacion.label', default: 'TipoEstacion')] as Object[],
                          "Another user has updated this TipoEstacion while you were editing")
                render(view: "edit", model: [tipoEstacionInstance: tipoEstacionInstance])
                return
            }
        }

        tipoEstacionInstance.properties = params

        if (!tipoEstacionInstance.save(flush: true)) {
            render(view: "edit", model: [tipoEstacionInstance: tipoEstacionInstance])
            return
        }

        String descripcion = "'${tipoEstacionInstance.toString()}'"
        flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoEstacion.label', default: 'TipoEstacion'), descripcion])
        redirect(action: "show", id: tipoEstacionInstance.id,entityName:TipoEstacion.entityName,entityNamePlural:TipoEstacion.entityNamePlural)
    }

    def delete(Long id) {
        def tipoEstacionInstance = TipoEstacion.get(id)
        if (!tipoEstacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEstacion.label', default: 'TipoEstacion'), id])
            redirect(action: "list")
            return
        }

        String descripcion = "'${tipoEstacionInstance.toString()}'"
        try {
            tipoEstacionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoEstacion.label', default: 'TipoEstacion'), descripcion])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoEstacion.label', default: 'TipoEstacion'), id])
            redirect(action: "show", id: id)
        }
    }
}
