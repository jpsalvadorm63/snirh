
<%@ page import="base.TipoEstacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
    <g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tipoEstacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="show-tipoEstacion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoEstacion">
			
				<g:if test="${tipoEstacionInstance?.codigo}">
				<li class="fieldcontain">
					<span id="codigo-label" class="property-label"><g:message code="tipoEstacion.codigo.label" default="Codigo" /></span>
					
						<span class="property-value" aria-labelledby="codigo-label"><g:fieldValue bean="${tipoEstacionInstance}" field="codigo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoEstacionInstance?.tipoEstacion}">
				<li class="fieldcontain">
					<span id="tipoEstacion-label" class="property-label"><g:message code="tipoEstacion.tipoEstacion.label" default="Tipo Estacion" /></span>
					
						<span class="property-value" aria-labelledby="tipoEstacion-label"><g:fieldValue bean="${tipoEstacionInstance}" field="tipoEstacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoEstacionInstance?.dominio}">
				<li class="fieldcontain">
					<span id="dominio-label" class="property-label"><g:message code="tipoEstacion.dominio.label" default="Dominio" /></span>
					<span class="property-value" aria-labelledby="dominio-label"><g:link controller="dominio" action="show" id="${tipoEstacionInstance?.dominio?.id}">${tipoEstacionInstance?.dominio?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tipoEstacionInstance?.id}" />
					<g:link class="edit" action="edit" id="${tipoEstacionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
  </div>
	</body>
</html>
