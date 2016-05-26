<%@ page import="base.Parametro" %>



<div class="fieldcontain ${hasErrors(bean: parametroInstance, field: 'codigo', 'error')} ">
  <label for="codigo">
    <g:message code="parametro.codigo.label" default="Codigo" />
    
  </label>
  <g:textField name="codigo" maxlength="16" value="${parametroInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: parametroInstance, field: 'dominio', 'error')} required">
  <label for="dominio">
    <g:message code="parametro.dominio.label" default="Dominio" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="dominio" name="dominio.id" from="${base.Dominio.list()}" optionKey="id" required="" value="${parametroInstance?.dominio?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: parametroInstance, field: 'parametro', 'error')} ">
  <label for="parametro">
    <g:message code="parametro.parametro.label" default="Parametro" />
    
  </label>
  <g:textField name="parametro" maxlength="128" value="${parametroInstance?.parametro}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: parametroInstance, field: 'unidad', 'error')} ">
  <label for="unidad">
    <g:message code="parametro.unidad.label" default="Unidad" />
    
  </label>
  <g:textField name="unidad" maxlength="32" value="${parametroInstance?.unidad}"/>
</div>

