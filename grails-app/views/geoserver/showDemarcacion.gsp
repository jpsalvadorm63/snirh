<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>DATAMART !</title>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
  <style type="text/css">
  body { background: url("${resource(dir: 'images', file: 'box-bg.png')}") repeat; min-height:1080px;min-width:960px; }
  div.main_top { clear: both; background: url("${resource(dir: 'images', file: 'main-top.png')}") no-repeat top left ; border-width:0;height:96px;}
  div#menu {top:2px;width:100%;background:transparent url("${resource(dir: 'menu/images', file: 'header_bg.gif')}") repeat-x 0 0;}
  div#description { clear:both;width:100%;height:16px;vertical-align: middle; background-color: white;margin: 8px 0 0 16px }
  </style>
  <script type="text/javascript" src="${resource(dir: 'menu', file: 'jquery.js')}"></script>
  <script type="text/javascript" src="${resource(dir: 'menu', file: 'menu.js')}"></script>
  <link rel="stylesheet" href="${resource(dir: 'menu', file: 'menu.css')}">

  <link rel="stylesheet" type="text/css" href="${geoserver}/openlayers/theme/default/style.css"/>
  <script src="${geoserver}/openlayers/OpenLayers.js" type="text/javascript"></script>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'mapas.css')}">
  <script defer="defer" type="text/javascript">
    var map;
    var lyrInfo0;
    /*var lyrDemarcacion;*/
    var pureCoverage = false;
    // pink tile avoidance
    OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
    // make OL compute scale according to WMS spec
    OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;

    function init(){
      // if this is just a coverage or a group of them, disable a few items,
      // and default to jpeg format
      format = 'image/png';

      var bounds = new OpenLayers.Bounds(
          <%=box%>
      );

      var options = {
        controls: [],
        maxExtent: bounds,
        maxResolution: 2795.3264132812546,
        projection: "EPSG:32717",
        units: 'm'
      };
      map = new OpenLayers.Map('map', options);

      var lyrInfo0 = new OpenLayers.Layer.WMS(
          "Demarcaciones", "${geoserver}/HIDRO/wms",
          {
            <%=viewparams%>
            LAYERS: "${geomapa}",
            STYLES: '',
            format: format
          },
          {
            singleTile: false,
            ratio: 1,
            isBaseLayer: true,
            yx : {'EPSG:32717' : false}
          }
      );

      /*lyrDemarcacion = new OpenLayers.Layer.WMS(
       "HIDRO:demarcacion - Tiled", "${geoserver}/HIDRO/wms",
       {
       VIEWPARAMS: 'demrcdgo:DHGU',
       LAYERS: 'HIDRO:demarcacion',
       STYLES: '',
       format: format,
       tiled: true,
       tilesOrigin : map.maxExtent.left + ',' + map.maxExtent.bottom,
       transparent: true
       },
       {
       buffer: 0,
       displayOutsideMaxExtent: true,
       isBaseLayer: false,
       yx : {'EPSG:32717' : false}
       }

       );*/

      map.addLayers([lyrInfo0]);

      // build up all controls
      map.addControl(new OpenLayers.Control.PanZoomBar({
        position: new OpenLayers.Pixel(2, 15)
      }));

      map.addControl(new OpenLayers.Control.Navigation());
      //map.addControl(new OpenLayers.Control.Scale($('scale')));
      //map.addControl(new OpenLayers.Control.MousePosition({element: $('location')}));
      // map.addControl(new OpenLayers.Control.LayerSwitcher());
      map.zoomToExtent(bounds);

      // support GetFeatureInfo
      map.events.register('click', map, function (e) {
        // document.getElementById('nodelist').innerHTML = "cargando información . . .";
        var params = {
          REQUEST: "GetFeatureInfo",
          EXCEPTIONS: "application/vnd.ogc.se_xml",
          BBOX: map.getExtent().toBBOX(),
          SERVICE: "WMS",
          INFO_FORMAT: 'text/html',
          QUERY_LAYERS: map.layers[0].params.LAYERS,
          FEATURE_COUNT: 50,
          Layers: ['HIDRO:demarcaciones'],
          WIDTH: map.size.w,
          HEIGHT: map.size.h,
          format: format,
          styles: map.layers[0].params.STYLES,
          srs: map.layers[0].params.SRS};

        // handle the wms 1.3 vs wms 1.1 madness
        if(map.layers[0].params.VERSION == "1.3.0") {
          params.version = "1.3.0";
          params.j = parseInt(e.xy.x);
          params.i = parseInt(e.xy.y);
        } else {
          params.version = "1.1.1";
          params.x = parseInt(e.xy.x);
          params.y = parseInt(e.xy.y);
        }

        // merge filters
        if(map.layers[0].params.CQL_FILTER != null) {
          params.cql_filter = map.layers[0].params.CQL_FILTER;
        }
        if(map.layers[0].params.FILTER != null) {
          params.filter = map.layers[0].params.FILTER;
        }
        if(map.layers[0].params.FEATUREID) {
          params.featureid = map.layers[0].params.FEATUREID;
        }

        var lonLat = map.getLonLatFromPixel(e.xy);
        alert(lonLat.lon + ' ' + lonLat.lat) ;

        OpenLayers.loadURL("${geoserver}/HIDRO/wms", params, this, setHTML, setHTML);
        OpenLayers.Event.stop(e);
      });
    }

    // sets the HTML provided into the nodelist element
    function setHTML(response){
      document.getElementById('nodelist').innerHTML = response.responseText;
    }

  </script>

</head>

<body onload="init()">
<div class="main_top" ></div>
<div id="menu">
  <ul class="menu">
    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    <li><g:link controller="geoserver" action="showInfo" id="Demarcaciones">Demarcaciones</g:link></li>
    <li><g:link controller="geoserver" action="showInfo" id="Sistemas">Sistemas</g:link></li>
    <li><g:link controller="geoserver" action="showInfo" id="Provincias">Provincias</g:link></li>
  </ul>
</div>

<div style="width: 1024px; margin-left:auto;margin-right:auto;background-color: #e0ffff;">

  <g:if test="${mainList != []}">
    <div id="main-list" style="width:200px;float:left;margin-top:28px;">
      <div style="height:24px; padding: 24px 24px 4px 16px;font-size:12px;background-color:green;color:white;"><%=tituloSeleccion%></div>
      <div style="height:500px; overflow-x:hidden; overflow-y:scroll;">
        <table style="margin: 4px;">
          <tbody>
          <g:each in="${mainList}" status="i" var="ml">
            <tr><td><g:link controller="${ml.geoserver}" action="${ml.action}" id="${ml.id}"><%=ml.nombre%></g:link></td></tr>
          </g:each>
          </tbody>
        </table>
      </div>
    </div>
  </g:if>

  <div style="float:left;">
    <div id="description" style="clear:both;">
      <h1 style="text-align:center;"><%=tituloPrincipal%></h1>
    </div>
    <div id="map" style="width:600px;height:600px;"></div>
  </div>

  <div id="filtro" style="width:200px;float:right;">
    <g:form method="post">
      <div>
        <label for="desde" style="width:60px">Desde</label>
        <g:jqDatePicker name="desde" value="1/01/1960"/>
      </div>
      <div>
        <label for="hasta" style="width:60px">hasta</label>
        <g:jqDatePicker name="hasta" value="31/12/2014"/>
      </div>
      <fieldset class="buttons">
        <g:actionSubmit class="save" action="showData" value="FILTRAR !" />
      </fieldset>
    </g:form>
  </div>

</div>

</body>
</html>
