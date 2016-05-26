<%@ page import="accesus.Usuario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
    <g:set var="entityName" value="${entityName}" />
    <g:set var="entityNamePlural" value="${entityNamePlural}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

    <div id="menu">
      <ul class="menu">
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityNamePlural]" /></g:link></li>
      </ul>
    </div>

    <div class="crud" style="width:700px;margin-left: auto; margin-right: auto;">
		<div id="create-usuario" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${usuarioInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${usuarioInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
        <g:hiddenField name="individual" value="${usuarioInstance?.individual}" />
        <g:hiddenField name="accountExpired" value="${usuarioInstance?.accountExpired}" />
        <g:hiddenField name="accountLocked" value="${usuarioInstance?.accountLocked}" />
        <g:hiddenField name="enabled" value="${usuarioInstance?.enabled}" />
        <g:hiddenField name="passwordExpired" value="${usuarioInstance?.passwordExpired}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
    </div>
	</body>
</html>
