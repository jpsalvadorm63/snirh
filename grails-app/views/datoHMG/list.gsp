
<%@ page import="base.DatoHMG" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-datoHMG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div id="menu">
      <ul class="menu" style="float:left">
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
      </ul>
    </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="list-datoHMG" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<th><g:message code="datoHMG.pohm.label" default="Pohm" /></th>

						<th><g:message code="datoHMG.parametro.label" default="Parametro" /></th>

						<th><g:message code="datoHMG.frecuencia.label" default="Frecuencia" /></th>

						<th><g:message code="datoHMG.estadistica.label" default="Estadistica" /></th>

						<g:sortableColumn property="fechaHora" title="${message(code: 'datoHMG.fechaHora.label', default: 'Fecha Hora')}" />

						<g:sortableColumn property="datoOriginal" title="${message(code: 'datoHMG.datoOriginal.label', default: 'Dato Original')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${datoHMGInstanceList}" status="i" var="datoHMGInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${datoHMGInstance.id}">${fieldValue(bean: datoHMGInstance, field: "pohm")}</g:link></td>

						<td>${fieldValue(bean: ml, field: "parametro")}</td>

						<td>${fieldValue(bean: datoHMGInstance, field: "frecuencia")}</td>

						<td>${fieldValue(bean: datoHMGInstance, field: "estadistica")}</td>

						<td><g:formatDate date="${datoHMGInstance.fechaHora}" /></td>

						<td>${fieldValue(bean: datoHMGInstance, field: "datoOriginal")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
        <g:if test="${datoHMGInstanceTotal > maxDisplay}">
          <g:paginate total="${datoHMGInstanceTotal}" />
        </g:if>
        <g:if test="${( datoHMGInstanceTotal > 0 ) && ( datoHMGInstanceTotal <= maxDisplay ) }">
          <p>${datoHMGInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ datoHMGInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
      <sec:access expression="hasRole('ROLE_DATALOADER')">
      <div style="width:100%;height:28px;padding:8px 0 0 24px;">
        <div style="float:left;width:40%;text-align:right;margin-top:4px;"><spam style="margin:0px 8px 0 0;font-size:11px;">Subida masiva de datos desde archivo:</spam></div>
        <div style="float:left;width:60%;">
          <fileuploader:form
              upload="datos"
              successAction="uploadFile"
              successController="DatoHMG"
              errorAction="uploadFileError"
              errorController="DatoHMG" />
        </div>
      </div>
      </sec:access>
		</div>
  </div>
	</body>
</html>
