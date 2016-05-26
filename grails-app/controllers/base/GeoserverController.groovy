package base

import base.Demarcacion
import base.DivisionPolitica
import base.Sistema
import java.nio.file.Files
import java.io.File
import org.apache.commons.io.IOUtils
import groovy.sql.Sql
import java.sql.Connection
import org.hibernate.jdbc.Work
import pl.touk.excel.export.WebXlsxExporter

import grails.plugins.springsecurity.Secured


class GeoserverController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  static geoserver = 'http://hidromet.senagua.gob.ec:8080/geoserver'
  //static geoserver = 'http://192.168.1.7:8080/geoserver'

  def index() {
    initSelectedPohms()
    redirect(action: "showInfo", params: params)
  }

    def initSelectedPohms() {
      session.latitud = null
      session.longitud = null
      session.pohmsHI = []
      session.pohmsMET = []
      session.pohmsHG = []
    }

  def nextModo = [
      'Demarcaciones':'Demarcacion', 'Demarcacion':'*',
      'Sistemas':'Sistema','Sistema':'Cuencas','Cuencas':'Cuenca','Cuenca':'Subcuencas','Subcuencas':'Subcuenca','Subcuenca':'*',
      'Provincias':'Provincia','Provincia':'Cantones','Cantones':'Canton','Canton':'Parroquias','Parroquias':'Parroquia','Parroquia':'*',
      'Niveles1':'Nivel1','Nivel1':'Niveles2','Niveles2':'Nivel2','Nivel2':'Niveles3','Niveles3':'Nivel3','Nivel3':'Niveles4','Niveles4':'Nivel4','Nivel4':'Niveles5','Niveles5':'Nivel5','Nivel5':'*'
  ]

  def nivel = [
      'Demarcaciones':1, 'Demarcacion':2,
      'Sistemas':1,'Sistema':2,'Cuencas':3,'Cuenca':4,'Subcuencas':5,'Subcuenca':6,
      'Provincias':1,'Provincia':2,'Cantones':3,'Canton':4,'Parroquias':5,'Parroquia':6,
      'Niveles1':1,'Nivel1':2,'Niveles2':3,'Nivel2':4,'Niveles3':5,'Nivel3':6,'Niveles4':7,'Nivel4':8,'Niveles5':9,'Nivel5':10
  ]

  def viewparams = [
      'Demarcacion':'demrcdgo',
      'Sistema':'sistcdgo','Cuencas':'sistcdgo','Cuenca':'sistcdgo','Subcuencas':'sistcdgo','Subcuenca':'sistcdgo',
      'Provincia':'divpcdgo','Cantones':'divpcdgo','Canton':'divpcdgo','Parroquias':'divpcdgo','Parroquia':'divpcdgo',
      'Niveles1':'pfabcdgo','Niveles2':'pfabcdgo','Niveles3':'pfabcdgo','Niveles4':'pfabcdgo','Niveles5':'pfabcdgo',
      'Nivel1':'pfabcdgo','Nivel2':'pfabcdgo','Nivel3':'pfabcdgo','Nivel4':'pfabcdgo','Nivel5':'pfabcdgo'
  ]

  def tituloPrincipal = [
      'Demarcaciones':'DEMARCACIONES A NIVEL NACIONAL','Demarcacion':'DEMARCACION',
      'Sistemas':'SISTEMAS A NIVEL NACIONAL','Sistema':'SISTEMA','Cuencas':'CUENCAS DEL SISTEMA','Cuenca':'CUENCA','Subcuencas':'SUBCUENCAS DE LA CUENCA','Subcuenca':'SUBCUENCA',
      'Niveles1':'PFAFSTETTER A NIVEL NACIONAL','Nivel1':'PFAFSTETTER - NIVEL 1',
      'Niveles2':'PFAFSTETTER - NIVEL 2',       'Nivel2':'PFAFSTETTER - NIVEL 2',
      'Niveles3':'PFAFSTETTER - NIVEL 3',       'Nivel3':'PFAFSTETTER - NIVEL 3',
      'Niveles4':'PFAFSTETTER - NIVEL 4',       'Nivel4':'PFAFSTETTER - NIVEL 4',
      'Niveles5':'PFAFSTETTER - NIVEL 5',       'Nivel5':'PFAFSTETTER - NIVEL 5',
      'Provincias':'PROVINCIAS A NIVEL NACIONAL','Provincia':'PROVINCIA','Cantones':'CANTONES DE LA PROVINCIA','Canton':'CANTON','Parroquias':'PARROQUIAS DEL CANTON','Parroquia':'PARROQUIA'
  ]

  def tituloSeleccion = [
      'Demarcaciones':'SELECCIONE UNA DEMARCACION','Demarcacion':'DEMARCACION',
      'Sistemas':'SELECCIONE UN SISTEMA','Sistema':'SELECCIONE UN SISTEMA',
      'Cuencas':'SELECCIONE UNA CUENCA','Cuenca':'SELECCIONE UNA CUENCA',
      'Subcuencas':'SELECCIONE UNA SUBCUENCA','Subcuenca':'SELECCIONE UNA SUBCUENCA',
      'Niveles1':'SELECCIONE UN NIVEL 1','Nivel1':'SELECCIONE UN NIVEL 1',
      'Niveles2':'SELECCIONE UN NIVEL 2',      'Nivel2':'SELECCIONE UN NIVEL 2',
      'Niveles3':'SELECCIONE UN NIVEL 3',      'Nivel3':'SELECCIONE UN NIVEL 3',
      'Niveles4':'SELECCIONE UN NIVEL 4',      'Nivel4':'SELECCIONE UN NIVEL 4',
      'Niveles5':'SELECCIONE UN NIVEL 5',      'Nivel5':'SELECCIONE UN NIVEL 5',
      'Provincias':'SELECCIONE UNA PROVINCIA','Provincia':'SELECCIONE UNA PROVINCIA',
      'Cantones':'SELECCIONE UNA CANTON','Canton':'SELECCIONE UNA CANTON',
      'Parroquias':'SELECCIONE UNA PARROQUIA','Parroquia':'SELECCIONE UNA PARROQUIA'
  ]

  def objs = [
      'Demarcaciones':Demarcacion, 'Demarcacion':Demarcacion,
      'Sistemas':Sistema, 'Sistema':Sistema, 'Cuencas':Sistema, 'Cuenca':Sistema, 'Subcuencas':Sistema, 'Subcuenca':Sistema,
      'Niveles1':Pfabsteter,'Nivel1':Pfabsteter,'Niveles2':Pfabsteter,'Nivel2':Pfabsteter,'Niveles3':Pfabsteter,'Nivel3':Pfabsteter,'Niveles4':Pfabsteter,'Nivel4':Pfabsteter,'Niveles5':Pfabsteter,'Nivel5':Pfabsteter,
      'Provincia':DivisionPolitica,'Provincias':DivisionPolitica,'Canton':DivisionPolitica,'Cantones':DivisionPolitica,'Parroquia':DivisionPolitica,'Parroquias':DivisionPolitica
  ]

  def mapas = [
      'Demarcaciones':'HIDRO:demarcaciones','Demarcacion':'HIDRO:demarcacion',
      'Sistemas':'HIDRO:sistemas','Sistema':'HIDRO:cuencasXSistema',
      'Cuencas':'HIDRO:cuencasXSistema','Cuenca':'HIDRO:subcuencasXCuenca',
      'Subcuencas':'HIDRO:subcuencasXCuenca','Subcuenca':'subcuenca',
      'Niveles1':'HIDRO:niveles','Nivel1':'HIDRO:nivelInfXnivelSup',
      'Niveles2':'HIDRO:nivelInfXnivelSup','Nivel2':'HIDRO:nivelInfXnivelSup',
      'Niveles3':'HIDRO:nivelInfXnivelSup','Nivel3':'HIDRO:nivelInfXnivelSup',
      'Niveles4':'HIDRO:nivelInfXnivelSup','Nivel4':'HIDRO:nivelInfXnivelSup',
      'Niveles5':'HIDRO:nivelInfXnivelSup','Nivel5':'HIDRO:nivel',
      'Provincias':'HIDRO:provincias','Provincia':'HIDRO:cantonesXProvincia',
      'Cantones':'HIDRO:cantonesXProvincia','Canton':'HIDRO:parroquiasXCanton',
      'Parroquias':'HIDRO:parroquiasXCanton','Parroquia':'HIDRO:parroquias'
  ]

  def showInfo() {
    params['paramId'] = params['id']
    def returnp = params

    initSelectedPohms()

    session.condicion = ''
    session.modo = ((params.id==null)?'Niveles1':params.id )
       session.modo1 = null
       session.modo2 = null
       session.modo3 = null
       session.modo4 = null
       session.modo5 = null
       session.modo6 = null

    if (session.modo == 'Demarcaciones')
      session.otrosMapas = "XDemarcacion"
    else
    if (session.modo == 'Provincias')
      session.otrosMapas = "XDivp"
    else
    if (session.modo == 'Sistemas')
      session.otrosMapas = "XSistema"
    else
    if (session.modo == 'Niveles1')
      session.otrosMapas = "XNivel"

    def geomapa = mapas[session.modo]
    def list = buildList(session.modo,null)
    def cajaTxt = calcBox(session.modo,null)
    def tPrincipal = tituloPrincipal[session.modo]
    def tSeleccion = tituloSeleccion[session.modo]
    def viewParams = ''

    render( view : "showInfo",
            model : [ nivel : session.nivel,
                      modo : session.modo,
                      nextModo: nextModo[session.modo],
                      geoserver : geoserver,
                      geomapa : geomapa,
                      otrosMapas : '',
                      viewparams : "$viewParams",
                      mainList : list,
                      box : cajaTxt,
                      tituloPrincipal : "${tPrincipal}",
                      tituloSeleccion : "${tSeleccion}",
                      returnp:returnp,
                      selectedCdgo:'',
                      zona: '',
                      explorerAction: '',
                      explorerId: '',
                      mapaVisible:['false','false','false','false','false'] ] )
  }

  def simples = ['Sistema','Cuenca','Subcuenca'] + ['Provincia','Canton','Parroquia'] + ['Nivel1','Nivel2','Nivel3','Nivel4','Nivel5']

  def showInfo1() {
    if(session.modo == null) {
      flash.message = "ERROR: Sesión finalizada. Ingrese nuevamente al sistema"
      redirect controller: 'login', action:'auth'
      return
    }
    def returnp = params

    session.condicion = ''
    session.modo1 = (session.modo1 != null)?session.modo1:nextModo[session.modo]
    session.cdgo1 = params.id

    if (session.modo1 == 'Demarcacion')
      session.condicion = "and p.demarcacion.id = ${Demarcacion.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Sistema')
      session.condicion = "and p.sistema.id = ${Sistema.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Cuenca')
      session.condicion = "and p.cuenca.id = ${Sistema.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Subcuenca')
      session.condicion = "and p.subcuenca.id = ${Sistema.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Provincia')
      session.condicion = "and p.provincia.id = ${DivisionPolitica.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Canton')
      session.condicion = "and p.canton.id = ${DivisionPolitica.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Parroquia')
      session.condicion = "and p.parroquia.id = ${DivisionPolitica.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Nivel1')
      session.condicion = "and p.pfabsteter1.id = ${Pfabsteter.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Nivel2')
      session.condicion = "and p.pfabsteter2.id = ${Pfabsteter.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Nivel3')
      session.condicion = "and p.pfabsteter3.id = ${Pfabsteter.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Nivel4')
      session.condicion = "and p.pfabsteter4.id = ${Pfabsteter.findByCodigo(session.cdgo1).id}"
    if (session.modo1 == 'Nivel5')
      session.condicion = "and p.pfabsteter5.id = ${Pfabsteter.findByCodigo(session.cdgo1).id}"

    def dad = null
    def zona = ''
    if (params.supId != null) {
      dad = params.supId
      zona = "${(objs[session.modo1]).findByCodigo(params.supId).nombre}"
    } else {
      dad = params.id
      zona = "${(objs[session.modo1]).findByCodigo(params.id).nombre}"
    }
    def geomapa = mapas[session.modo1]
    def list = buildList(session.modo1,dad )
    def cajaTxt = calcBox(session.modo1,session.cdgo1)
    def tPrincipal = tituloPrincipal[session.modo1]
    def tSeleccion = tituloSeleccion[session.modo1]
    def viewParams = "VIEWPARAMS:'${viewparams[session.modo1]}:${session.cdgo1}',"
    def selectedCdgo = ''
    def explorerAction = ''
    def explorerId = ''

    if( ( simples.contains(session.modo1) && nextModo[session.modo1] != '*' ) ) {
      selectedCdgo = params.id
      explorerAction = 'showInfo2'
      explorerId = params.id
    }

    def mynivel = nivel[session.modo1]
    render( view : "showInfo",
            model : [ nivel : session.nivel,
                      modo : session.modo1,
                      nextModo: nextModo[session.modo1],
                      geoserver : geoserver,
                      geomapa : geomapa,
                      otrosMapas : session.otrosMapas,
                      viewparams : "$viewParams",
                      mainList : list,
                      box : cajaTxt,
                      tituloPrincipal : "${tPrincipal}",
                      tituloSeleccion : "${tSeleccion}",
                      returnp : returnp,
                      selectedCdgo : selectedCdgo,
                      zona : zona,
                      explorerAction : explorerAction,
                      explorerId : explorerId ] )
  }

  def showInfo2() {

    if(session.modo == null) {
      flash.message = "ERROR: Sesión finalizada. Ingrese nuevamente al sistema"
      redirect controller: 'login', action:'auth'
      return
    }

    def returnp = params
    params.supId = params.id

    // println "showInfo2(): cambio de modo, ${session.modo1} -> ${nextModo[session.modo1]}"
    def nuevoModo = nextModo[session.modo1]
    session.modo1 = (nuevoModo != '*')?nuevoModo:session.modo1
    redirect(action: "showInfo1", params: params)
  }

          String calcBox(modo,codigo) {
            def geom
            if (codigo == null)
              geom = base.DivisionPolitica.findByCodigo('CO')
            else
              geom = (objs[modo]).findByCodigo(codigo)
            def strBox = "${geom.minx} , ${geom.miny}, ${geom.maxx}, ${geom.maxy}"

            return strBox
          }

  def buildList(modo,codigo) {

  def list = []
    if(modo == 'Demarcaciones' || modo == 'Demarcacion') {
      Demarcacion.list(sort:'nombre').each {
        list.add([id:it.codigo, controller:'geoserver', action:'showInfo1', nombre:it.nombre])
      }
    }
    else
    if(modo == 'Sistemas' || modo == 'Sistema') {
      Sistema.findAllByNivel('SISTEMA',[sort:'nombre']).each {
        list.add([id:it.codigo, controller:'geoserver', action:'showInfo1', nombre:it.nombre])
      }
    }
    else
    if(modo == 'Provincias' || modo == 'Provincia') {
      DivisionPolitica.findAllByNivel('PROVINCIA',[sort:'nombre']).each {
        list.add([id:it.codigo, controller:'geoserver', action:'showInfo1', nombre:it.nombre])
      }
    }
    else
    if(modo == 'Niveles1' || modo == 'Nivel1') {
      try {
        Pfabsteter.findAllByNivel('NIVEL1',[sort:'nombre']).each {
          list.add([id:it.codigo, controller:'geoserver', action:'showInfo1', nombre:it.nombre])
        }
      } catch (e) {
        println e
      }
    }
    else
    if(['Cuencas','Subcuencas','Cantones','Parroquias','Niveles2','Niveles3','Niveles4','Niveles5'].contains(modo)) {
      int dad = (objs[modo]).findByCodigo(codigo).id
      (objs[modo]).findAllByDad(dad,[sort:'nombre']).each {
        list.add([id:it.codigo, controller:'geoserver', action:'showInfo2', nombre:it.nombre])
      }
    }
    else
    if(['Cuenca','Subcuenca','Canton','Parroquia','Nivel2','Nivel3','Nivel4','Nivel5'].contains(modo)) {
      int dad = (objs[modo]).findByCodigo(codigo).dad
      (objs[modo]).findAllByDad(dad,[sort:'nombre']).each {
        list.add([id:it.codigo, controller:'geoserver', action:'showInfo1', nombre:it.nombre])
      }
    }

    return list
  }

  def showData() {
    println "SHOWDATA: $params"
    println "--------------------------------------------------------------------------------\n"
    params['desde'] = (params['desde'])?:'01/01/1961'
    params['hasta'] = (params['hasta'])?:'31/12/2014'
    params.action = params['action']
    params.max = 16
    params.offset = (params.offset)?(params.offset as int):0
    params.pohmid = (params['pohmid'])?:null

    def pohm = (params.pohmid != null)?Pohm.get(params.pohmid):null

    def returnParams = [ 'paramController':params['paramController'], 'paramAction':params['paramAction'] ,
                         'paramId':params['paramId'], 'dominio':params['dominio'] ,
                         'desde':params['desde'],'hasta':params['hasta'],
                         'controller':params['controller]'], 'action':params['action'] ]
    if (params.pohmid != null)
      returnParams += ['pohmid':params.pohmid]

    def titulos = [:]

    def dominio = base.Dominio.findByCodigo(params['dominio'])
    def condicion2 = session.condicion + " and p.dominio = ${dominio.id}"
    def condition2titulo = 'Serie de Datos'
    switch(params['dominio']) {
      case 'HI' : condition2titulo = 'Serie de Datos HIDROLOGICOS' ; break
      case 'MET' : condition2titulo = 'Serie de Datos METEOROLOGICOS' ; break
      case 'HG' : condition2titulo = 'Serie de Datos HIDROGEOLOGICOS' ; break
    }

    Date desde = Date.parse('dd/MM/yyyy',params['desde'] as String)
    Date hasta = Date.parse('dd/MM/yyyy',params['hasta'] as String)
    condicion2 += " and d.fechaHora between :desde and :hasta"

    base.Frecuencia frecuencia = base.Frecuencia.findByCodigo('men')
    //base.Estadistica estadistica = base.Estadistica.findByCodigo('med')
    //base.Parametro parametro = base.Parametro.findByCodigo('QMM')
    base.Estadistica estadistica = base.Estadistica.findByCodigo('tot')
    base.Parametro parametro = base.Parametro.findByCodigo('HEM')

    titulos['principal'] = condition2titulo
    titulos['frecuencia'] = "${frecuencia.toString2()}"
    titulos['estadistica'] = "${estadistica.toString2()}"
    titulos['parametro'] = "${parametro.toString2()}"
    titulos['cobertura'] = "${buscarConbertura(params['paramAction'],params['paramId'])}"

    def strSelect = """from \
        DatoHMG d, \
        Pohm p \
      where \
        d.pohm.id = p.id \
        and d.frecuencia.id = ${frecuencia.id} \
        and d.estadistica.id = ${estadistica.id} \
        and d.parametro.id = ${parametro.id} \
        ${condicion2?:''}"""

    def pohms = Pohm.executeQuery('select distinct p ' + strSelect,[desde: desde, hasta: hasta])

    if (pohm != null)
      strSelect += " and p.id = ${pohm.id}"
    def regCount = DatoHMG.executeQuery( 'select count(d), min(d.fechaHora),max(d.fechaHora),count(distinct p) ' + strSelect,[desde: desde, hasta: hasta])
    def contObj = regCount[0]
    def contador = ['n' : contObj[0], 'dDesde' : contObj[1], 'dHasta' : contObj[2], 'npohms' : contObj[3]]

    def i = 0
    render( view : "showData",
            model : [ maxDisplay: params.max,
                      datoHMGInstanceList: DatoHMG.executeQuery('select d ' + strSelect + ' order by d.pohm, d.fechaHora',
                          [desde: desde, hasta: hasta],[max: params.max, offset: params.offset]),
                      datoHMGInstanceTotal: contador['n'],
                      entityName: DatoHMG.entityName,
                      entityNamePlural: DatoHMG.entityNamePlural,
                      titulos: titulos,
                      returnParams: returnParams,
                      contador: contador,
                      pohms: pohms,
                      pohm: pohm ] )
  }

           String buscarConbertura(paramAction,paramId) {
             def cobertura = '?'
             switch(paramAction) {
               case 'showInfo': cobertura = 'Pais' ; break;
               case 'showInfo1':
                 switch(session.modo1) {
                   case 'Demarcacion': cobertura = "Demarcación: ${Demarcacion.findByCodigo(paramId).toString2()}"; break;
                   case 'Provincia': cobertura = "Provincia: ${DivisionPolitica.findByCodigo(paramId).toString2()}"; break;
                   case 'Sistema': cobertura = "Sistema: ${Sistema.findByCodigo(paramId).toString2()}"; break;
                   case 'Nivel1':  cobertura = "Pfafstetter Nivel 1: ${Pfabsteter.findByCodigo(paramId).toString2()}"; break;
                   default : cobertura = '**'; break;
                 }
                 break ;
             }
             return cobertura
           }

  def showPohmData() {
    params['pars'] = (params['pars'])?((params['pars']).toInteger()):0
    params['desde'] = (params['desde'])?:'01/01/1961'
    params['hasta'] = (params['hasta'])?:'31/12/2014'
    params.action = params['action']
    params.max = 18
    params.offset = (params.offset)?(params.offset as int):0
    params.pohmid = (params['id'])?:null

    def pohm = (params.pohmid != null)?Pohm.get(params.pohmid):null

    def returnParams = ['paramId':params['paramId'],'desde':params['desde'],'hasta':params['hasta']]

    if (params.pohmid != null)
      returnParams += ['pohmid':params.pohmid]

    def titulos = [:]

    def condition2titulo = 'Serie de Datos'

    Date desde = Date.parse('dd/MM/yyyy',params['desde'] as String)
    Date hasta = Date.parse('dd/MM/yyyy',params['hasta'] as String)
    def condicion2 = " and d.fechaHora between :desde and :hasta"

    def strsql = "select distinct d.parametro, d.frecuencia, d.estadistica from base.Pohm p, base.DatoHMG d where p = d.pohm and d.pohm = :pohm"
    def pars = base.DatoHMG.executeQuery(strsql,[pohm:pohm])
    session.pars = pars

    /*
    println "- - - - - - - - - - - - - - - - -"
    pars.each() {
      println it
    }
    */

    def pi = params['pars']
    returnParams += [ 'pars':params['pars'] ]
    base.Frecuencia frecuencia = (pars[pi] != null)?pars[pi][1] as base.Frecuencia:null
    base.Estadistica estadistica = (pars[pi] != null)?pars[pi][2] as base.Estadistica:null
    base.Parametro parametro = (pars[pi] != null)?pars[pi][0] as base.Parametro:null

    titulos['principal'] = condition2titulo
    titulos['frecuencia'] = "${(frecuencia!=null)?frecuencia.frecuencia:''}"
    titulos['estadistica'] = "${(estadistica!=null)?estadistica.estadistica:''}"
    titulos['parametro'] = "${(parametro!=null)?parametro.parametro.split(' ')[0]:''}"
    titulos['cobertura'] = "${buscarConbertura(params['paramAction'],params['paramId'])}"

    def strSelectORI = """from DatoHMG d, Pohm p where \
            p.id = ${pohm?.id} \
        and d.pohm.id = p.id \
        and d.frecuencia.id = ${frecuencia?.id} \
        and d.estadistica.id = ${estadistica?.id} \
        and d.parametro.id = ${parametro?.id} \
        ${condicion2?:''}"""

    def strSelect = """from DatoHMG d, Pohm p where \
            p.id = ${pohm?.id} \
        and d.pohm.id = p.id \
        and d.frecuencia.id = ${frecuencia?.id} \
        and d.estadistica.id = ${estadistica?.id} \
        and d.parametro.id = ${parametro?.id} \
        ${condicion2?:''}"""

    def regCount = 0
    def contObj = 0
    def contador = ['n' : 0, 'dDesde' : desde, 'dHasta' : hasta, 'maxDato' : 0 ]

    regCount = DatoHMG.executeQuery('select count(d),min(d.fechaHora),max(d.fechaHora),max(d.datoOriginal) ' + strSelect,[desde: desde, hasta: hasta])
    contObj = regCount[0]
    contador = ['n' : contObj[0], 'dDesde' : contObj[1], 'dHasta' : contObj[2], 'maxDato' : contObj[3] ]

    def i = 0
    render ( template : "pohmData",
            model : [ maxDisplay: params.max,
                      datoHMGInstanceList: DatoHMG.executeQuery('select d ' + strSelect + ' order by d.pohm, d.fechaHora',
                      [desde: desde, hasta: hasta],[max: params.max, offset: params.offset]),
            datoHMGInstanceTotal: contador['n'],
            entityName: DatoHMG.entityName,
            entityNamePlural: DatoHMG.entityNamePlural,
            titulos: titulos,
            returnParams: returnParams,
            parte:[max: params.max, offset:params.offset],
            contador: contador,
            pohm: pohm,
            pars: pars,
            parsIndex: params['pars']
            ] )
  }

  def aExcel() {
    // println params

    params['desde'] = (params['desde'])?:'01/01/1961'
    params['hasta'] = (params['hasta'])?:'31/12/2014'
    params.action = params['action']

    params.max = 18
    if(params.modo != "visualizado")
      params.max = 24000
    params.offset = (params.offset)?(params.offset as int):0
    params.pohmid = (params['id'])?:null

    def pohm = (params.pohmid != null)?Pohm.get(params.pohmid):null
    def returnParams = ['paramId':params['paramId'],'desde':params['desde'],'hasta':params['hasta']]
    if (params.pohmid != null)
      returnParams += ['pohmid':params.pohmid]
    def titulos = [:]
    def condition2titulo = 'Serie de Datos'
    Date desde = Date.parse('dd/MM/yyyy',params['desde'] as String)
    Date hasta = Date.parse('dd/MM/yyyy',params['hasta'] as String)
    def condicion2 = " and d.fechaHora between :desde and :hasta"

    def strsql = "select distinct d.parametro, d.frecuencia, d.estadistica from base.Pohm p, base.DatoHMG d where p = d.pohm and d.pohm = :pohm"
    def pars = base.DatoHMG.executeQuery(strsql,[pohm:pohm])

    def pi = params['pars'] = (params['pars'])?((params['pars']).toInteger()):0
    base.Frecuencia frecuencia = (pars[pi] != null)?pars[pi][1] as base.Frecuencia:null
    base.Estadistica estadistica = (pars[pi] != null)?pars[pi][2] as base.Estadistica:null
    base.Parametro parametro = (pars[pi] != null)?pars[pi][0] as base.Parametro:null

    titulos['principal'] = condition2titulo
    titulos['frecuencia'] = "${frecuencia?.toString2()}"
    titulos['estadistica'] = "${estadistica?.toString2()}"
    titulos['parametro'] = "${parametro?.toString2()}"
    titulos['cobertura'] = "${buscarConbertura(params['paramAction'],params['paramId'])}"
    def strSelect = """from DatoHMG d, Pohm p where \
            p.id = ${pohm?.id} \
        and d.pohm.id = p.id \
        and d.frecuencia.id = ${frecuencia?.id} \
        and d.estadistica.id = ${estadistica?.id} \
        and d.parametro.id = ${parametro?.id} \
        ${condicion2?:''}"""

    def regCount = 0
    def contObj = 0
    def contador = ['n' : 0, 'dDesde' : desde, 'dHasta' : hasta, 'maxDato' : 0 ]

    regCount = DatoHMG.executeQuery( 'select count(d),min(d.fechaHora),max(d.fechaHora),max(d.datoOriginal) ' + strSelect,[desde: desde, hasta: hasta])
    contObj = regCount[0]
    contador = ['n' : contObj[0], 'dDesde' : contObj[1], 'dHasta' : contObj[2], 'maxDato' : contObj[3] ]

    def datoHMGInstanceList = DatoHMG.executeQuery('select d ' + strSelect + ' order by d.pohm, d.fechaHora',[desde: desde, hasta: hasta],[max: params.max, offset: params.offset])
    def datoHMGInstanceTotal = contador['n']
    def headers = ['AAAA-MMM','dato','unidad','tipo','relleno','gráfico']

    def i = 12
    def xlsx = new WebXlsxExporter('/senagua/templates/template.xlsx')
    xlsx.with {
      setResponseHeaders(response)
      fillRow(["POHM CODIGO",pohm.codigo], 2)
      fillRow(["DENOMINACION",pohm.nombre], 3)
      fillRow(["PARAMETRO",parametro.toString2()], 4)
      fillRow(["ENTIDAD", pohm.institucion?.toString()], 5)
      fillRow(["DEMARCACION",pohm.demarcacion?.toString()], 6)
      fillRow(["PROVINCIA", pohm.provincia?.toString() + ' | cantón: ' + pohm.canton?.toString() + ' | parroquia: ' + pohm.parroquia?.toString()], 7)
      fillRow(["SISTEMA", pohm.sistema?.toString() + ' | cuenca: ' + pohm.cuenca?.toString() + ' | subcuenca: ' + pohm.subcuenca?.toString()], 8)
      fillRow(["PFAFSTETTER", 'nivel1 : ' + pfafstetter(pohm,1) + ' | nivel2 : ' + pfafstetter(pohm,2) + ' | nivel3: ' + pfafstetter(pohm,3) + ' | nivel4: ' + pfafstetter(pohm,4) + ' | nivel5: ' + pfafstetter(pohm,5)], 9)
      datoHMGInstanceList.each { dato ->
        fillRow([dato.fechaHora, dato.dDatoValido(),dato.parametro.unidad,dato.sTipoDatoValido(),dato.sRellenoDatoValido()],i++)
      }

      save(response.outputStream)
    }
  }

     String pfafstetter(pohm,nivel) {
       def niv = null
       if (nivel == 1)
         niv = pohm.pfabsteter1
       if (nivel == 2)
         niv = pohm.pfabsteter2
       if (nivel == 3)
         niv = pohm.pfabsteter3
       if (nivel == 4)
         niv = pohm.pfabsteter4
       if (nivel == 5)
         niv = pohm.pfabsteter5
       if (niv == null)
         return ''
       else
         return niv.codigo
     }

  def click() {
    def radioClick = 800
    // println "scala - - - > ${params.scale} inches = ${params.scale.toDouble()*2.54/100000.0}KM"
    def cobertura = ""
    if ( params.latitud && params.longitud ) {
      session.extent = null
      session.latitud = params.latitud
      session.longitud = params.longitud
      cobertura = "ST_GeomFromText('POINT(' || ${session.longitud} || ' ' || ${session.latitud} || ')',32717)"
      cobertura = "st_dwithin(p.geom,$cobertura,$radioClick)"
    } else {
      session.extent = params.extent[1..-2]
      session.latitud = null
      session.longitud = null
      cobertura = "st_within(p.geom,ST_MakeEnvelope(${session.extent},32717))"
    }
    // String sqlString = "select * from pohm p where $cobertura limit 32"
    String sqlString = "select * from pohm p where $cobertura"

    def pohmsHI = []
    def pohmsMET = []
    def pohmsHG = []
    Pohm.withSession { s ->
      s.doWork new Work() {
        void execute(Connection c) {
          new Sql(c).eachRow sqlString, { pohm ->
            def p = Pohm.get(pohm.pohm__id)
            if(p != null) {
              if(p.dominio.codigo == 'HI')
                pohmsHI << p
              if(p.dominio.codigo == 'MET')
                pohmsMET << p
              if(p.dominio.codigo == 'HG')
                pohmsHG << p
            }
          }
        }
      }
    }
    session.pohmsHI = pohmsHI
    session.pohmsMET = pohmsMET
    session.pohmsHG = pohmsHG
    render (template: 'listPohms', model:[pohmsHI:pohmsHI, pohmsMET:pohmsMET, pohmsHG:pohmsHG])
  }

  def imagen() {
    def baseFolder = servletContext.getRealPath("/")
    def s = File.separator
    def file = "${baseFolder}images${s}${params.pngfile}"
    File fi = new File(file)
    if(fi.exists())
      response.outputStream << Files.readAllBytes(fi.toPath())
    else {
      file = "${baseFolder}static${s}images${s}${params.pngfile}"
      fi = new File(file)
      if(fi.exists())
        response.outputStream << Files.readAllBytes(fi.toPath())
    }
  }

}
