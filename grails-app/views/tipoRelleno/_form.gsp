<%@ page import="base.TipoRelleno" %>



<div class="fieldcontain ${hasErrors(bean: tipoRellenoInstance, field: 'codigo', 'error')} required">
  <label for="codigo">
    <g:message code="tipoRelleno.codigo.label" default="Codigo" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="codigo" maxlength="16" required="" value="${tipoRellenoInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoRellenoInstance, field: 'tipoRelleno', 'error')} required">
  <label for="tipoRelleno">
    <g:message code="tipoRelleno.tipoRelleno.label" default="Tipo Relleno" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="tipoRelleno" maxlength="128" required="" value="${tipoRellenoInstance?.tipoRelleno}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoRellenoInstance, field: 'color', 'error')} ">
  <label for="color">
    <g:message code="tipoRelleno.color.label" default="Color" />
    
  </label>
  <g:textField name="color" maxlength="32" value="${tipoRellenoInstance?.color}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoRellenoInstance, field: 'descripcion', 'error')} ">
  <label for="descripcion">
    <g:message code="tipoRelleno.descripcion.label" default="Descripcion" />
    
  </label>
  <g:textField name="descripcion" maxlength="128" value="${tipoRellenoInstance?.descripcion}"/>
</div>

