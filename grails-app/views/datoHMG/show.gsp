
<%@ page import="base.DatoHMG" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
    <g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-datoHMG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="show-datoHMG" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list datoHMG">
			
				<g:if test="${datoHMGInstance?.pohm}">
				<li class="fieldcontain">
					<span id="pohm-label" class="property-label"><g:message code="datoHMG.pohm.label" default="Pohm" /></span>
					<span class="property-value" aria-labelledby="pohm-label"><g:link controller="pohm" action="show" id="${datoHMGInstance?.pohm?.id}">${datoHMGInstance?.pohm?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
			
				<g:if test="${datoHMGInstance?.parametro}">
				<li class="fieldcontain">
					<span id="parametro-label" class="property-label"><g:message code="datoHMG.parametro.label" default="Parametro" /></span>
					
						<span class="property-value" aria-labelledby="parametro-label"><g:link controller="parametro" action="show" id="${datoHMGInstance?.parametro?.id}">${datoHMGInstance?.parametro?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${datoHMGInstance?.frecuencia}">
				<li class="fieldcontain">
					<span id="frecuencia-label" class="property-label"><g:message code="datoHMG.frecuencia.label" default="Frecuencia" /></span>
					
						<span class="property-value" aria-labelledby="frecuencia-label"><g:link controller="frecuencia" action="show" id="${datoHMGInstance?.frecuencia?.id}">${datoHMGInstance?.frecuencia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${datoHMGInstance?.estadistica}">
				<li class="fieldcontain">
					<span id="estadistica-label" class="property-label"><g:message code="datoHMG.estadistica.label" default="Estadistica" /></span>
					
						<span class="property-value" aria-labelledby="estadistica-label"><g:link controller="estadistica" action="show" id="${datoHMGInstance?.estadistica?.id}">${datoHMGInstance?.estadistica?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${datoHMGInstance?.fechaHora}">
				<li class="fieldcontain">
					<span id="fechaHora-label" class="property-label"><g:message code="datoHMG.fechaHora.label" default="Fecha Hora" /></span>
					
						<span class="property-value" aria-labelledby="fechaHora-label"><g:formatDate date="${datoHMGInstance?.fechaHora}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${datoHMGInstance?.datoOriginal}">
				<li class="fieldcontain">
					<span id="datoOriginal-label" class="property-label"><g:message code="datoHMG.datoOriginal.label" default="Dato Original" /></span>
					
						<span class="property-value" aria-labelledby="datoOriginal-label"><g:fieldValue bean="${datoHMGInstance}" field="datoOriginal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${datoHMGInstance?.datoRellenado}">
				<li class="fieldcontain">
					<span id="datoRellenado-label" class="property-label"><g:message code="datoHMG.datoRellenado.label" default="Dato Rellenado" /></span>
					
						<span class="property-value" aria-labelledby="datoRellenado-label"><g:fieldValue bean="${datoHMGInstance}" field="datoRellenado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${datoHMGInstance?.datoSenagua}">
				<li class="fieldcontain">
					<span id="datoSenagua-label" class="property-label"><g:message code="datoHMG.datoSenagua.label" default="Dato Senagua" /></span>
					
						<span class="property-value" aria-labelledby="datoSenagua-label"><g:fieldValue bean="${datoHMGInstance}" field="datoSenagua"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${datoHMGInstance?.tipoRelleno}">
				<li class="fieldcontain">
					<span id="tipoRelleno-label" class="property-label"><g:message code="datoHMG.tipoRelleno.label" default="Tipo Relleno" /></span>
					
						<span class="property-value" aria-labelledby="tipoRelleno-label"><g:link controller="tipoRelleno" action="show" id="${datoHMGInstance?.tipoRelleno?.id}">${datoHMGInstance?.tipoRelleno?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${datoHMGInstance?.observacion}">
				<li class="fieldcontain">
					<span id="observacion-label" class="property-label"><g:message code="datoHMG.observacion.label" default="Observacion" /></span>
					
						<span class="property-value" aria-labelledby="observacion-label"><g:fieldValue bean="${datoHMGInstance}" field="observacion"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${datoHMGInstance?.id}" />
					<g:link class="edit" action="edit" id="${datoHMGInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
  </div>
	</body>
</html>
