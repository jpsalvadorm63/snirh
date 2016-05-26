
<%@ page import="base.TipoRelleno" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tipoRelleno" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="list-tipoRelleno" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'tipoRelleno.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="tipoRelleno" title="${message(code: 'tipoRelleno.tipoRelleno.label', default: 'Tipo Relleno')}" />
					
						<g:sortableColumn property="color" title="${message(code: 'tipoRelleno.color.label', default: 'Color')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'tipoRelleno.descripcion.label', default: 'Descripcion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoRellenoInstanceList}" status="i" var="tipoRellenoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tipoRellenoInstance.id}">${fieldValue(bean: tipoRellenoInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: tipoRellenoInstance, field: "tipoRelleno")}</td>
					
						<td>${fieldValue(bean: tipoRellenoInstance, field: "color")}</td>
					
						<td>${fieldValue(bean: tipoRellenoInstance, field: "descripcion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
        <g:if test="${tipoRellenoInstanceTotal > maxDisplay}">
          <g:paginate total="${tipoRellenoInstanceTotal}" />
        </g:if>
        <g:if test="${( tipoRellenoInstanceTotal > 0 ) && ( tipoRellenoInstanceTotal <= maxDisplay ) }">
          <p>${tipoRellenoInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ tipoRellenoInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
		</div>
  </div>
	</body>
</html>
