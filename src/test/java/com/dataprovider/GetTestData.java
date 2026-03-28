package com.dataprovider;

import com.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

public class GetTestData {

    @DataProvider(name="getAllUser")
    public static Object[][] getAllUser(){
        return ExcelUtils.getEntireData("login.xlsx","Sheet1");
    }

}
