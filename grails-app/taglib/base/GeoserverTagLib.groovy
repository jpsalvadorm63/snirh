package base

class GeoserverTagLib {

  def geoserverMaps = {attrs, body ->
    // println attrs.modo
    def out = out
    def mapas = []

    out.println '        var mapaBase = null;'
    out.println '        var mapaCuencas = null;'
    out.println '        var mapaSubcuencas = null;'
    out.println '        var mapaCantones = null;'
    out.println '        var mapaParroquias = null;'
    out.println '        var mapaNiveles2 = null;'
    out.println '        var mapaNiveles3 = null;'
    out.println '        var mapaNiveles4 = null;'
    out.println '        var mapaNiveles5 = null;'

    out.println buildMapaBase(attrs); mapas << 'mapaBase'
    if(attrs.modo == 'Sistemas' ||attrs.modo == 'Sistema' ) {
      out.println buildOtrosMapaBase('mapaCuencas','Cuencas','cuencas',attrs);           mapas << 'mapaCuencas'
      out.println buildOtrosMapaBase('mapaSubcuencas','Subcuencas','subcuencas',attrs);  mapas << 'mapaSubcuencas'
    }
    if(attrs.modo == 'Cuencas' ||attrs.modo == 'Cuenca' ) {
      out.println buildOtrosMapaBase('mapaSubcuencas','Subcuencas','subcuencas',attrs);  mapas << 'mapaSubcuencas'
    }
    if(attrs.modo == 'Provincias' || attrs.modo == 'Provincia') {
      out.println buildOtrosMapaBase('mapaCantones','Cantones','cantones',attrs);         mapas << 'mapaCantones'
      out.println buildOtrosMapaBase('mapaParroquias','Parroquias','parroquiass',attrs);  mapas << 'mapaParroquias'
    }
    if(attrs.modo == 'Cantones' || attrs.modo == 'Canton') {
      out.println buildOtrosMapaBase('mapaParroquias','Parroquias','parroquiass',attrs);  mapas << 'mapaParroquias'
    }
    if(attrs.modo == 'Niveles1' || attrs.modo == 'Nivel1') {
      out.println buildOtrosMapaBase('mapaNiveles2','Niveles2','niveles2',attrs);         mapas << 'mapaNiveles2'
      out.println buildOtrosMapaBase('mapaNiveles3','Niveles3','niveles3',attrs);         mapas << 'mapaNiveles3'
      out.println buildOtrosMapaBase('mapaNiveles4','Niveles4','niveles4',attrs);         mapas << 'mapaNiveles4'
      out.println buildOtrosMapaBase('mapaNiveles5','Niveles5','niveles5',attrs);         mapas << 'mapaNiveles5'
    }
    if(attrs.modo == 'Niveles2' || attrs.modo == 'Nivel2') {
      out.println buildOtrosMapaBase('mapaNiveles3','Niveles3','niveles3',attrs);         mapas << 'mapaNiveles3'
      out.println buildOtrosMapaBase('mapaNiveles4','Niveles4','niveles4',attrs);         mapas << 'mapaNiveles4'
      out.println buildOtrosMapaBase('mapaNiveles5','Niveles5','niveles5',attrs);         mapas << 'mapaNiveles5'
    }
    if(attrs.modo == 'Niveles3' || attrs.modo == 'Nivel3') {
      out.println buildOtrosMapaBase('mapaNiveles4','Niveles4','niveles4',attrs);         mapas << 'mapaNiveles4'
      out.println buildOtrosMapaBase('mapaNiveles5','Niveles5','niveles5',attrs);         mapas << 'mapaNiveles5'
    }
    if(attrs.modo == 'Niveles4' || attrs.modo == 'Nivel4') {
      out.println buildOtrosMapaBase('mapaNiveles5','Niveles5','niveles5',attrs);         mapas << 'mapaNiveles5'
    }
    out.println buildMapaNoBase('mapaRiosD','Rios Dobles','riosD',attrs);         mapas << 'mapaRiosD'
    out.println buildMapaNoBase('mapaRiosS','Rios Simples','riosS',attrs);        mapas << 'mapaRiosS'
    out.println buildMapaNoBase('mapaHI','Hidrologia','hidrologia',attrs);        mapas << 'mapaHI'
    out.println buildMapaNoBase('mapaMET','Meteorología','meteorologia',attrs);   mapas << 'mapaMET'
    out.println buildMapaNoBase('mapaHG','Hidrogeología','hidrogeologia',attrs);  mapas << 'mapaHG'

    out.println "      map.addLayers($mapas);"
  }

      String buildMapaBase(attrs) {
        """mapaBase = new OpenLayers.Layer.WMS('${attrs.modo}','${attrs.geoserver}/HIDRO/wms',
          { ${attrs.viewparams}
            layers: '${attrs.geomapa}',
            format: format,
            transparent:'true',
            tiled: 'true',
            tilesorigin: [map.maxExtent.left, map.maxExtent.bottom]
          },
          { isBaseLayer: true ,
            visibility: true,
            yx : {'EPSG:32717' : false},
            buffer:0
          });"""
      }

      String buildOtrosMapaBase(nombre,detalle,geomapa,attrs) {
        """        $nombre = new OpenLayers.Layer.WMS('$detalle','${attrs.geoserver}/HIDRO/wms',
            { ${attrs.viewparams}
              layers: '${geomapa}',
              format: format,
              transparent:"true",
              tiled: 'true',
              tilesorigin: [map.maxExtent.left, map.maxExtent.bottom]
            },
            { isBaseLayer: false,
              visibility: false,
              yx : {'EPSG:32717' : false},
              buffer:0
            } );"""
      }

      String buildMapaNoBase(nombre,detalle,layersPrefix,attrs) {
        """        $nombre = new OpenLayers.Layer.WMS('$detalle','${attrs.geoserver}/HIDRO/wms',
          { ${attrs.viewparams}
            layers: '${layersPrefix}${attrs.otrosMapas}',
            format: format,
            transparent:"true",
            tiled: 'true',
            tilesorigin: [map.maxExtent.left, map.maxExtent.bottom]
          },
          { isBaseLayer: false,
            visibility: false,
            yx : {'EPSG:32717' : false},
            buffer:0
          } );"""
      }

}
