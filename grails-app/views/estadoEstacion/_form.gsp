<%@ page import="base.EstadoEstacion" %>



<div class="fieldcontain ${hasErrors(bean: estadoEstacionInstance, field: 'codigo', 'error')} ">
  <label for="codigo">
    <g:message code="estadoEstacion.codigo.label" default="Codigo" />
    
  </label>
  <g:textField name="codigo" maxlength="4" value="${estadoEstacionInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoEstacionInstance, field: 'estado', 'error')} ">
  <label for="estado">
    <g:message code="estadoEstacion.estado.label" default="Estado" />
    
  </label>
  <g:textField name="estado" maxlength="16" value="${estadoEstacionInstance?.estado}"/>
</div>

