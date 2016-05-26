
<%@ page import="base.Institucion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-institucion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="list-institucion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'institucion.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'institucion.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="contacto" title="${message(code: 'institucion.contacto.label', default: 'Contacto')}" />
					
						<g:sortableColumn property="numero" title="${message(code: 'institucion.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="mail" title="${message(code: 'institucion.mail.label', default: 'Mail')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${institucionInstanceList}" status="i" var="institucionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${institucionInstance.id}">${fieldValue(bean: institucionInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: institucionInstance, field: "nombre")}</td>
					
						<td>${fieldValue(bean: institucionInstance, field: "contacto")}</td>
					
						<td>${fieldValue(bean: institucionInstance, field: "numero")}</td>
					
						<td>${fieldValue(bean: institucionInstance, field: "mail")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
        <g:if test="${institucionInstanceTotal > maxDisplay}">
          <g:paginate total="${institucionInstanceTotal}" />
        </g:if>
        <g:if test="${( institucionInstanceTotal > 0 ) && ( institucionInstanceTotal <= maxDisplay ) }">
          <p>${institucionInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ institucionInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
		</div>
  </div>
	</body>
</html>
