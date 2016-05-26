
<%@ page import="base.Pohm" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
    <g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pohm" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="show-pohm" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pohm">
			
				<g:if test="${pohmInstance?.dominio}">
				<li class="fieldcontain">
					<span id="dominio-label" class="property-label"><g:message code="pohm.dominio.label" default="Dominio" /></span>
					
						<span class="property-value" aria-labelledby="dominio-label"><g:link controller="dominio" action="show" id="${pohmInstance?.dominio?.id}">${pohmInstance?.dominio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.codigo}">
				<li class="fieldcontain">
					<span id="codigo-label" class="property-label"><g:message code="pohm.codigo.label" default="Codigo" /></span>
					
						<span class="property-value" aria-labelledby="codigo-label"><g:fieldValue bean="${pohmInstance}" field="codigo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="pohm.nombre.label" default="Nombre" /></span>
					<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${pohmInstance}" field="nombre"/></span>
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.desde}">
				<li class="fieldcontain">
					<span id="desde-label" class="property-label"><g:message code="pohm.desde.label" default="Desde" /></span>
				  <span class="property-value" aria-labelledby="desde-label"><g:formatDate format="dd/MM/yyyy" date="${pohmInstance?.desde}" /><span style="color:silver;"> dd/mm/yyyy</span>
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.hasta}">
				<li class="fieldcontain">
					<span id="hasta-label" class="property-label"><g:message code="pohm.hasta.label" default="Hasta" /></span>
					<span class="property-value" aria-labelledby="hasta-label"><g:formatDate format="dd/MM/yyyy" date="${pohmInstance?.hasta}" /> <span style="color:silver;">dd/mm/yyyy</span>
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.tipoEstacion}">
				<li class="fieldcontain">
					<span id="tipoEstacion-label" class="property-label"><g:message code="pohm.tipoEstacion.label" default="Tipo Estacion" /></span>
					<span class="property-value" aria-labelledby="tipoEstacion-label"><g:link controller="tipoEstacion" action="show" id="${pohmInstance?.tipoEstacion?.id}">${pohmInstance?.tipoEstacion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.institucion}">
				<li class="fieldcontain">
					<span id="institucion-label" class="property-label"><g:message code="pohm.institucion.label" default="Institucion" /></span>
					
						<span class="property-value" aria-labelledby="institucion-label"><g:link controller="institucion" action="show" id="${pohmInstance?.institucion?.id}">${pohmInstance?.institucion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.estadoEstacion}">
				<li class="fieldcontain">
					<span id="estadoEstacion-label" class="property-label"><g:message code="pohm.estadoEstacion.label" default="Estado Estacion" /></span>
					
						<span class="property-value" aria-labelledby="estadoEstacion-label"><g:link controller="estadoEstacion" action="show" id="${pohmInstance?.estadoEstacion?.id}">${pohmInstance?.estadoEstacion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.latitud}">
				<li class="fieldcontain">
					<span id="latitud-label" class="property-label"><g:message code="pohm.latitud.label" default="Latitud" /></span>
					
						<span class="property-value" aria-labelledby="latitud-label"><g:fieldValue bean="${pohmInstance}" field="latitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.longitud}">
				<li class="fieldcontain">
					<span id="longitud-label" class="property-label"><g:message code="pohm.longitud.label" default="Longitud" /></span>
					
						<span class="property-value" aria-labelledby="longitud-label"><g:fieldValue bean="${pohmInstance}" field="longitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.x}">
				<li class="fieldcontain">
					<span id="x-label" class="property-label"><g:message code="pohm.x.label" default="X" /></span>
					
						<span class="property-value" aria-labelledby="x-label"><g:fieldValue bean="${pohmInstance}" field="x"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.y}">
				<li class="fieldcontain">
					<span id="y-label" class="property-label"><g:message code="pohm.y.label" default="Y" /></span>
					
						<span class="property-value" aria-labelledby="y-label"><g:fieldValue bean="${pohmInstance}" field="y"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.area}">
				<li class="fieldcontain">
					<span id="area-label" class="property-label"><g:message code="pohm.area.label" default="Area" /></span>
					
						<span class="property-value" aria-labelledby="area-label"><g:fieldValue bean="${pohmInstance}" field="area"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.elevacion}">
				<li class="fieldcontain">
					<span id="elevacion-label" class="property-label"><g:message code="pohm.elevacion.label" default="Elevacion" /></span>
					
						<span class="property-value" aria-labelledby="elevacion-label"><g:fieldValue bean="${pohmInstance}" field="elevacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pohmInstance?.observaciones}">
				<li class="fieldcontain">
					<span id="observaciones-label" class="property-label"><g:message code="pohm.observaciones.label" default="Observaciones" /></span>
					
						<span class="property-value" aria-labelledby="observaciones-label"><g:fieldValue bean="${pohmInstance}" field="observaciones"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${pohmInstance?.id}" />
					<g:link class="edit" action="edit" id="${pohmInstance?.id}">
            <g:message code="default.button.edit.label" default="Edit" />
          </g:link>
          <!-- <g:actionSubmit class="edit" action="edit"  id="${pohmInstance?.id}" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />-->
          <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
  </div>
	</body>
</html>
