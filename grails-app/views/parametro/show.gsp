
<%@ page import="base.Parametro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
    <g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-parametro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="show-parametro" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list parametro">
			
				<g:if test="${parametroInstance?.codigo}">
				<li class="fieldcontain">
					<span id="codigo-label" class="property-label"><g:message code="parametro.codigo.label" default="Codigo" /></span>
					
						<span class="property-value" aria-labelledby="codigo-label"><g:fieldValue bean="${parametroInstance}" field="codigo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${parametroInstance?.dominio}">
				<li class="fieldcontain">
					<span id="dominio-label" class="property-label"><g:message code="parametro.dominio.label" default="Dominio" /></span>
					
						<span class="property-value" aria-labelledby="dominio-label"><g:link controller="dominio" action="show" id="${parametroInstance?.dominio?.id}">${parametroInstance?.dominio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${parametroInstance?.parametro}">
				<li class="fieldcontain">
					<span id="parametro-label" class="property-label"><g:message code="parametro.parametro.label" default="Parametro" /></span>
					
						<span class="property-value" aria-labelledby="parametro-label"><g:fieldValue bean="${parametroInstance}" field="parametro"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${parametroInstance?.unidad}">
				<li class="fieldcontain">
					<span id="unidad-label" class="property-label"><g:message code="parametro.unidad.label" default="Unidad" /></span>
					
						<span class="property-value" aria-labelledby="unidad-label"><g:fieldValue bean="${parametroInstance}" field="unidad"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${parametroInstance?.id}" />
					<g:link class="edit" action="edit" id="${parametroInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
  </div>
	</body>
</html>
