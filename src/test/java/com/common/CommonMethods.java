package com.common;

import com.pages.LoginPage;
import com.utils.PropertyUtils;
import org.openqa.selenium.WebDriver;

public class CommonMethods {

    public static void performLogin(WebDriver driver){
        LoginPage login = new LoginPage(driver);
        login.login(PropertyUtils.getProperty("USERNAME"),PropertyUtils.getProperty("PASSWORD"));
    }

}
