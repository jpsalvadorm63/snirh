import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
    try {
      session.invalidate()
    } catch (ex) {
      println ex.message
    }

    redirect controller:'login', action: 'auth'
	}

}
