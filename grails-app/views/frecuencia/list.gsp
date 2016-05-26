
<%@ page import="base.Frecuencia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-frecuencia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="list-frecuencia" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'frecuencia.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="frecuencia" title="${message(code: 'frecuencia.frecuencia.label', default: 'Frecuencia')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${frecuenciaInstanceList}" status="i" var="frecuenciaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${frecuenciaInstance.id}">${fieldValue(bean: frecuenciaInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: frecuenciaInstance, field: "frecuencia")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
        <g:if test="${frecuenciaInstanceTotal > maxDisplay}">
          <g:paginate total="${frecuenciaInstanceTotal}" />
        </g:if>
        <g:if test="${( frecuenciaInstanceTotal > 0 ) && ( frecuenciaInstanceTotal <= maxDisplay ) }">
          <p>${frecuenciaInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ frecuenciaInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
		</div>
  </div>
	</body>
</html>
