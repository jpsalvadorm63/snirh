package base

class AuxiliaresTagLib {

  def jqDatePicker = { attrs, body ->
    def out = out
    def name = attrs.name
    def value = (attrs.value)?:''
    def id = (attrs.id)?:name

    out.println "<input type=\"text\" name=\"${name}\" value=\"${value}\" id=\"${id}\" size=\"10\"/> <span style=\"color:silver;\"> dd/mm/yyyy<span>"
    out.println "<input type=\"hidden\" name=\"${name}_day\" id=\"${id}_day\"/>"
    out.println "<input type=\"hidden\" name=\"${name}_month\" id=\"${id}_month\"/>"
    out.println "<input type=\"hidden\" name=\"${name}_year\" id=\"${id}_year\"/>"
    out.println "<script type=\"text/javascript\">"
    out.println "\$(document).ready( function() {"
    out.println "   \$(\"#${name}\").datepicker({"
    out.println "   defaultDate:\"01/01/1940\","
    out.println "   dateFormat:\"dd/mm/yy\","
    out.println "   changeMonth:true,"
    out.println "   changeYear:true,"
    out.println "   firstDay: 1,"
    out.println "   onClose: function(dateText,inst) { var myDate = \$.datepicker.parseDate('dd/mm/yy', dateText);"
    out.println "     \$(\"#${name}_month\").attr(\"value\",myDate.getMonth() + 1);"
    out.println "     \$(\"#${name}_day\").attr(\"value\",myDate.getDate());"
    out.println "     \$(\"#${name}_year\").attr(\"value\",myDate.getFullYear());"
    out.println "   }"
    out.println "  });"
    out.println "});"
    out.println "</script>"
  }

}
