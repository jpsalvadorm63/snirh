<%@ page import="base.Pohm" %>

<table>
  <thead>
  <tr>
    <th><g:message code="pohm.dominio.label" default="Dominio" /></th>
    <g:sortableColumn property="codigo" title="${message(code: 'pohm.codigo.label', default: 'Codigo')}" />
    <g:sortableColumn property="nombre" title="${message(code: 'pohm.nombre.label', default: 'Nombre')}" />
    <g:sortableColumn property="desde" title="${message(code: 'pohm.desde.label', default: 'Desde')}" />
    <g:sortableColumn property="hasta" title="${message(code: 'pohm.hasta.label', default: 'Hasta')}" />
    <th><g:message code="pohm.tipoEstacion.label" default="Tipo Estacion" /></th>
  </tr>
  </thead>
  <tbody>
  <g:each in="${pohmInstanceList}" status="i" var="pohmInstance">
    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
      <td>${fieldValue(bean: pohmInstance, field: "dominio")}</td>
      <td><g:link action="show" id="${pohmInstance.id}">${fieldValue(bean: pohmInstance, field: "codigo")}</g:link></td>
      <td>${fieldValue(bean: pohmInstance, field: "nombre")}</td>
      <td><g:formatDate date="${pohmInstance.desde}" /></td>
      <td><g:formatDate date="${pohmInstance.hasta}" /></td>
      <td>${fieldValue(bean: pohmInstance, field: "tipoEstacion")}</td>
    </tr>
  </g:each>
  </tbody>
</table>
