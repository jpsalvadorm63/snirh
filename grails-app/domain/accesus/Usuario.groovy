package accesus

class Usuario {

	transient springSecurityService

	String  username
	String  password
  String  mail
  Rol     rol
  base.Institucion institucion
  String descripcion
  boolean individual
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired


  static entityName = "Usuario"
  static entityNamePlural = "Usuarios"

	static constraints = {
		username blank: false, unique: true
		password blank: false
    mail     nullable:true, blank:true, size:0..64, email:true
    individual nullable:false, default:true
    descripcion nullable:true, blank: true, maxSize: 64
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol } as Set
	}

	def beforeInsert() {
    encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

}
