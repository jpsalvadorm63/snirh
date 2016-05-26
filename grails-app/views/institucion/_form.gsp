<%@ page import="base.Institucion" %>



<div class="fieldcontain ${hasErrors(bean: institucionInstance, field: 'codigo', 'error')} required">
  <label for="codigo">
    <g:message code="institucion.codigo.label" default="Codigo" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="codigo" maxlength="16" required="" value="${institucionInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: institucionInstance, field: 'nombre', 'error')} required">
  <label for="nombre">
    <g:message code="institucion.nombre.label" default="Nombre" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="nombre" maxlength="128" required="" value="${institucionInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: institucionInstance, field: 'contacto', 'error')} ">
  <label for="contacto">
    <g:message code="institucion.contacto.label" default="Contacto" />
    
  </label>
  <g:textField name="contacto" maxlength="32" value="${institucionInstance?.contacto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: institucionInstance, field: 'numero', 'error')} ">
  <label for="numero">
    <g:message code="institucion.numero.label" default="Numero" />
    
  </label>
  <g:textField name="numero" maxlength="32" value="${institucionInstance?.numero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: institucionInstance, field: 'mail', 'error')} ">
  <label for="mail">
    <g:message code="institucion.mail.label" default="Mail" />
    
  </label>
  <g:field type="email" name="mail" maxlength="40" value="${institucionInstance?.mail}"/>
</div>

