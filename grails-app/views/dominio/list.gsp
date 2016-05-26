
<%@ page import="base.Dominio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-dominio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="list-dominio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'dominio.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="dominio" title="${message(code: 'dominio.dominio.label', default: 'Dominio')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dominioInstanceList}" status="i" var="dominioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${dominioInstance.id}">${fieldValue(bean: dominioInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: dominioInstance, field: "dominio")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
        <g:if test="${dominioInstanceTotal > maxDisplay}">
          <g:paginate total="${dominioInstanceTotal}" />
        </g:if>
        <g:if test="${( dominioInstanceTotal > 0 ) && ( dominioInstanceTotal <= maxDisplay ) }">
          <p>${dominioInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ dominioInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
		</div>
  </div>
	</body>
</html>
