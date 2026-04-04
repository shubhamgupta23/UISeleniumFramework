package com.dataprovider;

import com.utils.CSVUtils;
import com.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

public class GetTestData {

    @DataProvider(name="getAllUser")
    public static Object[][] getAllUser(){
        return ExcelUtils.getEntireData("login.xlsx","Sheet1");
    }

    @DataProvider(name="getAllUserFromCsv")
    public static Object[][] getAllUserFromCsv(){
        return CSVUtils.readCsvFileDataProvide("./src/test/resources/testdata/login.csv");
    }

}
