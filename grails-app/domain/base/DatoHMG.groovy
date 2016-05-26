package base

class DatoHMG {

  Pohm pohm
  Parametro parametro
  Frecuencia frecuencia
  Estadistica estadistica
  Date fechaHora
  Double datoOriginal
  Double datoRellenado
  Double datoSenagua
  TipoRelleno tipoRelleno
  String observacion
  UpldFile file
  String usuario


  static constraints = {
    pohm(nullable:false)
    parametro(nullable:false)
    frecuencia(nullable:false)
    estadistica(nullable:false)
    fechaHora(nullable:false)
    datoOriginal(nullable:true)
    datoRellenado(nullable:true)
    datoSenagua(nullable:true)
    tipoRelleno(nullable:true)
    observacion(nullable:true, maxSize: 128, blank: true)
    file(nullable: true)
    usuario(nullable:true, maxSize: 128)
  }

  static dbtable = 'dhmg'

  static mapping = {
    table "${base.DatoHMG.dbtable}"
    version true
    cache false

    id column: "${base.DatoHMG.dbtable}__id"
    pohm column: "${base.Pohm.dbtable}__id", index:"${base.Pohm.dbtable}pohm_idx"
    parametro column: "${base.Parametro.dbtable}__id"
    frecuencia column: "${base.Frecuencia.dbtable}__id"
    estadistica column: "${base.Estadistica.dbtable}__id"
    fechaHora column: "${base.DatoHMG.dbtable}feho"
    datoOriginal column: "${base.DatoHMG.dbtable}orig"
    datoRellenado column: "${base.DatoHMG.dbtable}rell"
    datoSenagua column: "${base.DatoHMG.dbtable}sgua"
    tipoRelleno column: "${base.TipoRelleno.dbtable}__id"
    observacion column: "${base.DatoHMG.dbtable}obsr"
    file column: "${base.UpldFile.dbtable}__id", index:"file_idx"
    usuario column: "usuario"
  }

  static int fillData(params) {
    String obs = ""
    def pohm = base.Pohm.findByCodigo(params.pohm)
    def frecuencia = base.Frecuencia.findByCodigo(params.frecuencia)
    def estadistica = base.Estadistica.findByCodigo(params.estadistica)
    def tipoRelleno = base.TipoRelleno.findByCodigo(params.tipoRelleno)
    def tipoRellenoNO = base.TipoRelleno.findByCodigo("NO")
    def parametro = base.Parametro.findByCodigo(params.parametro)
    if(!pohm) {
      obs += "[Punto de observación desconcocida:${params.pohm}]"
      println obs
      return 1
    }
    if(!frecuencia) {
      obs += "[Frecuencia desconcocida:${params.frecuencia}]"
      println obs
      return 1
    }
    if(!estadistica) {
      obs += "[Estadística desconcocida:${params.estadistica}]"
      println obs
      return 1
    }
    if(!tipoRelleno) {
      obs += "[Tipo de relleno desconocido:${params.tipoRelleno}]"
      println obs
      return 1
    }
    if(!parametro) {
      obs += "[parametro desconocido:${params.parametro}]"
      println obs
      return 1
    }
    Date fechaHora = Date.parse("dd-MMM-yyyy hh:mm",params.fechaHora)

    Double datoOriginal
    datoOriginal = (params.datoOriginal && params.datoOriginal !='null' && params.datoOriginal.isDouble())?params.datoOriginal.toDouble():null
    Double datoRellenado
    datoRellenado = (params.datoRellenado && params.datoRellenado !='null' && params.datoRellenado.isDouble())?params.datoRellenado.toDouble():null

    //DatoHMG dato = DatoHMG.findWhere(pohm: pohm, frecuencia: frecuencia, estadistica:estadistica, tipoRelleno: tipoRelleno, fechaHora: fechaHora, parametro: parametro)
    DatoHMG dato = DatoHMG.findWhere(pohm: pohm, frecuencia: frecuencia, estadistica:estadistica, fechaHora: fechaHora, parametro: parametro)

    if(dato) {
      if(dato.tipoRelleno.codigo == "NO")
        tipoRelleno = tipoRellenoNO
      dato.properties = [datoOriginal:datoOriginal, datoRellenado:datoRellenado, usuario:params.usuario, file: params.file, tipoRelleno: tipoRelleno]
    } else {
      dato = new DatoHMG([pohm: pohm, frecuencia: frecuencia, estadistica:estadistica, tipoRelleno: tipoRelleno,
          fechaHora: fechaHora, parametro: parametro, datoOriginal:datoOriginal, datoRellenado:datoRellenado,
          usuario: params.usuario, file: params.file])
    }

    dato.save(flush:true)
    if(dato.hasErrors()) {
      println dato.errors
    }
    return 0
  }

  String toString() { "$datoOriginal | $datoSenagua | $tipoRelleno" }

  String toString2() { "pohm:$pohm;parametro:$parametro;frecuencia:$frecuencia;estadistica:$estadistica;fechaHora:$fechaHora;datoOriginal:$datoOriginal;datoRellenado:$datoRellenado;datoSenagua:$datoSenagua;tipoRelleno$tipoRelleno;observacion:$observacion;" }

  static entityName = "Dato Hidrometeorlógico y Hidrogeológico"

  static entityNamePlural = "Datos Hidrometeorlógicos y Hidrogeológicos"

  //evitar esta "barbaridad"
  Map datoValido() {
    def resultado = [ 'valor':'', 'tipo':'nod', 'relleno':'' ]

    if(datoSenagua != null ) {
      resultado['valor'] = String.format('%.2f',datoSenagua)
      resultado['tipo'] = 'seng'
      resultado['relleno'] = (tipoRelleno != null)?tipoRelleno.codigo:''
    } else if(datoRellenado != null ) {
      resultado['valor'] = String.format('%.2f',datoRellenado)
      resultado['tipo'] = 'rell'
      resultado['relleno'] = (tipoRelleno != null)?tipoRelleno.codigo:''
    } else if(datoOriginal != null ) {
      resultado['valor'] = String.format('%.2f',datoOriginal)
      resultado['tipo'] = 'orig'
    }

    return resultado
  }
          //esto en vez de la "barbaridad"
          Double dDatoValido() {
          if(datoSenagua != null ) {
            return datoSenagua
          } else if(datoRellenado != null ) {
            return datoRellenado
          } else if(datoOriginal != null ) {
            return datoOriginal
          } else return null
        }

        String sTipoDatoValido() {
          if(datoSenagua != null ) {
            return 'senagua'
          } else if(datoRellenado != null ) {
            return 'rellenado'
          } else if(datoOriginal != null ) {
            return 'original'
          } else return 'no data'
        }

  String sRellenoDatoValido() {
    return (tipoRelleno != null)?tipoRelleno.codigo:''
  }

  double porcentageDe(value) {
    if(value == 0)
      return 0
    if(datoSenagua != null)
      return datoSenagua*100/value
    else
    if( datoRellenado != null)
      return datoRellenado*100/value
    else
    if( datoOriginal != null)
      return datoOriginal*100/value
    else
      return 0
  }

}
