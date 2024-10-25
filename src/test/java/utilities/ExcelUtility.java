package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet ws;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtility(String path) {
        this.path = path;
    }
    
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }
    
    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }
    
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rowNum);
        cell = row.getCell(colNum);
        
        DataFormatter formatter = new DataFormatter();
        String cellData;
        try {
            cellData = formatter.formatCellValue(cell);
        } catch (Exception e) {
            cellData = "";
        }
        wb.close();
        fi.close();
        return cellData;
    }
    
    public void setCellData(String sheetName, int rowNum, int colNum, String cellData) throws IOException {
       
    	File xlfile = new File(path);

        if (!xlfile.exists()) {
            wb = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            wb.write(fo);
        }        
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);

        if (wb.getSheetIndex(sheetName)==-1)
            wb.createSheet(sheetName);
        ws = wb.getSheet(sheetName);
        
        if (ws.getRow(rowNum) == null)
            ws.createRow(rowNum);
        row = ws.getRow(rowNum);
        
        cell = row.createCell(colNum);
        cell.setCellValue(cellData);
        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }
    
    public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        
        row = ws.getRow(rowNum);
        cell = row.getCell(colNum);
        
        style = wb.createCellStyle();
        
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        cell.setCellStyle(style);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }
}
