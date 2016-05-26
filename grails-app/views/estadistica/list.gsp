
<%@ page import="base.Estadistica" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-estadistica" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="list-estadistica" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'estadistica.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="estadistica" title="${message(code: 'estadistica.estadistica.label', default: 'Estadistica')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'estadistica.descripcion.label', default: 'Descripcion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${estadisticaInstanceList}" status="i" var="estadisticaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${estadisticaInstance.id}">${fieldValue(bean: estadisticaInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: estadisticaInstance, field: "estadistica")}</td>
					
						<td>${fieldValue(bean: estadisticaInstance, field: "descripcion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
        <g:if test="${estadisticaInstanceTotal > maxDisplay}">
          <g:paginate total="${estadisticaInstanceTotal}" />
        </g:if>
        <g:if test="${( estadisticaInstanceTotal > 0 ) && ( estadisticaInstanceTotal <= maxDisplay ) }">
          <p>${estadisticaInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ estadisticaInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
		</div>
  </div>
	</body>
</html>
