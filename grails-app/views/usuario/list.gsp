
<%@ page import="accesus.Usuario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
    <script language="javascript">
      function resetPasswordSuccess(musuario) {
        var msg = 'Se estableció nueva clave de acceso para usuario/a [ ' + musuario + ' ]. La nueva clave ha sido enviada a su correo electrónico.';
        alert(msg);
      }
      function resetPasswordFailure(musuario) {
        alert('Error al establecer nueva clave para usuario/a [ ' + musuario + ' ].');
      }
    </script>
	</head>
	<body>
		<a href="#list-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left:auto;margin-right:auto;">
		<div id="list-usuario" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
            <g:sortableColumn property="enabled" title="${message(code: 'usuario.institucion.label', default: 'Institución')}" />
            <g:sortableColumn property="enabled" title="Hab." />
						<g:sortableColumn property="username" title="${message(code: 'usuario.username.label', default: 'Usuario')}" />
            <g:sortableColumn property="rol" title="${message(code: 'usuario.rol.label', default: 'Rol')}" />
						<g:sortableColumn property="mail" title="${message(code: 'usuario.mail.label', default: 'Mail')}" />
            <td>Reset Passwd</td>
					</tr>
				</thead>
				<tbody>
				<g:each in="${usuarioInstanceList}" status="i" var="usuarioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${fieldValue(bean: usuarioInstance, field: "institucion")}</td>
            <td>${usuarioInstance.enabled?'<span style=\'font-weight:bolder;\'>SI</span>':'NO'}</td>
						<td><g:link action="show" id="${usuarioInstance.id}">${fieldValue(bean: usuarioInstance, field: "username")}</g:link></td>
            <td>${fieldValue(bean: usuarioInstance, field: "rol")}</td>
            <td>${fieldValue(bean: usuarioInstance, field: "mail")}</td>
            <td>
              <g:remoteLink
                action="resetPass"
                id="${usuarioInstance.id}"
                onSuccess="resetPasswordSuccess('${fieldValue(bean: usuarioInstance, field: "username")}')"
                onFailure="resetPasswordFailure('${fieldValue(bean: usuarioInstance, field: "username")}')">
                    Reset
              </g:remoteLink>
            </td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
        <g:if test="${usuarioInstanceTotal > maxDisplay}">
          <g:paginate total="${usuarioInstanceTotal}" />
        </g:if>
        <g:if test="${( usuarioInstanceTotal > 0 ) && ( usuarioInstanceTotal <= maxDisplay ) }">
          <p>${usuarioInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ usuarioInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
		</div>
  </div>
	</body>
</html>
