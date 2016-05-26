<%@ page import="base.Operativo" %>



<div class="fieldcontain ${hasErrors(bean: operativoInstance, field: 'codigo', 'error')} required">
  <label for="codigo">
    <g:message code="operativo.codigo.label" default="Codigo" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="codigo" maxlength="8" required="" value="${operativoInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: operativoInstance, field: 'descripcion', 'error')} required">
  <label for="descripcion">
    <g:message code="operativo.descripcion.label" default="Descripcion" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="descripcion" maxlength="32" required="" value="${operativoInstance?.descripcion}"/>
</div>

