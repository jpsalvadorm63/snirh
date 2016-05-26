
<%@ page import="base.TipoRelleno" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
    <g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tipoRelleno" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="show-tipoRelleno" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoRelleno">
			
				<g:if test="${tipoRellenoInstance?.codigo}">
				<li class="fieldcontain">
					<span id="codigo-label" class="property-label"><g:message code="tipoRelleno.codigo.label" default="Codigo" /></span>
					
						<span class="property-value" aria-labelledby="codigo-label"><g:fieldValue bean="${tipoRellenoInstance}" field="codigo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoRellenoInstance?.tipoRelleno}">
				<li class="fieldcontain">
					<span id="tipoRelleno-label" class="property-label"><g:message code="tipoRelleno.tipoRelleno.label" default="Tipo Relleno" /></span>
					
						<span class="property-value" aria-labelledby="tipoRelleno-label"><g:fieldValue bean="${tipoRellenoInstance}" field="tipoRelleno"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoRellenoInstance?.color}">
				<li class="fieldcontain">
					<span id="color-label" class="property-label"><g:message code="tipoRelleno.color.label" default="Color" /></span>
					
						<span class="property-value" aria-labelledby="color-label"><g:fieldValue bean="${tipoRellenoInstance}" field="color"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoRellenoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="tipoRelleno.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${tipoRellenoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tipoRellenoInstance?.id}" />
					<g:link class="edit" action="edit" id="${tipoRellenoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
  </div>
	</body>
</html>
