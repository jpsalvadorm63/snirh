package base

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class DominioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [maxDisplay:params.max, dominioInstanceList: Dominio.list(params), dominioInstanceTotal: Dominio.count(),entityName:Dominio.entityName,entityNamePlural:Dominio.entityNamePlural]
    }

    def create() {
        [dominioInstance: new Dominio(params),entityName:Dominio.entityName,entityNamePlural:Dominio.entityNamePlural]
    }

    def save() {
        def dominioInstance = new Dominio(params)
        if (!dominioInstance.save(flush: true)) {
            render(view: "create", model: [dominioInstance: dominioInstance,entityName:Dominio.entityName,entityNamePlural:Dominio.entityNamePlural])
            return
        }

        String descripcion = "'${dominioInstance.toString()}'"
        flash.message = message(code: 'default.created.message', args: [message(code: 'dominio.label', default: 'Dominio'), descripcion])
        redirect(action: "show", id: dominioInstance.id)
    }

    def show(Long id) {
        def dominioInstance = Dominio.get(id)
        if (!dominioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dominio.label', default: 'Dominio'), id])
            redirect(action: "list")
            return
        }

        [dominioInstance: dominioInstance,entityName:Dominio.entityName,entityNamePlural:Dominio.entityNamePlural]
    }

    def edit(Long id) {
        def dominioInstance = Dominio.get(id)
        if (!dominioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dominio.label', default: 'Dominio'), id])
            redirect(action: "list")
            return
        }

        [dominioInstance: dominioInstance,entityName:Dominio.entityName,entityNamePlural:Dominio.entityNamePlural]
    }

    def update(Long id, Long version) {
        def dominioInstance = Dominio.get(id)
        if (!dominioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dominio.label', default: 'Dominio'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (dominioInstance.version > version) {
                dominioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'dominio.label', default: 'Dominio')] as Object[],
                          "Another user has updated this Dominio while you were editing")
                render(view: "edit", model: [dominioInstance: dominioInstance])
                return
            }
        }

        dominioInstance.properties = params

        if (!dominioInstance.save(flush: true)) {
            render(view: "edit", model: [dominioInstance: dominioInstance])
            return
        }

        String descripcion = "'${dominioInstance.toString()}'"
        flash.message = message(code: 'default.updated.message', args: [message(code: 'dominio.label', default: 'Dominio'), descripcion])
        redirect(action: "show", id: dominioInstance.id,entityName:Dominio.entityName,entityNamePlural:Dominio.entityNamePlural)
    }

    def delete(Long id) {
        def dominioInstance = Dominio.get(id)
        if (!dominioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dominio.label', default: 'Dominio'), id])
            redirect(action: "list")
            return
        }

        String descripcion = "'${dominioInstance.toString()}'"
        try {
            dominioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dominio.label', default: 'Dominio'), descripcion])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dominio.label', default: 'Dominio'), id])
            redirect(action: "show", id: id)
        }
    }

}
