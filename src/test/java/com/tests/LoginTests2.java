package com.tests;

import com.base.BaseClass;
import com.pages.LoginPage;
import com.utils.ExtentReportUtils;
import com.utils.PropertyUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTests2 extends BaseClass {

    private LoginPage login;

    @BeforeClass
    public void init(){
        login = new LoginPage(getDriver());
    }

    @Test
    public void TC002_getCurrentUrl(){
        String url = getDriver().getCurrentUrl();
        ExtentReportUtils.info("URL is : "+url);
    }

    @Test
    public void TC001_getCorrectLogin(){
        login.login(PropertyUtils.getProperty("USERNAME"),PropertyUtils.getProperty("PASSWORD"));
    }

}
