<%@ page import="base.UpldFile" %>
<!DOCTYPE html>

<html>
<head>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${entityName}" />
  <g:set var="entityNamePlural" value="${entityNamePlural}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>

  <script type="text/javascript">

    function init(){

      $("div#dialog").dialog({
        position: "top",
        show: "blind",
        hide: "puff",
        width: "920",
        height: "760",
        resizable: true,
        autoOpen: false });

      $("a.step,a.nextLink,a.prevLink").live("click", function(e) {
        var url = this.href;
        $.ajax({
          url : url,
          success: function(data) {
            $("div#dialog").dialog('open') ;
            document.getElementById('dialog').innerHTML = data;
          }
        });
        return false;
      });

      $("p.pohm").live("click",function(e) {
        var pohmId = this.id;
        $.ajax({
          url : "${g.createLink(controller:'geoserver',action:'showPohmData')}" ,
          data : {id : pohmId},
          success: function(data) {
            $("div#dialog").dialog('open') ;
            document.getElementById('dialog').innerHTML = data;
          },
          complete: function() {
            $("div.ui-dialog-titlebar").hide();
          }
        });
      }) ;

      $("p.upldFile").live("click",function(e) {
        var id = this.id;
        $.ajax({
          url : "${g.createLink(controller:'upldFile',action:'showData')}" ,
          data : {id : id},
          success: function(data) {
            $("div#dialog").dialog('open') ;
            $("div#dialog").on('keydown', function(evt) {
              if (evt.keyCode === $.ui.keyCode.ESCAPE) {
                $("div#dialog").dialog('close');
              }
              evt.stopPropagation();
            });
            document.getElementById('dialog').innerHTML = data;
          },
          complete: function() {
            $("div.ui-dialog-titlebar").hide();
          }
        });
        e.stopPropagation();
      }) ;

      $("p.cerrar").live("click", function(e) {
        // alert("hello");
        var myDialog = $('div#dialog');
        if (myDialog.dialog('isOpen'))
          myDialog.dialog('close');
      });

      $("a.delete").live("click", function(e) {
        return confirm("¿Desea borrar los datos y el archivo de datos?");
      });

    }
  </script>

  <style type="text/css">
  div.ui-dialog-titlebar {display:hidden;}
  #pohmData {width:100%;display: table;height:100%}
  #pohmData td {color: #444444;}
  #pohmData td.dato {font-weight: bold; text-align: right;}
  #pohmData td.orig {color: #778899; text-align: center;}
  #pohmData td.nod {color: #99AABB; text-align: center;}
  #pohmData td.seng {color: #00008b; text-align: center;}
  #pohmData td.rell {color: #8b0000; text-align: center;}
  #pohmData td.unidad { text-align: center;}
  #pohmData td.relleno {color: red; text-align: left;}
  #pohmData th { text-align: center; }
  div.pohmGeneral {
    width: 240px;
    border: 1px solid #888888;
    float: left;
    padding: 16px 0 16px 0;
    margin: 0 16px 0 16px;
    background-color: #E1F2B6;
    -moz-border-radius: 4px;
    border-radius: 4px;
    clear:both;
  }
  table.pohm { width:100%;border-top: 1px solid #888888;background-color: transparent; }
  table.pohm tr { border-width:0; border-bottom: 1px solid #888888; }
  table.pohm td.detalle { background-color: #E1F2B6; border-width: 0 0 1px 0; }
  table.pohm td.fechaHora { background-color: #E1F2B6; border-width: 0 0 1px 0; }
  label { width:60px;text-align:left;color:#888888; }
  div.bar {
    background-color: #fc0000;
    color:white;
    height:100%;
    padding:0;
    margin:0;
    text-align:right;
    width:30%;
  }
  p.upldFile {
    font-weight: bolder;
    color: #00008b;
    cursor: pointer;
  }
  p.upldFile:hover {
    color:red;
  }
  div.titulo {
    position: relative;
    padding: 16px;
    margin: 8px 4px 4px 4px;
    font-size:12px;
    text-align: center;
    background-color: #718bb7;
    border: 2px blue;
    color:white;
    -moz-border-radius: 4px;
    border-radius: 4px;
    clear:both;
  }
  p.cerrar {
    font-size:10px;
    text-align:center;
    margin-top:4px;
    cursor:pointer;
    font-weight: bolder;
    color:black;
  }
  p.cerrar:hover {
    color:silver;
  }
  td.delete {
    cursor:pointer;
  }
  </style>

</head>
<body>
<a href="#list-upldFile" class="skip" tabindex="-1">
  <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>

<div id="menu">
  <ul class="menu">
    <li>
      <a class="home" href="${createLink(uri: '/')}">
        <g:message code="default.home.label"/>
      </a>
    </li>
  </ul>
</div>

<div class="crud" style="width:700px;margin-left:auto;margin-right:auto;">
  <div id="list-upldFile" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityNamePlural]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
      <thead>
      <tr>
        <g:sortableColumn property="nombre" title="${message(code: 'upldFile.nombre.label', default: 'Nombre')}" />
        <g:sortableColumn property="fechaCarga" title="${message(code: 'upldFile.fechaCarga.label', default: 'Fecha Carga')}" />
        <g:sortableColumn property="subido" style="text-align: center" title="${message(code: 'upldFile.subido.label', default: 'Subido')}" />
        <g:sortableColumn property="cargado" style="text-align: center" title="${message(code: 'upldFile.cargado.label', default: 'Cargado')}" />
        <g:sortableColumn property="falla" style="text-align: center" title="${message(code: 'upldFile.falla.label', default: 'Falla')}" />
        <sec:access expression="hasRole('ROLE_DATALOADER')">
          <th>Borrar</th>
        </sec:access>
      </tr>
      </thead>
      <tbody>
      <g:each in="${upldFileInstanceList}" status="i" var="upldFileInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
        <td><p id="${upldFileInstance.id}" class="upldFile">${fieldValue(bean: upldFileInstance, field: "nombre")}</p></td>
        <td><g:formatDate format="yyyy-MM-dd hh:mm" date="${upldFileInstance.fechaCarga}" /></td>
        <td style="font-weight:bolder;color:blue;text-align: center;">
          ${(upldFileInstance.subido)?'SI':'<span style="color:red;">NO</span>'}
        </td>
        <td style="font-weight:bolder;color:blue;text-align: center;">
          ${(upldFileInstance.cargado)?'SI':'<span style="color:red;">NO</span>'}
        </td>
        <td style="font-weight:bolder;color:blue;text-align: center;">
          ${(upldFileInstance.falla)?'<span style="color:red;">SI</span>':'NO'}
        </td>
        <sec:access expression="hasRole('ROLE_DATALOADER')">
          <td>
            <g:link class="delete" action="deleteData" id="${upldFileInstance?.id}">
              <g:img dir="images/skin" file="database_delete.png"/>
            </g:link>
          </td>
          </tr>
        </sec:access>
      </g:each>
      </tbody>
    </table>
    <div class="pagination">
      <g:if test="${upldFileInstanceTotal > maxDisplay}">
        <g:paginate total="${upldFileInstanceTotal}" />
      </g:if>
      <g:if test="${( upldFileInstanceTotal > 0 ) && ( upldFileInstanceTotal <= maxDisplay ) }">
        <p>${upldFileInstanceTotal} registros</p>
      </g:if>
      <g:if test="${ upldFileInstanceTotal == 0 }">
        <p>NO HAY registros</p>
      </g:if>
    </div>
    <sec:access expression="hasRole('ROLE_DATALOADER')">
      <div style="width:100%;height:28px;padding:8px 0 0 24px;">
        <div style="float:left;width:40%;text-align:right;margin-top:4px;"><spam style="margin:0px 8px 0 0;font-size:11px;">Subida masiva de datos desde archivo:</spam></div>
        <div style="float:left;width:60%;">
          <fileuploader:form upload="datos" successAction="uploadFile" successController="upldFile" errorAction="list2" errorController="uploadFile" />
        </div>
      </div>
    </sec:access>
  </div>
</div>

<div id="dialog" title="Despliegue de información" style="background-color:#b0c4de;"></div>

</body>
</html>
