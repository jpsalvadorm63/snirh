package base

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class EstadisticaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [maxDisplay:params.max, estadisticaInstanceList: Estadistica.list(params), estadisticaInstanceTotal: Estadistica.count(),entityName:Estadistica.entityName,entityNamePlural:Estadistica.entityNamePlural]
    }

    def create() {
        [estadisticaInstance: new Estadistica(params),entityName:Estadistica.entityName,entityNamePlural:Estadistica.entityNamePlural]
    }

    def save() {
        def estadisticaInstance = new Estadistica(params)
        if (!estadisticaInstance.save(flush: true)) {
            render(view: "create", model: [estadisticaInstance: estadisticaInstance,entityName:Estadistica.entityName,entityNamePlural:Estadistica.entityNamePlural])
            return
        }

        String descripcion = "'${estadisticaInstance.toString()}'"
        flash.message = message(code: 'default.created.message', args: [message(code: 'estadistica.label', default: 'Estadistica'), descripcion])
        redirect(action: "show", id: estadisticaInstance.id)
    }

    def show(Long id) {
        def estadisticaInstance = Estadistica.get(id)
        if (!estadisticaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadistica.label', default: 'Estadistica'), id])
            redirect(action: "list")
            return
        }

        [estadisticaInstance: estadisticaInstance,entityName:Estadistica.entityName,entityNamePlural:Estadistica.entityNamePlural]
    }

    def edit(Long id) {
        def estadisticaInstance = Estadistica.get(id)
        if (!estadisticaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadistica.label', default: 'Estadistica'), id])
            redirect(action: "list")
            return
        }

        [estadisticaInstance: estadisticaInstance,entityName:Estadistica.entityName,entityNamePlural:Estadistica.entityNamePlural]
    }

    def update(Long id, Long version) {
        def estadisticaInstance = Estadistica.get(id)
        if (!estadisticaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadistica.label', default: 'Estadistica'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (estadisticaInstance.version > version) {
                estadisticaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'estadistica.label', default: 'Estadistica')] as Object[],
                          "Another user has updated this Estadistica while you were editing")
                render(view: "edit", model: [estadisticaInstance: estadisticaInstance])
                return
            }
        }

        estadisticaInstance.properties = params

        if (!estadisticaInstance.save(flush: true)) {
            render(view: "edit", model: [estadisticaInstance: estadisticaInstance])
            return
        }

        String descripcion = "'${estadisticaInstance.toString()}'"
        flash.message = message(code: 'default.updated.message', args: [message(code: 'estadistica.label', default: 'Estadistica'), descripcion])
        redirect(action: "show", id: estadisticaInstance.id,entityName:Estadistica.entityName,entityNamePlural:Estadistica.entityNamePlural)
    }

    def delete(Long id) {
        def estadisticaInstance = Estadistica.get(id)
        if (!estadisticaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estadistica.label', default: 'Estadistica'), id])
            redirect(action: "list")
            return
        }

        String descripcion = "'${estadisticaInstance.toString()}'"
        try {
            estadisticaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'estadistica.label', default: 'Estadistica'), descripcion])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'estadistica.label', default: 'Estadistica'), id])
            redirect(action: "show", id: id)
        }
    }
}
