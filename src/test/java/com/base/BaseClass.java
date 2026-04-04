package com.base;

import com.factory.BrowserFactory;
import com.factory.DriverFactory;
import com.factory.ExtentReportFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.utils.ExtentReportUtils;
import com.utils.JSONUtils;
import com.utils.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseClass {

    protected static JsonNode expected_json = null;

    @BeforeSuite
    @Parameters("environment")
    public void setup(String env){
        if(env==null || env.isBlank()){
            throw new IllegalArgumentException("Please set the environment variable");
        }
        System.setProperty("environment",env);
        PropertyUtils.loadProperties();
        expected_json = JSONUtils.readJsonFromFile(PropertyUtils.getProperty("EXPECTED_HEADERS_JSON"));
    }

    @BeforeTest
    public void initialization(){
        DriverFactory.getDriverInstance().setDriver(BrowserFactory.getBrowser(PropertyUtils.getProperty("BROWSER")));
        DriverFactory.getDriverInstance().getDriver().manage().deleteAllCookies();
        DriverFactory.getDriverInstance().getDriver().manage().window().maximize();
        DriverFactory.getDriverInstance().getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        DriverFactory.getDriverInstance().getDriver().get(PropertyUtils.getProperty("URL"));
        DriverFactory.getDriverInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeClass
    public void parent(){
        ExtentReportUtils.setParentTest(getClass().getName().toString());
    }

    @BeforeMethod
    public void child(Method method){
        ExtentReportUtils.setChildTest(method.getName().toString());
    }

    @AfterTest
    public void tearDown(){
        DriverFactory.getDriverInstance().quitDriver();
    }

    @AfterSuite
    public void tearDownChild(){
        ExtentReportFactory.flushReport();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriverInstance().getDriver();
    }

}
