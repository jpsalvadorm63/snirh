import grails.converters.JSON

import javax.servlet.http.HttpServletResponse

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class LoginController {

	/**
	 * Dependency injection for the authenticationTrustResolver.
	 */
	def authenticationTrustResolver

	/**
	 * Dependency injection for the springSecurityService.
	 */
	def springSecurityService

	/**
	 * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
	 */
	def index = {

		if (springSecurityService.isLoggedIn()) {
			redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
		}
		else {
			redirect action: 'auth', params: params
		}

	}

	/**
	 * Show the login page.
	 */
	def auth = {

		def config = SpringSecurityUtils.securityConfig

		if (springSecurityService.isLoggedIn()) {
			redirect uri: config.successHandler.defaultTargetUrl
			return
		}

		String view = 'auth'
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		render view: view, model: [postUrl: postUrl, rememberMeParameter: config.rememberMe.parameter]
	}

	/**
	 * The redirect action for Ajax requests.
	 */
	def authAjax = {
		response.setHeader 'Location', SpringSecurityUtils.securityConfig.auth.ajaxLoginFormUrl
		response.sendError HttpServletResponse.SC_UNAUTHORIZED
	}

	/**
	 * Show denied page.
	 */
	def denied = {
		if (springSecurityService.isLoggedIn() &&
				authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
			redirect action: 'full', params: params
		}
	}

	/**
	 * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
	 */
	def full = {
		def config = SpringSecurityUtils.securityConfig
		render view: 'auth', params: params,
			model: [hasCookie: authenticationTrustResolver.isRememberMe(SCH.context?.authentication),
			        postUrl: "${request.contextPath}${config.apf.filterProcessesUrl}"]
	}

	/**
	 * Callback after a failed login. Redirects to the auth page with a warning message.
	 */
	def authfail = {

		def username = session[UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
		String msg = ''
		def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
		if (exception) {
			if (exception instanceof AccountExpiredException) {
				msg = g.message(code: "springSecurity.errors.login.expired")
			}
			else if (exception instanceof CredentialsExpiredException) {
				msg = g.message(code: "springSecurity.errors.login.passwordExpired")
			}
			else if (exception instanceof DisabledException) {
				msg = g.message(code: "springSecurity.errors.login.disabled")
			}
			else if (exception instanceof LockedException) {
				msg = g.message(code: "springSecurity.errors.login.locked")
			}
			else {
				msg = g.message(code: "springSecurity.errors.login.fail")
			}
		}

		if (springSecurityService.isAjax(request)) {
			render([error: msg] as JSON)
		}
		else {
			flash.message = msg
			redirect action: 'auth', params: params
		}
	}

	/**
	 * The Ajax success redirect url.
	 */
	def ajaxSuccess = {
		render([success: true, username: springSecurityService.authentication.name] as JSON)
	}

	/**
	 * The Ajax denied redirect url.
	 */
	def ajaxDenied = {
		render([error: 'access denied'] as JSON)
	}

  def changePass() {

    params.modo = (params.modo)?:'ENLACE'
    params.usuario = params.id

    if(params.modo == 'ENLACE') {
      [usuario:params.usuario]
    }
    else {
      def usuario = params.usuario?.trim()
      accesus.Usuario usr = accesus.Usuario.findByUsername(usuario)
      if(usr == null) {
        flash.message = "ERROR: Usuario no existe"
        redirect(action: 'auth')
        return
      }
      params.pass = (params.j_oldPassword != null)?params.j_oldPassword.trim():''
      params.newPass1 = (params.j_newPassword1 != null)?params.j_newPassword1.trim():''
      params.newPass2 = (params.j_newPassword2 != null)?params.j_newPassword2.trim():''
      flash.message = "${params}"

      if (params.pass == '') {
        flash.message = "ERROR: La clave actual no puede ser vacía"
        redirect(action: 'changePass', id: params.usuario)
        return
      } else
      if (springSecurityService.encodePassword(params.pass) != usr.password) {
        flash.message = "ERROR: Clave actual NO válida . . ."
        redirect(action: 'changePass', id: params.usuario)
        return
      } else
      if (params.newPass1 == '') {
        flash.message = "ERROR: Nueva Clave NO puede ser vacía . . ."
        redirect(action: 'changePass', id: params.usuario)
        return
      } else
      if (params.newPass1 != params.newPass2) {
        flash.message = "ERROR: NO repitió la misma clave . . ."
        redirect(action: 'changePass', id: params.usuario)
        return
      }
      usr.password = params.newPass1
      usr.save(flush:true)
      flash.message = "Se cambió con exito clave de usuario"
      redirect(action: 'auth')
    }
  }

  def changePass2() {
    def auth = springSecurityService.getCurrentUser()
    def props = auth.getProperties()
    if (props['individual'] == true) {
      redirect action:'changePass', id: props['username']
    } else {
      flash.message = "No está permitido el cambio de Clave"
      redirect controller: 'logout'
    }
  }

}
