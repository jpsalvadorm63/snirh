package base

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class EstadoEstacionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [maxDisplay:params.max, estadoEstacionInstanceList: EstadoEstacion.list(params), estadoEstacionInstanceTotal: EstadoEstacion.count(),entityName:EstadoEstacion.entityName,entityNamePlural:EstadoEstacion.entityNamePlural]
    }


    def create() {
        [estadoEstacionInstance: new EstadoEstacion(params),entityName:EstadoEstacion.entityName,entityNamePlural:EstadoEstacion.entityNamePlural]
    }

    def save() {
        def estadoEstacionInstance = new EstadoEstacion(params)
        if (!estadoEstacionInstance.save(flush: true)) {
            render(view: "create", model: [estadoEstacionInstance: estadoEstacionInstance,entityName:EstadoEstacion.entityName,entityNamePlural:EstadoEstacion.entityNamePlural])
            return
        }

        String descripcion = "'${estadoEstacionInstance.toString()}'"
        flash.message = message(code: 'default.created.message', args: [message(code: 'estadoEstacion.label', default: 'EstadoEstacion'), descripcion])
        redirect(action: "show", id: estadoEstacionInstance.id)
    }

    def show(Long id) {
        def estadoEstacionInstance = EstadoEstacion.get(id)
        if (!estadoEstacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoEstacion.label', default: 'EstadoEstacion'), id])
            redirect(action: "list")
            return
        }

        [estadoEstacionInstance: estadoEstacionInstance,entityName:EstadoEstacion.entityName,entityNamePlural:EstadoEstacion.entityNamePlural]
    }

    def edit(Long id) {
        def estadoEstacionInstance = EstadoEstacion.get(id)
        if (!estadoEstacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoEstacion.label', default: 'EstadoEstacion'), id])
            redirect(action: "list")
            return
        }

        [estadoEstacionInstance: estadoEstacionInstance,entityName:EstadoEstacion.entityName,entityNamePlural:EstadoEstacion.entityNamePlural]
    }

    def update(Long id, Long version) {
        def estadoEstacionInstance = EstadoEstacion.get(id)
        if (!estadoEstacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoEstacion.label', default: 'EstadoEstacion'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (estadoEstacionInstance.version > version) {
                estadoEstacionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'estadoEstacion.label', default: 'EstadoEstacion')] as Object[],
                          "Another user has updated this EstadoEstacion while you were editing")
                render(view: "edit", model: [estadoEstacionInstance: estadoEstacionInstance])
                return
            }
        }

        estadoEstacionInstance.properties = params

        if (!estadoEstacionInstance.save(flush: true)) {
            render(view: "edit", model: [estadoEstacionInstance: estadoEstacionInstance])
            return
        }

        String descripcion = "'${estadoEstacionInstance.toString()}'"
        flash.message = message(code: 'default.updated.message', args: [message(code: 'estadoEstacion.label', default: 'EstadoEstacion'), descripcion])
        redirect(action: "show", id: estadoEstacionInstance.id,entityName:EstadoEstacion.entityName,entityNamePlural:EstadoEstacion.entityNamePlural)
    }

    def delete(Long id) {
        def estadoEstacionInstance = EstadoEstacion.get(id)
        if (!estadoEstacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadoEstacion.label', default: 'EstadoEstacion'), id])
            redirect(action: "list")
            return
        }

        String descripcion = "'${estadoEstacionInstance.toString()}'"
        try {
            estadoEstacionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'estadoEstacion.label', default: 'EstadoEstacion'), descripcion])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'estadoEstacion.label', default: 'EstadoEstacion'), id])
            redirect(action: "show", id: id)
        }
    }

}
