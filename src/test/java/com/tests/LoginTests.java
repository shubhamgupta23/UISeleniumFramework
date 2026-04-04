package com.tests;

import com.base.BaseClass;
import com.dataprovider.GetTestData;
import com.factory.LoggerFactory;
import com.pages.LoginPage;
import com.utils.CSVUtils;
import com.utils.ExtentReportUtils;
import com.utils.LoggerUtils;
import com.utils.PropertyUtils;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class LoginTests extends BaseClass {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);

    private LoginPage login;

    @BeforeClass
    public void init(){
        login = new LoginPage(getDriver());
    }

    @Test
    public void TC001_getCurrentUrl(){
        LoggerUtils.log_info(log,"Going to extract current url");
        String url = getDriver().getCurrentUrl();
        ExtentReportUtils.info("URL is : "+url);
        Assert.assertEquals(url, PropertyUtils.getProperty("URL"));
    }

    @Test
    public void TC002_getCorrectLogin(){
        login.login(PropertyUtils.getProperty("USERNAME"),PropertyUtils.getProperty("PASSWORD"));
    }

    @Test
    public void TC003_getCurrentUrlButCheckWrong(){
        String url = getDriver().getCurrentUrl()+"123";
        ExtentReportUtils.info("URL is : "+url);
        Assert.assertEquals(url, PropertyUtils.getProperty("URL"));
    }

    @Test(dataProvider = "getAllUser", dataProviderClass = GetTestData.class)
    public void TC004_getAllUsers(Map<String,Object> map){
        ExtentReportUtils.info("Username is : "+map.get("username"));
        ExtentReportUtils.info("Password is :"+map.get("password"));
        ExtentReportUtils.info("Active status is :"+map.get("active"));
    }

    @Test(dataProvider = "getAllUserFromCsv", dataProviderClass = GetTestData.class)
    public void TC005_getAllUsersCsv(Map<String,Object> map){
        ExtentReportUtils.info("Username is : "+map.get("username"));
        ExtentReportUtils.info("Password is :"+map.get("password"));
        ExtentReportUtils.info("Active status is :"+map.get("active"));
    }

    @Test
    public void TC006_workWithCsv(){
        List<String> columns = CSVUtils.readCsvColumn("./src/test/resources/testdata/login.csv",0);
        ExtentReportUtils.info(columns.toString());

        String value = CSVUtils.readCsvRowColumn("./src/test/resources/testdata/login.csv",3,1);
        ExtentReportUtils.info(value);
    }

}
