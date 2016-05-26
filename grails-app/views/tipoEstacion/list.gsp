
<%@ page import="base.TipoEstacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tipoEstacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="list-tipoEstacion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'tipoEstacion.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="tipoEstacion" title="${message(code: 'tipoEstacion.tipoEstacion.label', default: 'Tipo Estacion')}" />
					
						<th><g:message code="tipoEstacion.dominio.label" default="Dominio" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoEstacionInstanceList}" status="i" var="tipoEstacionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: tipoEstacionInstance, field: "codigo")}</td>
					
						<td><g:link action="show" id="${tipoEstacionInstance.id}">${fieldValue(bean: tipoEstacionInstance, field: "tipoEstacion")}</g:link></td>
					
						<td>${fieldValue(bean: tipoEstacionInstance, field: "dominio")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
        <g:if test="${tipoEstacionInstanceTotal > maxDisplay}">
          <g:paginate total="${tipoEstacionInstanceTotal}" />
        </g:if>
        <g:if test="${( tipoEstacionInstanceTotal > 0 ) && ( tipoEstacionInstanceTotal <= maxDisplay ) }">
          <p>${tipoEstacionInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ tipoEstacionInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
		</div>
  </div>
	</body>
</html>
