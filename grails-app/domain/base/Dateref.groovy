package base

class Dateref {

  Frecuencia frecuencia
  Date fecha


  static constraints = {
    fecha(nullable: false)
    frecuencia(nullable: false)
  }

  static dbtable = 'fech'

  static mapping = {
    table   "${base.Dateref.dbtable}"
    version true
    cache   false

    id           column : "${base.Dateref.dbtable}__id"
    frecuencia   column : "frec__id" , index : "${base.Dateref.dbtable}frec_idx"
    fecha        column : "${base.Dateref.dbtable}fech" , index:"${base.Dateref.dbtable}fech_Idx"
  }

  static void fillData() {
    /* Date fechahora = new Date()
    fechahora = fechahora.clearTime()
    def myfrecuencia = base.Frecuencia.findByCodigo("dia")
    int i = 0
    fechahora.set(year:1960, month:0, date:0, hourOfDay:0, minute:0)
    while(fechahora.getAt(Calendar.YEAR) < 2015) {
      new Dateref(frecuencia: myfrecuencia, fecha: fechahora.clearTime()).save(flush:true)
      fechahora=  fechahora.next()
      i++
    }
    println fechahora
    println i

    fechahora = fechahora.clearTime()
    myfrecuencia = base.Frecuencia.findByCodigo("men")
    for(int anio=1960;anio<=2015;anio++) {
      for(int mes=1;mes<=12;mes++) {
        fechahora = Date.parse("d/MM/yyyy","1/$mes/$anio")
        fechahora = fechahora.clearTime()
        def dateref = new Dateref()
        dateref.fecha = fechahora
        dateref.frecuencia = myfrecuencia
        dateref.save(flush: true)
      }
    } */
    //update
    //fech
    //set
    //fechfech = to_date(to_char(fechfech,'DD/MM/YYYY'),'DD/MM/YYYY') ;
    //
    //select
    //extract('HOUR' from fechfech),
    //max(fechfech) max,
    //min(fechfech) min
    //from
    //fech
    //group by
    //1 ;
  }

  String toString() { '$fecha' }
  String toString2() { '$frecuencia $fecha' }

  static entityName = "Hora"

  static entityNamePlural = "Horas"

}
