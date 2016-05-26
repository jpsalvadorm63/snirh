<%@ page import="base.Dominio" %>



<div class="fieldcontain ${hasErrors(bean: dominioInstance, field: 'codigo', 'error')} ">
  <label for="codigo">
    <g:message code="dominio.codigo.label" default="Codigo" />
    
  </label>
  <g:textField name="codigo" maxlength="4" value="${dominioInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dominioInstance, field: 'dominio', 'error')} ">
  <label for="dominio">
    <g:message code="dominio.dominio.label" default="Dominio" />
    
  </label>
  <g:textField name="dominio" maxlength="16" value="${dominioInstance?.dominio}"/>
</div>

