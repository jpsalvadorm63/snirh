
<%@ page import="base.EstadoEstacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-estadoEstacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="list-estadoEstacion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'estadoEstacion.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="estado" title="${message(code: 'estadoEstacion.estado.label', default: 'Estado')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${estadoEstacionInstanceList}" status="i" var="estadoEstacionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${estadoEstacionInstance.id}">${fieldValue(bean: estadoEstacionInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: estadoEstacionInstance, field: "estado")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
        <g:if test="${estadoEstacionInstanceTotal > maxDisplay}">
          <g:paginate total="${estadoEstacionInstanceTotal}" />
        </g:if>
        <g:if test="${( estadoEstacionInstanceTotal > 0 ) && ( estadoEstacionInstanceTotal <= maxDisplay ) }">
          <p>${estadoEstacionInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ estadoEstacionInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
		</div>
  </div>
	</body>
</html>
