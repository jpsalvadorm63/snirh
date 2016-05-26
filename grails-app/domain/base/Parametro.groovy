package base

class Parametro {

  String codigo
  Dominio dominio
  String parametro
  String unidad

  static constraints = {
    codigo(nullable: false, maxSize: 16, unique: true)
    dominio(nullable: false)
    parametro(nullable: false, maxSize: 128)
    unidad(nullable: false, maxSize: 32)
  }

  static dbtable = 'parm'

  static mapping = {
    table "${base.Parametro.dbtable}"
    version true
    cache true

    id column: "${base.Parametro.dbtable}__id"
    codigo column: "${base.Parametro.dbtable}cdgo"
    dominio column: "${base.Dominio.dbtable}__id"
    parametro column: "${base.Parametro.dbtable}parm"
    unidad column: "${base.Parametro.dbtable}unid"

  }

  static void fillData() {
    if(!Parametro.findByCodigo('?')) new Parametro(dominio:Dominio.findByCodigo('?'), codigo: '?', parametro: 'Desconocido', unidad: '?').save(flush: true)
    if(!Parametro.findByCodigo('NEP')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'NEP', parametro: ' Nivel Estático', unidad: 'm').save(flush: true)
    if(!Parametro.findByCodigo('CN')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'CN', parametro: 'Cota Neta', unidad: 'msnm').save(flush: true)
    if(!Parametro.findByCodigo('PTP')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'PTP', parametro: 'Profundidad total', unidad: 'm').save(flush: true)
    if(!Parametro.findByCodigo('DP')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'DP', parametro: 'Diámetro del Pozo', unidad: 'm').save(flush: true)
    if(!Parametro.findByCodigo('QMC')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'QMC', parametro: 'Caudal Medido', unidad: 'l/s').save(flush: true)
    if(!Parametro.findByCodigo('CE')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'CE', parametro: 'Conductividad', unidad: 'µS/cm').save(flush: true)
    if(!Parametro.findByCodigo('PH')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'PH', parametro: ' pH', unidad: '').save(flush: true)
    if(!Parametro.findByCodigo('PRx')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'PRx', parametro: 'Potencial Redox', unidad: 'mV').save(flush: true)
    if(!Parametro.findByCodigo('TA')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'TA', parametro: 'Temperatura del Agua', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('ALC')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'ALC', parametro: 'Alcalinidad', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('OD')) new Parametro(dominio:Dominio.findByCodigo('HG'), codigo: 'OD', parametro: 'Oxígeno disuelto', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('HED')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'HED', parametro: 'Heliofanía diaria', unidad: 'horas').save(flush: true)
    if(!Parametro.findByCodigo('HEM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'HEM', parametro: 'Heliofanía mensual', unidad: 'horas').save(flush: true)
    if(!Parametro.findByCodigo('HEA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'HEA', parametro: 'Heliofanía anual', unidad: 'horas').save(flush: true)
    if(!Parametro.findByCodigo('HRMx')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'HRMx', parametro: 'Humedad Relativa maxima diaria', unidad: '%').save(flush: true)
    if(!Parametro.findByCodigo('HRMi')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'HRMi', parametro: 'Humedad Relativa mínima diaria', unidad: '%').save(flush: true)
    if(!Parametro.findByCodigo('HRMD')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'HRMD', parametro: 'Humedad Relativa media diaria', unidad: '%').save(flush: true)
    if(!Parametro.findByCodigo('HRMM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'HRMM', parametro: 'Humedad Relativa media mensual', unidad: '%').save(flush: true)
    if(!Parametro.findByCodigo('HRMA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'HRMA', parametro: 'Humedad Relativa media anual', unidad: '%').save(flush: true)
    if(!Parametro.findByCodigo('TMxM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TMxM', parametro: 'Temperatura media máxima mensual', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TMiM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TMiM', parametro: 'Temperatura media mínima mensual', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TMM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TMM', parametro: 'Temperatura media mensual', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TMA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TMA', parametro: 'Temperatura media anual', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TMxAD')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TMxAD', parametro: 'Temperatura máxima absoluta diaria', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TMiAD')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TMiAD', parametro: 'Temperatura mínima absoluta diaria', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TMxAM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TMxAM', parametro: 'Temperatura máxima absoluta mensual', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TMiAM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TMiAM', parametro: 'Temperatura mínima absoluta mensual', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TMxAA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TMxAA', parametro: 'Temperatura máxima absoluta anual', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TMiAA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TMiAA', parametro: 'Temperatura mínima absoluta anual', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('RRD')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'RRD', parametro: 'Precipitación diaria', unidad: 'mm').save(flush: true)
    if(!Parametro.findByCodigo('RRM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'RRM', parametro: 'Precipitación mensual', unidad: 'mm').save(flush: true)
    if(!Parametro.findByCodigo('RRA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'RRA', parametro: 'Precipitación Anual', unidad: 'mm').save(flush: true)
    if(!Parametro.findByCodigo('RRMx')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'RRMx', parametro: 'Precipitación Maxima 24 h', unidad: 'mm').save(flush: true)
    if(!Parametro.findByCodigo('ED')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'ED', parametro: 'Evaporación potencial diaria', unidad: 'mm').save(flush: true)
    if(!Parametro.findByCodigo('EM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'EM', parametro: 'Evaporación potencial mensual', unidad: 'mm').save(flush: true)
    if(!Parametro.findByCodigo('EA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'EA', parametro: 'Evaporación potencial anual', unidad: 'mm').save(flush: true)
    if(!Parametro.findByCodigo('NUMD')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'NUMD', parametro: 'Nubosidad Media diaria', unidad: 'mm').save(flush: true)
    if(!Parametro.findByCodigo('NUMM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'NUMM', parametro: 'Nubosidad Media mensual', unidad: 'mm').save(flush: true)
    if(!Parametro.findByCodigo('NUMA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'NUMA', parametro: 'Nubosidad Media anual', unidad: 'mm').save(flush: true)
    if(!Parametro.findByCodigo('TVMD')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TVMD', parametro: 'Tensión de vapor media diaria', unidad: 'HPa').save(flush: true)
    if(!Parametro.findByCodigo('TVMM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TVMM', parametro: 'Tensión de vapor media mensual', unidad: 'HPa').save(flush: true)
    if(!Parametro.findByCodigo('TVMA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TVMA', parametro: 'Tensión de vapor media anual', unidad: 'HPa').save(flush: true)
    if(!Parametro.findByCodigo('TRMD')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TRMD', parametro: 'Temperatura de rocio media diaria', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TRMM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TRMM', parametro: 'Temperatura de rocio media mensual', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('TRMA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'TRMA', parametro: 'Temperatura de rocio media anual', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('VVMD')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'VVMD', parametro: 'Velocidad del viento media diaria', unidad: 'm/s').save(flush: true)
    if(!Parametro.findByCodigo('VVMM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'VVMM', parametro: 'Velocidad del viento media mensual', unidad: 'm/s').save(flush: true)
    if(!Parametro.findByCodigo('VVMA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'VVMA', parametro: 'Velocidad del viento media anual', unidad: 'm/s').save(flush: true)
    if(!Parametro.findByCodigo('VMxA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'VMxA', parametro: 'Velocidad máxima diaria', unidad: 'm/s').save(flush: true)
    if(!Parametro.findByCodigo('PAMD')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'PAMD', parametro: 'Presión Atmosferica media diaria', unidad: 'HPa').save(flush: true)
    if(!Parametro.findByCodigo('PAMM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'PAMM', parametro: 'Presión Atmosferica media mensual', unidad: 'HPa').save(flush: true)
    if(!Parametro.findByCodigo('PAMA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'PAMA', parametro: 'Presión Atmosferica media anual', unidad: 'HPa').save(flush: true)
    if(!Parametro.findByCodigo('RVD')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'RVD', parametro: 'Recorrido del viento diario', unidad: 'km').save(flush: true)
    if(!Parametro.findByCodigo('RVM')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'RVM', parametro: 'Recorrido del viento mensual', unidad: 'km').save(flush: true)
    if(!Parametro.findByCodigo('RVA')) new Parametro(dominio:Dominio.findByCodigo('MET'), codigo: 'RVA', parametro: 'Recorrido del viento anual', unidad: 'km').save(flush: true)
    if(!Parametro.findByCodigo('pH')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'pH', parametro: 'Potencial Hidrogeno', unidad: '').save(flush: true)
    if(!Parametro.findByCodigo('CE')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'CE', parametro: 'Conductividad eléctrica', unidad: 'µS/cm').save(flush: true)
    if(!Parametro.findByCodigo('T')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'T', parametro: 'Temperatura', unidad: 'oC').save(flush: true)
    if(!Parametro.findByCodigo('Turbidez')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Turbidez', parametro: 'Turbidez', unidad: 'NTU').save(flush: true)
    if(!Parametro.findByCodigo('Color')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Color', parametro: 'Color', unidad: 'Co').save(flush: true)
    if(!Parametro.findByCodigo('TDS')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'TDS', parametro: 'Sólidos totales disueltos', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('HCO3-')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'HCO3-', parametro: 'Bicarbonatos', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('CO3=')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'CO3=', parametro: 'Carbonatos', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('SO4=')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'SO4=', parametro: 'Sulfatos', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('NO3-')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'NO3-', parametro: 'Nitrato', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('NO2-')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'NO2-', parametro: 'Nitrito', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('PO4')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'PO4', parametro: 'Fosfato', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('Cl-')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Cl-', parametro: 'Cloruro', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('F-')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'F-', parametro: 'Fluoruro', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('Ca++')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Ca++', parametro: 'Calcio', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('Mg++')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Mg++', parametro: 'Magnesio', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('Na')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Na', parametro: 'Sodio', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('K')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'K', parametro: 'Potasio', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('Pb')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Pb', parametro: 'Plomo', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('Mn')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Mn', parametro: 'Manganeso', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('Cu')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Cu', parametro: 'Cobre', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('Cr')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Cr', parametro: 'Cromo', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('Fe')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Fe', parametro: 'Hierro', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('A/T')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'A/T', parametro: 'Alcalinidad total', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('D/T')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'D/T', parametro: 'Dureza total', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('DCa')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'DCa', parametro: 'Dureza cálcica', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('SiO2')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'SiO2', parametro: 'Sílice', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('CO2')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'CO2', parametro: 'Dióxido de carbono', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('NH4')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'NH4', parametro: 'Amonio', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('DBO5')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'DBO5', parametro: 'Demanda Bioquímica de oxígeno en 5 días', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('DQO')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'DQO', parametro: 'Demanda Química de oxígeno', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('OD')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'OD', parametro: 'Oxígeno disuelto', unidad: 'mg/l').save(flush: true)
    if(!Parametro.findByCodigo('Coliformes T')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Coliformes T', parametro: 'Coliformes totales', unidad: 'NMP/100ml').save(flush: true)
    if(!Parametro.findByCodigo('Coliformes F')) new Parametro(dominio:Dominio.findByCodigo('HQ'), codigo: 'Coliformes F', parametro: 'Coliformes fecales', unidad: 'NMP/100ml').save(flush: true)
    if(!Parametro.findByCodigo('A')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'A', parametro: 'Área de drenaje', unidad: 'km2').save(flush: true)
    if(!Parametro.findByCodigo('º')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'º', parametro: 'Perímetro', unidad: 'km').save(flush: true)
    if(!Parametro.findByCodigo('L')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'L', parametro: 'longitud cauce principal', unidad: 'km').save(flush: true)
    if(!Parametro.findByCodigo('S')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'S', parametro: 'Pendiente media del cauce', unidad: 'm/m').save(flush: true)
    if(!Parametro.findByCodigo('SC')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'SC', parametro: 'Pendiente media', unidad: 'm/m').save(flush: true)
    if(!Parametro.findByCodigo('Hm')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'Hm', parametro: 'Altitud media de la cuenca', unidad: 'msnm').save(flush: true)
    if(!Parametro.findByCodigo('NMH')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'NMH', parametro: 'Nivel medio horario', unidad: 'm').save(flush: true)
    if(!Parametro.findByCodigo('NMD')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'NMD', parametro: 'Nivel medio diario', unidad: 'm').save(flush: true)
    if(!Parametro.findByCodigo('NMxM')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'NMxM', parametro: 'Nivel máximo instantaneo', unidad: 'm').save(flush: true)
    if(!Parametro.findByCodigo('NMiM')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'NMiM', parametro: 'Nivel mínimo instantaneo', unidad: 'm').save(flush: true)
    if(!Parametro.findByCodigo('QMH')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'QMH', parametro: 'Caudal medio horario', unidad: 'm3/s').save(flush: true)
    if(!Parametro.findByCodigo('QMD')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'QMD', parametro: 'Caudal medio diario', unidad: 'm3/s').save(flush: true)
    if(!Parametro.findByCodigo('QMM')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'QMM', parametro: 'Caudal medio mensual', unidad: 'm3/s').save(flush: true)
    if(!Parametro.findByCodigo('QMA')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'QMA', parametro: 'Caudal medio anual', unidad: 'm3/s').save(flush: true)
    if(!Parametro.findByCodigo('QMxM')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'QMxM', parametro: 'Caudal máximo anual', unidad: 'm3/s').save(flush: true)
    if(!Parametro.findByCodigo('QMiM')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'QMiM', parametro: 'Caudal mínimo anual', unidad: 'm3/s').save(flush: true)
    if(!Parametro.findByCodigo('QMx')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'QMx', parametro: 'Caudal máximo instantáneo anual', unidad: 'm3/s').save(flush: true)
    if(!Parametro.findByCodigo('Qmi')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'Qmi', parametro: 'Caudal mínimo instantáneo anual', unidad: 'm3/s').save(flush: true)
    if(!Parametro.findByCodigo('CMTSS')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'CMTSS', parametro: 'Concentración media solidos totales suspendidos', unidad: 'kg/m3').save(flush: true)
    if(!Parametro.findByCodigo('QS')) new Parametro(dominio:Dominio.findByCodigo('HI'), codigo: 'QS', parametro: 'Gasto sólido', unidad: 'kg/s').save(flush: true)
  }

  String toString() { parametro }

  String toString2() { "$parametro ($codigo)" }

  static entityName = "Parametro"

  static entityNamePlural = "Parametros"

}
