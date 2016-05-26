<%@ page import="base.TipoEstacion" %>



<div class="fieldcontain ${hasErrors(bean: tipoEstacionInstance, field: 'codigo', 'error')} ">
  <label for="codigo">
    <g:message code="tipoEstacion.codigo.label" default="Codigo" />
    
  </label>
  <g:textField name="codigo" maxlength="8" value="${tipoEstacionInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoEstacionInstance, field: 'tipoEstacion', 'error')} ">
  <label for="tipoEstacion">
    <g:message code="tipoEstacion.tipoEstacion.label" default="Tipo Estacion" />
    
  </label>
  <g:textField name="tipoEstacion" maxlength="32" value="${tipoEstacionInstance?.tipoEstacion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoEstacionInstance, field: 'dominio', 'error')} required">
  <label for="dominio">
    <g:message code="tipoEstacion.dominio.label" default="Dominio" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="dominio" name="dominio.id" from="${base.Dominio.list()}" optionKey="id" required="" value="${tipoEstacionInstance?.dominio?.id}" class="many-to-one"/>
</div>

