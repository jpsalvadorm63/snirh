<div id="pohmData" style="background-color:transparent;">

  <div class="titulo" style="height:24px;clear:both;">
    Archivo: <%=file?.nombre%> | datos cargado el: <%=file?.fechaCarga%>
    <p class="cerrar">[ Click aquí para salir ]</p>
  </div>

  <div class="titulo" style="clear:both;height:612px;">

  <table>
    <thead>
      <tr>
        <th><g:message code="datoHMG.pohm.label" default="Pohm" /></th>
        <th><g:message code="datoHMG.parametro.label" default="Parametro" /></th>
        <th><g:message code="datoHMG.frecuencia.label" default="Frecuencia" /></th>
        <th><g:message code="datoHMG.estadistica.label" default="Estadistica" /></th>
        <g:sortableColumn property="fechaHora" title="${message(code: 'datoHMG.fechaHora.label', default: 'Fecha Hora')}" />
        <g:sortableColumn property="datoOriginal" title="${message(code: 'datoHMG.datoOriginal.label', default: 'Orig')}" />
        <g:sortableColumn property="datoRellenado" title="${message(code: 'datoHMG.datoRellenado.label', default: 'Rell')}" />
        <g:sortableColumn property="datoSenagua" title="${message(code: 'datoHMG.datoRellenado.label', default: 'Senagua')}" />
      </tr>
    </thead>
    <tbody>
    <g:each in="${datoHMGInstanceList}" status="i" var="datoHMGInstance">
      <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
        <td>${fieldValue(bean: datoHMGInstance, field: "pohm")}</td>
        <td style="text-align: center;">${fieldValue(bean: datoHMGInstance, field: "parametro")}</td>
        <td style="text-align: center;">${fieldValue(bean: datoHMGInstance, field: "frecuencia")}</td>
        <td style="text-align: center;">${fieldValue(bean: datoHMGInstance, field: "estadistica")}</td>
        <td style="text-align: center;"><g:formatDate date="${datoHMGInstance.fechaHora}" format="MMM/yyyy"/></td>
        <td>${fieldValue(bean: datoHMGInstance, field: "datoOriginal")}</td>
        <td>${fieldValue(bean: datoHMGInstance, field: "datoRellenado")}</td>
        <td>${fieldValue(bean: datoHMGInstance, field: "datoSenagua")}</td>
      </tr>
    </g:each>
    </tbody>
  </table>

  <div class="pagination">
    <g:if test="${datoHMGInstanceTotal > maxDisplay}">
      <div id='pag'>
        <g:paginate total="${datoHMGInstanceTotal}"  id="${file?.id}"/>
      </div>
    </g:if>
    <g:if test="${( datoHMGInstanceTotal > 0 ) && ( datoHMGInstanceTotal <= maxDisplay ) }">
      <p>${datoHMGInstanceTotal} registros</p>
    </g:if>
    <g:if test="${ datoHMGInstanceTotal == 0 }">
      <p>NO HAY registros</p>
    </g:if>
  </div>

  </div>

</div>
