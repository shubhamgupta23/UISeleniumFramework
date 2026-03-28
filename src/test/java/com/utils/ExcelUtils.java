package com.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {
    public static Object[][] getEntireData(String filename, String sheetName) {
        String xlsx_path = System.getProperty("user.dir")+"/src/test/resources/testdata/"+filename;
        try(FileInputStream fis = new FileInputStream(xlsx_path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int row_count = sheet.getLastRowNum();
            int column_count = sheet.getRow(0).getLastCellNum();
            Object[][] obj = new Object[row_count][column_count];
            for (int i = 1; i <= row_count; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < column_count; j++) {
                    Cell cell = row.getCell(j);
                    obj[i-1][j] = getCellType(cell);
                }
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
