<%@ page import="base.DatoHMG" %>



<div class="fieldcontain ${hasErrors(bean: datoHMGInstance, field: 'pohm', 'error')} required">
  <label for="pohm">
    <g:message code="datoHMG.pohm.label" default="Pohm" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="pohm" name="pohm.id" from="${base.Pohm.list()}" optionKey="id" required="" value="${datoHMGInstance?.pohm?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datoHMGInstance, field: 'parametro', 'error')} required">
  <label for="parametro">
    <g:message code="datoHMG.parametro.label" default="Parametro" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="parametro" name="parametro.id" from="${base.Parametro.list()}" optionKey="id" required="" value="${datoHMGInstance?.parametro?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datoHMGInstance, field: 'frecuencia', 'error')} required">
  <label for="frecuencia">
    <g:message code="datoHMG.frecuencia.label" default="Frecuencia" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="frecuencia" name="frecuencia.id" from="${base.Frecuencia.list()}" optionKey="id" required="" value="${datoHMGInstance?.frecuencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datoHMGInstance, field: 'estadistica', 'error')} required">
  <label for="estadistica">
    <g:message code="datoHMG.estadistica.label" default="Estadistica" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="estadistica" name="estadistica.id" from="${base.Estadistica.list()}" optionKey="id" required="" value="${datoHMGInstance?.estadistica?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datoHMGInstance, field: 'fechaHora', 'error')} required">
  <label for="fechaHora">
    <g:message code="datoHMG.fechaHora.label" default="Fecha Hora" />
    <span class="required-indicator">*</span>
  </label>
  <g:datePicker name="fechaHora" precision="day"  value="${datoHMGInstance?.fechaHora}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: datoHMGInstance, field: 'datoOriginal', 'error')} ">
  <label for="datoOriginal">
    <g:message code="datoHMG.datoOriginal.label" default="Dato Original" />
    
  </label>
  <g:field name="datoOriginal" value="${fieldValue(bean: datoHMGInstance, field: 'datoOriginal')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datoHMGInstance, field: 'datoRellenado', 'error')} ">
  <label for="datoRellenado">
    <g:message code="datoHMG.datoRellenado.label" default="Dato Rellenado" />
    
  </label>
  <g:field name="datoRellenado" value="${fieldValue(bean: datoHMGInstance, field: 'datoRellenado')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datoHMGInstance, field: 'datoSenagua', 'error')} ">
  <label for="datoSenagua">
    <g:message code="datoHMG.datoSenagua.label" default="Dato Senagua" />
    
  </label>
  <g:field name="datoSenagua" value="${fieldValue(bean: datoHMGInstance, field: 'datoSenagua')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datoHMGInstance, field: 'tipoRelleno', 'error')} ">
  <label for="tipoRelleno">
    <g:message code="datoHMG.tipoRelleno.label" default="Tipo Relleno" />
    
  </label>
  <g:select id="tipoRelleno" name="tipoRelleno.id" from="${base.TipoRelleno.list()}" optionKey="id" value="${datoHMGInstance?.tipoRelleno?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: datoHMGInstance, field: 'observacion', 'error')} ">
  <label for="observacion">
    <g:message code="datoHMG.observacion.label" default="Observacion" />
    
  </label>
  <g:textField name="observacion" maxlength="128" value="${datoHMGInstance?.observacion}"/>
</div>

