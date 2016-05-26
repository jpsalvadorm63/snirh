<%@ page import="base.Frecuencia" %>



<div class="fieldcontain ${hasErrors(bean: frecuenciaInstance, field: 'codigo', 'error')} ">
  <label for="codigo">
    <g:message code="frecuencia.codigo.label" default="Codigo" />
    
  </label>
  <g:textField name="codigo" maxlength="16" value="${frecuenciaInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: frecuenciaInstance, field: 'frecuencia', 'error')} ">
  <label for="frecuencia">
    <g:message code="frecuencia.frecuencia.label" default="Frecuencia" />
    
  </label>
  <g:textField name="frecuencia" maxlength="64" value="${frecuenciaInstance?.frecuencia}"/>
</div>

