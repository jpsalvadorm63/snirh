package base

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFDateUtil


class ExcelBuilderService {

  HSSFWorkbook workbook
  HSSFSheet sheet
  def labels
  def row

  ExcelBuilderService(String fileName) {
    initGetAt()
    if(new File(fileName).exists()) {
      new File(fileName).withInputStream{ is ->
        workbook = new HSSFWorkbook(is)
      }
      sheet = (workbook)?findSheetByIDX(0):null;
    } else {
      workbook = null
      sheet = null
    }
  }

  void initGetAt() {
    HSSFRow.metaClass.getAt = {int idx ->
      def cell = delegate.getCell(idx)
      if(! cell) {
        return null
      }
      def value
      switch(cell.cellType) {
        case HSSFCell.CELL_TYPE_NUMERIC:
          if(HSSFDateUtil.isCellDateFormatted(cell)) {
            value = cell.dateCellValue
          } else {
            value = cell.numericCellValue
          }
          break
        case HSSFCell.CELL_TYPE_BOOLEAN:
          value = cell.booleanCellValue
          break
        case HSSFCell.CELL_TYPE_BLANK:
          value = "*"
          break
        default:
          try {
            value = cell.stringCellValue
          } catch (ex) {
            value = null
          }
          break
      }
      return value
    }
  }

  ExcelBuilderService(String fileName, String sheetName) {
    initGetAt()
    new File(fileName).withInputStream{is ->
      workbook = new HSSFWorkbook(is)
    }
    sheet = findSheetByName(sheetName)
  }

  HSSFSheet findSheetByName(String sheetName) {
    int n = (workbook)?workbook.getNumberOfSheets():0
    for (i in (0..n - 1)) {
      HSSFSheet pivotSheet = getSheet(i)
      if(pivotSheet.getSheetName().trim() == sheetName)
        return pivotSheet
    }
    null
  }

  String getSheetName(int idx) {
    return workbook.getSheetAt(idx).getSheetName()
  }

  HSSFSheet findSheetByIDX(int IDX) {
    sheet = (workbook)?getSheet(IDX):null
  }

        def getSheet(idx) {
          def sheet
          if(! idx) idx = 0
          if(idx instanceof Number) {
            sheet = workbook.getSheetAt(idx)
          } else if(idx ==~ /^\d+$/) {
            sheet = workbook.getSheetAt(Integer.valueOf(idx))
          } else {
            sheet = workbook.getSheet(idx)
          }
          return sheet
        }

  def cell(idx) {
    if(labels && (idx instanceof String)) {
      idx = labels.indexOf(idx.toLowerCase())
    }
    return row[idx]
  }

  def propertyMissing(String name) {
    cell(name)
  }

  def eachLine(Map params = [:], Closure closure) {
    def offset = params.offset ?: 0
    def max = params.max ?: 9999999
    def sheet = getSheet(params.sheet)
    def rowIterator = sheet.rowIterator()
    def linesRead = 0

    if(params.labels) {
      labels = rowIterator.next().collect{
        it.toString()
      }
    }
    offset.times{ rowIterator.next() }
    closure.setDelegate(this)
    //println labels
    while(rowIterator.hasNext() && linesRead++ < max) {
      row = rowIterator.next()
      closure.call(row)
    }
  }

  def eachSheet(Map params = [:], Closure closure) {
    int n = (workbook)?workbook.getNumberOfSheets():0
    (0..n - 1).each {i ->
      HSSFSheet pivotSheet = getSheet(i)
      // println "----------> ${pivotSheet.getSheetName()} <------------"
      params << [sheetName:pivotSheet.getSheetName()]
      params << [sheet:i]
      eachLine(params,closure)
    }
  }

}
