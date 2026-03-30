package com.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
    public static Object[][] getEntireData(String filename, String sheetName) {
        String xlsx_path = System.getProperty("user.dir")+"/src/test/resources/testdata/"+filename;
        try(FileInputStream fis = new FileInputStream(xlsx_path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int row_count = sheet.getLastRowNum();
            int column_count = sheet.getRow(0).getLastCellNum();
            //Object[][] obj = new Object[row_count][column_count];
            Object[][] obj = new Object[row_count][1];
            XSSFRow row_0 = sheet.getRow(0);
            for (int i = 1; i <= row_count; i++) {
                Map<String,Object> map = new HashMap<>();
                Row row = sheet.getRow(i);
                for (int j = 0; j < column_count; j++) {
                    Cell cell = row.getCell(j);
                    map.put(row_0.getCell(j).toString(),getCellType(cell));
                    //obj[i-1][j] = getCellType(cell);
                }
                obj[i-1][0] = map;
            }
            return obj;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object getCellType(Cell cell){
        return switch(cell.getCellType()){
            case _NONE -> null;
            case NUMERIC -> cell.getNumericCellValue();
            case STRING -> cell.getStringCellValue();
            case FORMULA -> cell.toString();
            case BLANK -> "";
            case BOOLEAN -> cell.getBooleanCellValue();
            case ERROR -> cell.getErrorCellValue();
        };
    }

}
