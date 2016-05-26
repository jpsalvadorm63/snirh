package accesus

import org.springframework.dao.DataIntegrityViolationException

class UsuarioController {

    def springSecurityService

    def mailService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [maxDisplay:params.max, usuarioInstanceList: Usuario.list(params), usuarioInstanceTotal: Usuario.count(),entityName:Usuario.entityName,entityNamePlural:Usuario.entityNamePlural]
    }

    def create() {
      Usuario newUser = new Usuario(params)
      newUser.individual = true
      newUser.institucion = base.Institucion.findByCodigo('SENAGUA')
      newUser.enabled = true
      newUser.passwordExpired = false
      newUser.accountExpired = false
      newUser.accountLocked = false
      [usuarioInstance: newUser,entityName:Usuario.entityName,entityNamePlural:Usuario.entityNamePlural]
    }

    def save() {
      int ivalor = Math.round(Math.random()*1000000)
      params.password = "${ivalor}"
      def usuarioInstance = new Usuario(params)
      if (!usuarioInstance.save(flush: true)) {
          render(view: "create", model: [usuarioInstance: usuarioInstance,entityName:Usuario.entityName,entityNamePlural:Usuario.entityNamePlural])
          return
      }

      configureUsuarioRol(usuarioInstance.username,usuarioInstance.rol.authority)
      if(usuarioInstance.mail != null && usuarioInstance.mail != '') {
        mailService.sendMail {
          from "noresponda@senagua.gob.ec"
          to "${usuarioInstance.mail}"
          subject "SNIRH - Inscripción de Usuario"
          text """

 Sistema Nacional de Información de Recursos Hídricos
 SENAGUA (c) 2013
 ====================================================\n
        Inscripción de Usuario/a:

        Usuario: ${usuarioInstance.username}
        Rol: ${usuarioInstance.rol}
        Clave generada por el sistema: ${params.password}


        Ingrese su propia clave siguiendo el siguiente enlace:

        ${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}/login/changePass/${usuarioInstance.username}


        Atte\n

        El Administrador del SNIRH\n
"""
        }
      }

      String descripcion = "'${usuarioInstance.username}'"
      flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), descripcion])
      redirect(action: "show", id: usuarioInstance.id)
    }

    def resetPass() {
      def usuarioInstance = Usuario.get(params.id)
      int ivalor = Math.round(Math.random()*1000000)

      params.password = "${ivalor}"
      usuarioInstance.password = params.password
      if (!usuarioInstance.save(flush: true)) {
        render(view: "create", model: [usuarioInstance: usuarioInstance,entityName:Usuario.entityName,entityNamePlural:Usuario.entityNamePlural])
        return
      }

      if(usuarioInstance.mail != null && usuarioInstance.mail != '') {
        mailService.sendMail {
          from "noresponda@senagua.gob.ec"
          to "${usuarioInstance.mail}"
          subject "SNIRH - Inscripción de Usuario"
          text """

   Sistema Nacional de Información de Recursos Hídricos
   SENAGUA (c) 2013
   ====================================================\n
          Notificación de cambio de Clave:

          Usuario: ${usuarioInstance.username}
          Rol: ${usuarioInstance.rol}
          Nueva Clave: ${params.password}


          Ingrese su propia clave siguiendo el siguiente enlace:

          ${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}/login/changePass/${usuarioInstance.username}


          Atte\n

          El Administrador del SNIRH\n
  """

        }
      }

      String descripcion = "'${usuarioInstance.username}'"
      flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), descripcion])
      redirect(action: "show", id: usuarioInstance.id)
    }

        def configureUsuarioRol(usuario,autoridad) {
          Rol myRol = Rol.findByAuthority(autoridad)
          Usuario myUsuario = Usuario.findByUsername(usuario)

          if(myUsuario && myRol && !UsuarioRol.findByUsuarioAndRol(myUsuario,myRol)) {
            UsuarioRol usuarioRol = new UsuarioRol()
            usuarioRol.usuario = myUsuario
            usuarioRol.rol = myRol
            usuarioRol.save(flush: true)
          }
        }

    def show(Long id) {
        def usuarioInstance = Usuario.get(id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "list")
            return
        }

        [usuarioInstance: usuarioInstance,entityName:Usuario.entityName,entityNamePlural:Usuario.entityNamePlural]
    }

    def edit(Long id) {
        def usuarioInstance = Usuario.get(id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "list")
            return
        }

        [usuarioInstance: usuarioInstance,entityName:Usuario.entityName,entityNamePlural:Usuario.entityNamePlural]
    }

    def update(Long id, Long version) {
        def usuarioInstance = Usuario.get(id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "list")
            return
        }
        def oldRol = usuarioInstance.rol

        if (version != null) {
            if (usuarioInstance.version > version) {
                usuarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'usuario.label', default: 'Usuario')] as Object[],
                          "Another user has updated this Usuario while you were editing")
                render(view: "edit", model: [usuarioInstance: usuarioInstance])
                return
            }
        }
        usuarioInstance.properties = params

        if (!usuarioInstance.save(flush: true)) {
            render(view: "edit", model: [usuarioInstance: usuarioInstance])
            return
        }
        def newRol = usuarioInstance.rol

        if(oldRol.id != newRol.id) {
          def ur = UsuarioRol.findByUsuario(usuarioInstance)
          if(ur)
            UsuarioRol.remove ur.usuario, ur.rol, true
          UsuarioRol.create ur.usuario, newRol, true
        }
        String descripcion = "'${usuarioInstance.toString()}'"
        flash.message = message(code: 'default.updated.message', args: [message(code: 'usuario.label', default: 'Usuario'), descripcion])
        redirect(action: "show", id: usuarioInstance.id,entityName:Usuario.entityName,entityNamePlural:Usuario.entityNamePlural)
    }

    def delete(Long id) {
        def usuarioInstance = Usuario.get(id)

        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "list")
            return
        }

        def ur = UsuarioRol.findByUsuario(usuarioInstance)
        if(ur)
          UsuarioRol.remove ur.usuario, ur.rol, true

        String descripcion = "'${usuarioInstance.toString()}'"
        try {
            usuarioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), descripcion])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "show", id: id)
        }
    }
}
