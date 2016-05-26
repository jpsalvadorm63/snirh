<%@ page import="base.Pohm" %>

<div class="tituloSelection">
  <g:if test="${session.latitud != null}">
    <p>Latitud: <%=session.latitud%></p>
    <p>Longitud: <%=session.longitud%></p>
    <p>Radio de consulta: 10km</p>
  </g:if>
</div>

<div class="domcontainer" style="background:#87cefa;">
  <div class="domtit">Hidrología</div>
  <table style="margin: 0 0 0 8px;">
    <tbody>
    <g:if test="${session.pohmsHI != null && session.pohmsHI != []}">
      <g:each in="${session.pohmsHI}" var="pohm">
        <tr>
          <td style="text-align:center;cursor:pointer;">
            <p class="pohm" id="${pohm.id}"><%=pohm.codigo%></p>
          </td>
        </tr>
      </g:each>
    </g:if>
    </tbody>
  </table>
</div>

<div class="domcontainer" style="background: #ffb6c1;">
  <div class="domtit">Meteorología</div>
  <table style="margin: 0 0 0 8px;">
    <tbody>
    <g:if test="${session.pohmsMET && session.pohmsMET != []}">
      <g:each in="${session.pohmsMET}" var="pohm">
        <tr>
          <td style="text-align:center;cursor:pointer;">
            <p class="pohm" id="${pohm.id}"><%=pohm.codigo%></p>
          </td>
        </tr>
      </g:each>
    </g:if>
    </tbody>
  </table>
</div>

<div class="domcontainer" style="background: #90ee90;">
  <div class="domtit">Hidrogeología</div>
  <table style="margin: 0 0 0 8px;">
    <thead>
    </thead>
    <tbody>
    <g:if test="${pohmsHG && pohmsHG != []}">
    <g:each in="${pohmsHG}" var="pohm">
      <tr>
        <td style="text-align:center;cursor:pointer;">
          <p class="pohm" id="${pohm.id}"><%=pohm.codigo%></p>
        </td>
      </tr>
    </g:each>
    </g:if>
    </tbody>
  </table>
</div>
