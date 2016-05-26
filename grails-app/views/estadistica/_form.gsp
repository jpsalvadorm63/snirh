<%@ page import="base.Estadistica" %>



<div class="fieldcontain ${hasErrors(bean: estadisticaInstance, field: 'codigo', 'error')} ">
  <label for="codigo">
    <g:message code="estadistica.codigo.label" default="Codigo" />
    
  </label>
  <g:textField name="codigo" maxlength="16" value="${estadisticaInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadisticaInstance, field: 'estadistica', 'error')} ">
  <label for="estadistica">
    <g:message code="estadistica.estadistica.label" default="Estadistica" />
    
  </label>
  <g:textField name="estadistica" maxlength="64" value="${estadisticaInstance?.estadistica}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadisticaInstance, field: 'descripcion', 'error')} ">
  <label for="descripcion">
    <g:message code="estadistica.descripcion.label" default="Descripcion" />
    
  </label>
  <g:textField name="descripcion" maxlength="128" value="${estadisticaInstance?.descripcion}"/>
</div>

