<%@ page import="base.DatoHMG" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main">
  <r:require module="datepick" />
  <g:set var="entityName" value="${entityName}" />
  <g:set var="entityNamePlural" value="${entityNamePlural}" />
  <title>Datamart !</title>

  <style type="text/css">
    td { font-family: monospace; }
    td.pohm { background-color:  #E1F2B6; }
    td.fechaHora {}
    td.dato {font-weight: bold; text-align: right;}
    td.orig {color: #778899; text-align: center;}
    td.nod {color: #99AABB; text-align: center;}
    td.seng {color: #00008b; text-align: center;}
    td.rell {color: #8b0000; text-align: center;}
    td.unidad { text-align: center;}
    td.relleno {color: red; text-align: left;}
    th {text-align: center;}
    td.detalle { background-color: #E1F2B6; }
  </style>

</head>
<body>

<div id="menu">
  <ul class="menu">
    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    <li><g:link controller="geoserver" action="showInfo" id="Demarcaciones">Demarcaciones</g:link></li>
    <li><g:link controller="geoserver" action="showInfo" id="Sistemas">Sistemas</g:link></li>
    <li><g:link controller="geoserver" action="showInfo" id="nivel1">Pfafstetter</g:link></li>
    <li><g:link controller="geoserver" action="showInfo" id="Provincias">Provincias</g:link></li>
    <li><g:link controller="${returnParams.paramController}" action="${returnParams.paramAction}" id="${returnParams.paramId}">Mapa base de consulta !</g:link></li>
  </ul>
</div>

<div style="min-width:960px; max-width:95%; margin-left: auto; margin-right: auto;background-color: #f5f5f5; display: table;">

  <div style="width:250px; height: 400px;display: table-cell;">
    <table style="width:230px;border-width: 1px; border-color: olive; ">
      <thead>
      <tr></tr><tr></tr>
      </thead>
      <tbody>
        <tr class='odd' style="border: 1px solid #888888;">
          <td class="detalle">Parámetro</td>
          <td class="fechaHora">${titulos['parametro']}</td>
        </tr>
        <tr class='even' style="border: 1px solid #888888;">
          <td class="detalle">Estadística</td>
          <td class="fechaHora">${titulos['estadistica']}</td>
        </tr>
        <tr class='odd' style="border: 1px solid #888888;">
          <td class="detalle">Frecuencia</td>
          <td class="fechaHora">${titulos['frecuencia']}</td>
        </tr>
        <tr class='even' style="border: 1px solid #888888;">
          <td class="detalle">Registros de datos</td>
          <td class="fechaHora">${contador['n']}</td>
        </tr>
        <tr class='even' style="border: 1px solid #888888;">
          <td class="detalle">Datos desde</td>
          <td class="fechaHora">${contador['dDesde']?.format("dd/MM/yyyy")}</td>
        </tr>
        <tr class='even' style="border: 1px solid #888888;">
          <td class="detalle">...  hasta</td>
          <td class="fechaHora">${contador['dHasta']?.format("dd/MM/yyyy")}</td>
        </tr>
      </tbody>
    </table>
  </div>

  <!-- <div id="list-datoHMG" class="content scaffold-list" role="main" > -->
  <div style="display: table-cell">

    <table style="width:450px;border-width: 1px; border-color: olive; margin-left: auto; margin-right: auto;">
      <thead>
      <tr style="border: 2px solid #888888;"><td colspan="6" style="width:100%;text-align: center; font-family: verdana;font-size: 12px;font-weight: bold;">
        ${titulos['principal']}
      </td></tr>
      <tr class="showData" style="border-bottom: 2px solid #888888;">
        <td style="width:15%">POHM</td>
        <td style="width:25%">AAAA-MMM</td>
        <td style="width:20%">dato</td>
        <td style="width:10%">unidad</td>
        <td style="width:10%">tipo</td>
        <td style="width:20%">relleno</td>
      </tr>
      </thead>
      <tbody>
      <g:each in="${datoHMGInstanceList}" status="i" var="datoHMGInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" style="border: 1px solid #888888;">
          <td class="pohm">${fieldValue(bean: datoHMGInstance, field: "pohm")}</td>
          <td class="fechaHora"><g:formatDate format="yyyy-MMM" date="${datoHMGInstance.fechaHora}" /></td>
          <td class="dato">${(datoHMGInstance.datoValido())['valor']}</td>
          <td class="unidad">${datoHMGInstance.parametro.unidad}</td>
          <td class="${(datoHMGInstance.datoValido())['tipo']}">${(datoHMGInstance.datoValido())['tipo']}</td>
          <td class="relleno">${(datoHMGInstance.datoValido())['relleno']}</td>
        </tr>
      </g:each>
      </tbody>
    </table>

    <div class="pagination">
      <g:if test="${datoHMGInstanceTotal > maxDisplay}">
        <g:paginate total="${datoHMGInstanceTotal}" action='showPohmData' update='successPohm' id="${pohm.id}" params="${returnParams}"/>
      </g:if>
      <g:if test="${( datoHMGInstanceTotal > 0 ) && ( datoHMGInstanceTotal <= maxDisplay ) }">
        <p>${datoHMGInstanceTotal} registros</p>
      </g:if>
      <g:if test="${ datoHMGInstanceTotal == 0 }">
        <p>NO HAY registros</p>
      </g:if>
    </div>
  </div>

  <div style="width:250px; height: 400px;display: table-cell; padding: 48px 0 0 36px;">
    <g:form method="post">

      <g:hiddenField name="paramController" value="${returnParams.paramController}" />
      <g:hiddenField name="paramAction" value="${returnParams.paramAction}" />
      <g:hiddenField name="id" value="${pohm?.id}" />

      <div style="margin: 8px 0 0 0; clear:both;">
        <label for="desde" style="width:60px">POHM</label>
        <g:textField readonly='true' name="pohmcdgo" value="${pohm?.codigo}"/>
      </div>

      <div style="margin: 8px 0 0 0; clear:both;">
        <label for="desde" style="width:60px">Desde</label>
        <g:jqDatePicker name="desde" value="${returnParams.desde}"/>
      </div>
      <div style="margin: 8px 0 0 0; clear:both;">
        <label for="hasta" style="width:60px">hasta</label>
        <g:jqDatePicker name="hasta" value="${returnParams.hasta}"/>
      </div>
      <div style="margin: 8px 32px 0 32px; clear:both;">
        <fieldset class="buttons">
          <g:actionSubmit class="save" action="showPohmData" value="FILTRAR !" />
        </fieldset>
      </div>
    </g:form>
  </div>

</div>
</body>
</html>
