<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
  <meta name="layout" content="main">
  <title><g:meta name="app.name"/> <g:meta name="app.version"/> - DATAMART</title>
  <link rel="stylesheet" href="${resource(dir:'openlayers', file: 'theme/default/style.css')}" type="text/css" />
  <script src="${resource(dir:'openlayers', file:'OpenLayers.js')}"></script>
  <link rel="stylesheet" href="${resource(dir:'openlayers', file:'style.css')}" type="text/css" />
  <style type="text/css">
    body {
      background: url("${resource(dir: 'images', file: 'box-bg.png')}") repeat;
      min-height:1080px;
      min-width:960px;
    }
    div.main_top {
      clear: both;
      background: url("${resource(dir: 'images', file: 'main-top.png')}") no-repeat top left ;
      border-width:0;
      height:96px;
      display:none;
    }
    div#menu {
      top:2px;
      width:100%;
      background:transparent url("${resource(dir: 'menu/images', file: 'header_bg.gif')}") repeat-x 0 0;
    }
    .container0 {
      position:relative;
      padding-left:200px;
      padding-right:240px;
      min-width: 800px;
    }
    .container0 .left-panel {
      width: 200px;
      position:absolute;
      left:0;
      top:0;
      background-color: #b0c4de;
    }
    .container0 .right-panel {
      width: 240px;
      position:absolute ;
      right:0;
      top:0;
      bottom:0;
      background-color: #add8e6;
    }
    .container0 .center-panel {}
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
    div.smallmap {
      position:relative;
      height:700px;
      min-width:340px;
      width:100%;
      margin-left:auto;
      margin-right:auto;
      border: 1px silver solid;
      bottom:0;
    }
    div.tituloSelection {
      margin-top: 16px;
      text-align: center;
      font-weight: bold ;
    }
    td {height:16px;}
    td.selection a {
      color: #000044;
      text-decoration: none;
    }
    td.selected {
      background-color: #718bb7;
    }
    td.selected a, p a {
      color: #f5f5dc;
      background-color: #718bb7;
      text-decoration: none;
    }
    div.pagination {
      -moz-border-radius: 4px;
      border-radius: 4px;
      background-color: #f5f5f5;
    }
    div.export {
      clear:both;
      opacity: 0.8;
      background-color:#f5f5f5;
      margin-top: 4px;
      padding: 4px 16px 4px 16px;
      -moz-border-radius: 4px;
      border-radius: 4px;
    }
    SPAN.currentStep {
      background-color: #8b0000;
      color: #f5f5f5;
    }
  </style>

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
    div.domtit {
      width:100%;
      background-color:white;
      text-align: center;
      color:#0000ff;
      padding:4px;
    }
    div.domcontainer {
      overflow-y: auto ;
      max-height:200px;
      overflow-x:hidden;
      margin:8px;
      -moz-border-radius: 8px;
      border-radius: 8px;
      border: 1px white solid;
    }
  </style>

  <script type="text/javascript">

    var map;
    format = 'image/png';
    var modo='<%=modo%>'
    function init(){

      var maxExtend = new OpenLayers.Bounds(-733034.9639, 9445137.7208, 1147737.71, 10186369.7608);

      var bounds = new OpenLayers.Bounds( <%=box%> );

      map = new OpenLayers.Map('map', {
        controls: [
          new OpenLayers.Control.Navigation({
            dragPanOptions: {
              enableKinetic: true
            }
          }),
          new OpenLayers.Control.PanZoomBar(),
          new OpenLayers.Control.LayerSwitcher({'ascending':false}),
          new OpenLayers.Control.ScaleLine(),
          new OpenLayers.Control.MousePosition(),
          // new OpenLayers.Control.OverviewMap(),
          new OpenLayers.Control.KeyboardDefaults()
        ],
        allOverlays: true,
        numZoomLevels: 16,
        maxExtent: maxExtend,
        restrictedExtent: maxExtend,
        maxResolution: 2795.3264132812546,
        projection: "EPSG:32717",
        units: 'm'
      });

      map.fractionalZoom = true;

      <g:geoserverMaps geoserver="$geoserver" modo="$modo" viewparams="${viewparams?:''}" geomapa="$geomapa" otrosMapas="$otrosMapas"/>

      map.events.register('click', map, function (e) {
        var lonLat = map.getLonLatFromPixel(e.xy);
        $.ajax({
          url:"${g.createLink(controller:'geoserver',action:'click')}",
          data: {
            scale: map.getScale(),
            longitud: lonLat.lon,
            latitud: lonLat.lat,
            paramController: "${returnp.controller}",
            paramAction: "${returnp.action}",
            paramId: "${returnp.paramId}"
          },
          success: successPohm
        });
      });

      function successPohm(data) {
        document.getElementById('clickresult').innerHTML = data;
      }

      map.events.register('zoomend', map, function () {
        var scale = map.getScale()*2.54/100000 ;
        var extent = "'" + map.getExtent() + "'";
        $.ajax({
          url: "${g.createLink(controller:'geoserver',action:'click')}" ,
          data: {
            scale : scale,
            extent : extent
          },
          success: function(data) {
            document.getElementById('clickresult').innerHTML = data;
            mapaRiosD.setVisibility(scale <= 40);
            mapaRiosS.setVisibility(scale <= 10);
            mapaHI.setVisibility(scale <= 40);
            mapaMET.setVisibility(scale <= 30);
            mapaHG.setVisibility(scale <= 10);
            if(mapaCantones) mapaCantones.setVisibility(scale <= 40);
            if(mapaParroquias) mapaParroquias.setVisibility(scale <= 15);
            if(mapaCuencas) mapaCuencas.setVisibility(scale <= 40);
            if(mapaSubcuencas) mapaSubcuencas.setVisibility(scale <= 15);
            if(mapaNiveles2) mapaNiveles2.setVisibility(scale <= 40);
            if(mapaNiveles3) mapaNiveles3.setVisibility(scale <= 30);
            if(mapaNiveles4) mapaNiveles4.setVisibility(scale <= 20);
            if(mapaNiveles5) mapaNiveles5.setVisibility(scale <= 10);
          }
        });
      });
      map.zoomToExtent(bounds) ;

      // JQUERY !!
      $("div#dialog").dialog({
        position: "top",
        show: "blind",
        hide: "puff",
        width: "920",
        height: "760",
        resizable: true,
        autoOpen: false }).on('keydown', function(evt) {
            if (evt.keyCode === $.ui.keyCode.ESCAPE) {
              dialog.dialog('close');
            }
            evt.stopPropagation();
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

      $('form').live('submit',function(event) {
        event.preventDefault();
        var theForm = $(this);
        $.ajax({
          url: theForm.attr('action'),
          data: theForm.serialize(),
          success: function(data) {
            $("div#dialog").dialog('open') ;
            document.getElementById('dialog').innerHTML = data;
          }
        });
        return false;
      });

    }

  </script>

</head>
<body onload="init()">
  <div id="menu">
    <ul class="menu">
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link controller="geoserver" action="showInfo" id="Demarcaciones">Demarcaciones</g:link></li>
      <li><g:link controller="geoserver" action="showInfo" id="Sistemas">Sistemas</g:link></li>
      <li><g:link controller="geoserver" action="showInfo" id="Niveles1">Pfafstetter</g:link></li>
      <li><g:link controller="geoserver" action="showInfo" id="Provincias">Provincias</g:link></li>
    </ul>
  </div>

  <div class="container0">

  <div class="left-panel" style="bottom: 0;">
    <div class="main-list">
      <div class="titulo" style="height:78px;">
        <p><%=tituloPrincipal%></p>
        <p style="font-size: 14px;"><%=zona%></p>
        <g:form method="post" >
          <g:hiddenField name="paramController" value="${returnp.controller}" />
          <g:hiddenField name="paramAction" value="${returnp.action}" />
          <g:hiddenField name="paramId" value="${returnp.id}" />
          <g:hiddenField name="dominio" value="HI" />
          <fieldset class="buttons" style="margin: 8px 2px 8px 2px;">
            <!-- <g:actionSubmit class="save" action="showData" value="Desplegar Información" /> -->
          </fieldset>
        </g:form>
      </div>
      <div style="overflow-y: auto;height:473px;overflow-x:hidden;">
        <table style="margin: 8px 0 0 8px;table-layout:fixed;">
          <thead><tr><th><%=tituloSeleccion%></th></tr></thead>
          <tbody>
          <g:each in="${mainList}" var="ml">
            <tr>
              <td class="${(selectedCdgo == ml.id)?'selected':'selection'}" style="border-bottom: 1px dotted #f5f5f5;">
                <g:if test="${nextModo != '*'}">
                  <div style="float:left;text-align:right;">
                    <g:link controller="${ml.geoserver}" action="${ml.action}" id="${ml.id}"><%=ml.nombre%></g:link>
                  </div>
                  <div style="float:right;width:20px;">
                    <g:link controller="${ml.geoserver}" action="showInfo2" id="${ml.id}">
                      <g:img dir="images/skin" file="76.png"/>
                    </g:link>
                  </div>
                </g:if>
                <g:if test="${nextModo == '*'}">
                  <div>
                    <g:link controller="${ml.geoserver}" action="${ml.action}" id="${ml.id}"><%=ml.nombre%></g:link>
                  </div>
                </g:if>
              </td>
            </tr>
          </g:each>
          </tbody>
        </table>
      </div>
      <div class="titulo" style="height:24px;vertical-align:middle;margin:16px 8px 16px 4px;box-shadow: 4px 4px 5px #888;">
        <p>CLICK sobre el nombre</p>
        <g:if test="${nextModo != '*'}">
          <p>CLICK sobre imagen <span style='top: 8px;'><g:img dir="images/skin" file="76.png"/></span></p>
        </g:if>
      </div>
    </div>
  </div>

  <div class="center-panel">
    <div id="map" class="smallmap">
    </div>
  </div>

  <div id="clickresult" class="right-panel">
    <g:render template="listPohms"/>
  </div>

  </div>

  <div id="dialog" title="Despliegue de información" style="background-color:#b0c4de;"></div>

</body>
</html>
