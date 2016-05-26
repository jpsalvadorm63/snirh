<%@ page import="accesus.Usuario" %>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'username', 'error')} required">
  <label for="username">
    <g:message code="usuario.username.label" default="Username" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="username" required="" value="${usuarioInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'mail', 'error')} ">
  <label for="mail">
    <g:message code="usuario.mail.label" default="Mail" />
    
  </label>
  <g:field type="email" name="mail" maxlength="64" value="${usuarioInstance?.mail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'institucion', 'error')} required">
  <label for="institucion">
    <g:message code="usuario.institucion.label" default="Institución" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="institucion" name="institucion.id" from="${base.Institucion.list()}" optionKey="id" required="" value="${usuarioInstance?.institucion?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'descripcion', 'error')}">
  <label for="username">
    <g:message code="usuario.descripcion.label" default="Descripción" />
  </label>
  <g:textField name="descripcion" required="" value="${usuarioInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'rol', 'error')} required">
  <label for="rol">
    <g:message code="usuario.rol.label" default="Rol" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="rol" name="rol.id" from="${accesus.Rol.list()}" optionKey="id" required="" value="${usuarioInstance?.rol?.id}" class="many-to-one"/>
</div>
