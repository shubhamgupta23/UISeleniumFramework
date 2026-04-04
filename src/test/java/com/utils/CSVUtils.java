package com.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVUtils {

    /* This method is used to read whole csv file */
    public static List<String[]> readCsvFile(String filepath){
        try(CSVReader reader = new CSVReader(new FileReader(filepath))){
            return reader.readAll();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    /* This method is used to read only specific row . Accept row in terms of index */
    public static String[] readCsvRow(String filepath, int row){
        List<String[]> rows_arr = readCsvFile(filepath);
        if((row < 0) || (row > rows_arr.size())){
            throw new IllegalArgumentException("Please provide valid row");
        }
        return rows_arr.get(row);
    }

    /* This method is used to read only single column for all rows . Accept column in terms of index */
    public static List<String> readCsvColumn(String filepath, int column){
        List<String[]> rows_arr = readCsvFile(filepath);
        List<String> column_value = new ArrayList<>();
        if(column < 0 || column > rows_arr.get(0).length){
            throw new IllegalArgumentException("Please provide valid column");
        }
        for(int i=0 ; i<rows_arr.size();i++){
            String[] columns_value = rows_arr.get(i);
            for(int j=0 ; j<columns_value.length;j++){
                if(j == column){
                    column_value.add(columns_value[j]);
                }
            }
        }
        return column_value;
    }

    public static String readCsvRowColumn(String filepath, int row, int column){
       String[] fetched_row = readCsvRow(filepath,row);
       if(column < 0 || column>fetched_row.length){
           throw new IllegalArgumentException("Please provide valid column");
       }
       return fetched_row[column];
    }

    public static void writeInCsv(String filepath, List<String[]> str){
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(filepath));
            writer.writeAll(str);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write on csv having path : "+filepath + e);
        }
    }

    /* This method is used as a Data Provider */
    public static Object[][] readCsvFileDataProvide(String filepath){
        List<String[]> reader = readCsvFile(filepath);
        Object[][] obj = new Object[reader.size()-1][1];
        String[] header = reader.get(0);
        System.out.println("Total Rows in CSV : "+reader.size());
        System.out.println("Total Column in CSV : "+header.length);
        for(int row=1 ; row < reader.size() ; row++){
            HashMap<String,Object> map = new HashMap<>();
            for(int column=0 ; column < header.length ; column++){
                map.put(header[column],reader.get(row)[column]);
            }
            obj[row-1][0] = map;
        }
        System.out.println(obj.length);
        return obj;
    }

}
