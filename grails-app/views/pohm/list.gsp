<%@ page import="base.Pohm" %>
<%@ page import="base.Dominio" %>
<%@ page import="base.TipoEstacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
    <% String appName=grailsApplication.metadata['app.name'] %>
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <g:javascript>
      $(document).ready(function() {

        $("#selDominio").change(function() {
          $("#selectTipoEstacion").css("display",(this.value!='*')?"block":"none");
          $.ajax({
            url: "${createLink(controller: 'pohm', action: 'selDominio')}",
            data: "id=" + this.value,
            cache: false,
            success: function(html) {
              $("#selTipoEstacion").html(html);
            }
          });
        });

        $("#selTipoEstacion").change(function() {
          $.ajax({
            url: "${createLink(controller: 'pohm', action: 'selTipoEstacion')}",
            data: "id=" + this.value,
            cache: false
          });
        });

      })
    </g:javascript>
	</head>
	<body>
		<a href="#list-pohm" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>

  <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
    <div style="width:100%;height:36px;padding:8px 0 0 24px;background-color:yellow;">
      <div id="selectDominio" style="float:left;">
        <label for="selDominio">Por dominio:</label>
        <g:select id="selDominio" noSelection="${['*':'Todos los dominios']}"
          optionKey="id"
          from="${Dominio.list()}"
          name="dominio"
          value="${(session.pohmDominio)?session.pohmDominio.id:''}">
        </g:select>
      </div>
      <div id="selectTipoEstacion" style="float:left;margin-left:16px;">
        <label for='selTipoEstacion'>Por tipo de estación:</label>
        <g:select id="selTipoEstacion" noSelection="${['':'Todo tipo de estación']}"
          optionKey="id"
          from="${TipoEstacion.list()}"
          name="tipoEstacion"
          value="${(session.pohmTipoEstacion)?session.pohmTipoEstacion.id:''}">
        </g:select>
      </div>
      <div id="btnFiltar" style="float:left;margin-left:16px;margin-top:4px;cursor:pointer;">
        <a href="${createLink(controller: 'pohm', action: 'filtrar')}">[ FILTRAR ]</a>
      </div>
    </div>
		<div id="list-pohm" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div id="displayTable">
        <g:render template="list"/>
      </div>
			<div class="pagination">
        <g:if test="${pohmInstanceTotal > maxDisplay}">
          <g:paginate total="${pohmInstanceTotal}" />
        </g:if>
        <g:if test="${( pohmInstanceTotal > 0 ) && ( pohmInstanceTotal <= maxDisplay ) }">
          <p>${pohmInstanceTotal} registros</p>
        </g:if>
        <g:if test="${ pohmInstanceTotal == 0 }">
          <p>NO HAY registros</p>
        </g:if>
      </div>
      <sec:access expression="hasRole('ROLE_DATALOADER')">
      <div style="width:100%;height:28px;padding:8px 0 0 24px;">
        <div style="float:left;width:40%;text-align:right;margin-top:4px;"><spam style="margin:0px 8px 0 0;font-size:11px;">Subida masiva de datos desde archivo:</spam></div>
        <div style="float:left;width:60%;">
          <fileuploader:form
              upload="estaciones"
              successAction="uploadFile"
              successController="pohm"
              errorAction="uploadFileError"
              errorController="pohm" />
        </div>
      </div>
      </sec:access>
		</div>
  </div>
	</body>
</html>
