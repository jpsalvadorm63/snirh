
<%@ page import="base.Parametro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-parametro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="list-parametro" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'parametro.codigo.label', default: 'Codigo')}" />
					
						<th><g:message code="parametro.dominio.label" default="Dominio" /></th>
					
						<g:sortableColumn property="parametro" title="${message(code: 'parametro.parametro.label', default: 'Parametro')}" />
					
						<g:sortableColumn property="unidad" title="${message(code: 'parametro.unidad.label', default: 'Unidad')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${parametroInstanceList}" status="i" var="parametroInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${parametroInstance.id}">${fieldValue(bean: parametroInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: parametroInstance, field: "dominio")}</td>
					
						<td>${fieldValue(bean: parametroInstance, field: "parametro")}</td>
					
						<td>${fieldValue(bean: parametroInstance, field: "unidad")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
        <g:if test="${parametroInstanceTotal > maxDisplay}">
          <g:paginate total="${parametroInstanceTotal}" />
        </g:if>
        <g:if test="${( parametroInstanceTotal > 0 ) && ( parametroInstanceTotal <= maxDisplay ) }">
          <p>${parametroInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ parametroInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
		</div>
  </div>
	</body>
</html>
