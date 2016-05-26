<div id="pohmData" style="background-color:transparent;">

  <div class="titulo" style="height:18px;clear:both;">
    <%=titulos['parametro'] + ': '+ titulos['estadistica'] + ' - ' + titulos['frecuencia'] + ' | POHM: ' + pohm.codigo +' ( ' + pohm.nombre + ') | Entidad:'+ pohm.institucion%>
    <p style="font-size:10px;text-align:center;margin-top:4px;">( Presione ESC para salir )</p>
  </div>

  <div class="titulo" style="clear:both;height:612px;">

    <div class="pohmGeneral">
      <table class="pohm">
        <tbody>
        <tr>
          <td class="detalle">Latitud</td>
          <td class="fechaHora"><%=pohm.y%></td>
        </tr>
        <tr>
          <td class="detalle">Longitud</td>
          <td class="fechaHora"><%=pohm.x%></td>
        </tr>
        <tr>
          <td class="detalle">Estadística</td>
          <td class="fechaHora">${titulos['estadistica']}</td>
        </tr>
        <tr>
          <td class="detalle">Frecuencia</td>
          <td class="fechaHora">${titulos['frecuencia']}</td>
        </tr>
        <tr>
          <td class="detalle">Datos</td>
          <td class="fechaHora">${contador['n']}</td>
        </tr>
        <tr>
          <td class="detalle">Desde</td>
          <td class="fechaHora">${contador['dDesde']?.format("dd/MM/yyyy")}</td>
        </tr>
        <tr>
          <td class="detalle">Hasta</td>
          <td class="fechaHora">${contador['dHasta']?.format("dd/MM/yyyy")}</td>
        </tr>
        </tbody>
      </table>
      <div style="width:100%; display: table-cell; padding: 8px 0 0 8px;clear:both;">
        <p style="margin: 0 0 4px 0;text-align: center;color:#444444;font-size: 12px;font-weight: bolder;">FILTRO DE DATOS</p>
        <g:form method="post" controller='geoserver' action='showPohmData' id="${pohm?.id}">
          <g:if test="${pars?.size >= 1}">
            <div style="margin: 4px 0 0 0; clear:both; border: 1px #f5f5f5 solid;">
              <g:each in="${pars}" status="i" var="par">
                <div style="color:gray;">
                  <input type="radio" name="pars" value="${i}" ${(parsIndex == i)?'checked':''}/>
                  ${(par[0] as base.Parametro).parametro.split(' ')[0]} /
                  ${(par[2] as base.Estadistica).estadistica} /
                  ${(par[1] as base.Frecuencia).frecuencia}
                </div>
              </g:each>
            </div>
          </g:if>
          <div style="margin: 8px 0 0 0; clear:both;">
            <label>pohm</label>
            <g:textField readonly='true' name="pohmcdgo" value="${pohm?.codigo}"/>
          </div>
          <div style="margin: 8px 0 0 0; clear:both;">
            <label for="desde">Desde</label>
            <g:jqDatePicker name="desde" value="${returnParams.desde}"/>
          </div>
          <div style="margin: 8px 0 0 0; clear:both;">
            <label for="hasta">hasta</label>
            <g:jqDatePicker name="hasta" value="${returnParams.hasta}"/>
          </div>
          <div style="margin: 8px 32px 0 32px; clear: both;">
            <fieldset class="buttons2">
              <g:actionSubmit class="filter" action="showPohmData" value="FILTRAR !" />
            </fieldset>
          </div>
        </g:form>
      </div>
    </div>

    <div style="float:left;">
      <table style="width:570px;border: 1px solid olive">
        <thead>
        <tr class="showData" style="border-bottom: 1px solid #888888;background-color:#dddddd;">
          <td style="width:20%">fecha</td>
          <td style="width:10%">dato</td>
          <td style="width:10%">unidad</td>
          <td style="width:20%">gráfico</td>
          <td style="width:5%">tipo</td>
          <td style="width:15%">relleno</td>
          <td style="width:20%">cargado x</td>
        </tr>
        </thead>
        <tbody>
        <g:each in="${datoHMGInstanceList}" status="i" var="datoHMGInstance">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" style="border: 1px solid #888888;">
            <td class="fechaHora"><g:formatDate format="yyyy-MMM" date="${datoHMGInstance.fechaHora}" /></td>
            <td class="dato">${(datoHMGInstance.datoValido())['valor']}</td>
            <td class="unidad">${datoHMGInstance.parametro.unidad}</td>
            <td style="padding:0;">
              <div class='bar' style="height:24px;width:${datoHMGInstance.porcentageDe(contador['maxDato'])}%"></div>
            </td>
            <td class="${(datoHMGInstance.datoValido())['tipo']}">
              ${(datoHMGInstance.datoValido())['tipo']}
            </td>
            <td class="relleno">${(datoHMGInstance.datoValido())['relleno']}</td>
            <td>${(datoHMGInstance.usuario)}</td>
          </tr>
        </g:each>
        </tbody>
      </table>

      <g:if test="${datoHMGInstanceTotal > maxDisplay}">
        <div class="pagination" style="margin-top:4px;">
          <g:paginate total="${datoHMGInstanceTotal}" action='showPohmData' id="${pohm.id}" params="${returnParams}"/>
        </div>
        <div class="export">
          <g:link class="excel" style="text-decoration: none;margin: 0 8px 0 8px;cursor: pointer" action='aExcel' id="${pohm.id}" params="${returnParams + ['modo':'visualizado', 'max':parte.max, 'offset':parte.offset]}">
            <g:img dir="images" file="excel.png"/> Visualizado
          </g:link>
          <g:link class="excel" style="text-decoration: none;margin: 0 8px 0 8px;cursor: pointer" action='aExcel' id="${pohm.id}" params="${returnParams + ['modo':'todo'] }">
            <g:img dir="images" file="excel.png"/> Todo
          </g:link>
        </div>
      </g:if>
      <g:if test="${( datoHMGInstanceTotal > 0 ) && ( datoHMGInstanceTotal <= maxDisplay ) }">
        <div class="pagination" style="margin-top:4px;">
          <p>${datoHMGInstanceTotal} registros</p>
        </div>
        <div style="clear:both">
          <g:link class="excel" style="text-decoration: none;margin: 0 8px 0 8px;cursor: pointer" action='aExcel' id="${pohm.id}" params="${returnParams + ['modo':'todo']}">
            <g:img dir="images" file="excel.png"/> Visualizado
          </g:link>
        </div>
      </g:if>
      <g:if test="${ datoHMGInstanceTotal == 0 }">
        <div class="pagination" style="margin-top:4px;">
          <p style="color:#8b0000;">NO HAY registros</p>
        </div>
      </g:if>
    </div>

  </div>

</div>
