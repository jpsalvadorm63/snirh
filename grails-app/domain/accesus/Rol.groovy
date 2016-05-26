package accesus

class Rol {

	String authority
  String description

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
    description blank: false,  unique: true, maxSize: 32
	}

  String toString() {
    return description
  }

}
