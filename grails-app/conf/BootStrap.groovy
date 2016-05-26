import accesus.Rol
import accesus.Usuario
import accesus.UsuarioRol

class BootStrap {

  def init = { servletContext ->
    base.Dominio.fillData()
    base.TipoEstacion.fillData()
    base.EstadoEstacion.fillData()
    base.Institucion.fillData()
    base.Pohm.fillData()
    base.TipoRelleno.fillData()
    base.Frecuencia.fillData()
    base.Estadistica.fillData()
    base.Parametro.fillData()
    base.Operativo.fillData()
    base.Dateref.fillData()

    configAccess()
  }

  def configAccess() {
    configureRol('ROLE_ADMIN','Administrador')
    configureRol('ROLE_PUBLICO','Público')
    configureRol('ROLE_DATALOADER','Carga de Datos')
    configureUsuario('admin','SnagUa','ROLE_ADMIN',false,'SENAGUA','Generado por el sistema')
    configureUsuario('publico','publico','ROLE_PUBLICO',false,'SENAGUA','Generado por el sistema')
    configureUsuario('público','publico','ROLE_PUBLICO',false,'SENAGUA','Generado por el sistema')
    configureUsuario('senagua','SnagUa','ROLE_DATALOADER',true,'SENAGUA','Generado por el sistema')
  }

  def configureRol(rol, descripcion) {
    if(!Rol.findByAuthority(rol))
      new Rol(authority: rol, description: descripcion).save(flush: true)
  }

  def configureUsuario(usuario,passwd,authority,individual,institucion,descripcion) {
    Rol rol = Rol.findByAuthority(authority)
    if(!Usuario.findByUsername(usuario) && rol)      {
      new Usuario(username: usuario,
                  rol: rol,
                  enabled: true,
                  password: passwd,
                  individual: individual,
                  institucion: base.Institucion.findByCodigo('SENAGUA'),
                  descripcion:descripcion).save(flush: true)
    }
    configureUsuarioRol(usuario,authority)
  }

  def configureUsuarioRol(usuario,autoridad) {
    Rol myRol = Rol.findByAuthority(autoridad)
    Usuario myUsuario = Usuario.findByUsername(usuario)

    if(myUsuario && myRol) {
      UsuarioRol usuarioRol = UsuarioRol.findByUsuarioAndRol(myUsuario,myRol)
      if(!usuarioRol) {
        usuarioRol = new UsuarioRol()
        usuarioRol.usuario = myUsuario
        usuarioRol.rol = myRol
        usuarioRol.save(flush: true)
        // println "----> ${usuarioRol.usuario} (${usuarioRol.usuario}"
      }
    }
  }

  def destroy = {

  }

}
