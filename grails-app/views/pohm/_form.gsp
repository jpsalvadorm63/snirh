<%@ page import="base.Pohm" %>


<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'dominio', 'error')} required">
  <label for="dominio">
    <g:message code="pohm.dominio.label" default="Dominio" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="dominio" name="dominio.id" from="${base.Dominio.list()}" optionKey="id" required="" value="${pohmInstance?.dominio?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'codigo', 'error')} required">
  <label for="codigo">
    <g:message code="pohm.codigo.label" default="Codigo" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="codigo" maxlength="16" required="" value="${pohmInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'codigoSENAGUA', 'error')}" >
  <label for="codigoSENAGUA">
    <g:message code="pohm.codigoSENAGUA.label" default="Código SENAGUA" />
  </label>
  <g:textField name="codigoSENAGUA" maxlength="16" required="" value="${pohmInstance?.codigoSENAGUA}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'nombre', 'error')} required" >
  <label for="nombre">
    <g:message code="pohm.nombre.label" default="Nombre" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="nombre" maxlength="128" required="" value="${pohmInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'desde', 'error')} ">
  <label for="desde">
    <g:message code="pohm.desde.label" default="Desde" />
  </label>
  <g:jqDatePicker name="desde" value="${pohmInstance?.desde?.format("dd/MM/yyyy")}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'hasta', 'error')} ">
  <label for="hasta">
    <g:message code="pohm.hasta.label" default="Hasta" />
  </label>
  <g:jqDatePicker name="hasta" value="${pohmInstance?.hasta?.format("dd/MM/yyyy")}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'tipoEstacion', 'error')} required">
  <label for="tipoEstacion">
    <g:message code="pohm.tipoEstacion.label" default="Tipo Estacion" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="tipoEstacion" name="tipoEstacion.id" from="${base.TipoEstacion.list()}" optionKey="id" required="" value="${pohmInstance?.tipoEstacion?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'institucion', 'error')} required">
  <label for="institucion">
    <g:message code="pohm.institucion.label" default="Institucion" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="institucion" name="institucion.id" from="${base.Institucion.list()}" optionKey="id" required="" value="${pohmInstance?.institucion?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'estadoEstacion', 'error')} required">
  <label for="estadoEstacion">
    <g:message code="pohm.estadoEstacion.label" default="Estado Estacion" />
    <span class="required-indicator">*</span>
  </label>
  <g:select id="estadoEstacion" name="estadoEstacion.id" from="${base.EstadoEstacion.list()}" optionKey="id" required="" value="${pohmInstance?.estadoEstacion?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'latitud', 'error')} ">
  <label for="latitud">
    <g:message code="pohm.latitud.label" default="Latitud" />
  </label>
  <g:textField name="latitud" value="${pohmInstance?.latitud}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'longitud', 'error')} ">
  <label for="longitud">
    <g:message code="pohm.longitud.label" default="Longitud" />
  </label>
  <g:textField name="longitud" value="${pohmInstance?.longitud}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'x', 'error')} required">
  <label for="x">
    <g:message code="pohm.x.label" default="X" />
    <span class="required-indicator">*</span>
  </label>
  <g:field name="x" value="${fieldValue(bean: pohmInstance, field: 'x')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'y', 'error')} required">
  <label for="y">
    <g:message code="pohm.y.label" default="Y" />
    <span class="required-indicator">*</span>
  </label>
  <g:field name="y" value="${fieldValue(bean: pohmInstance, field: 'y')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'area', 'error')} required">
  <label for="area">
    <g:message code="pohm.area.label" default="Area" />
    <span class="required-indicator">*</span>
  </label>
  <g:field name="area" value="${fieldValue(bean: pohmInstance, field: 'area')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'elevacion', 'error')} ">
  <label for="elevacion">
    <g:message code="pohm.elevacion.label" default="Elevacion" />
    
  </label>
  <g:field name="elevacion" type="number" value="${pohmInstance.elevacion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pohmInstance, field: 'observaciones', 'error')} ">
  <label for="observaciones">
    <g:message code="pohm.observaciones.label" default="Observaciones" />
  </label>
  <g:textField name="observaciones" maxlength="128" value="${pohmInstance?.observaciones}"/>
</div>
