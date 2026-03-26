package com.pages;

import com.factory.LoggerFactory;
import com.utils.LoggerUtils;
import com.wrappers.SeleniumWrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class LoginPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    private SeleniumWrappers wrappers;
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wrappers = new SeleniumWrappers(driver);
    }

    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");


    public void login(String user, String pass){
        LoggerUtils.log_info(log,"Going for login");
        wrappers.sendKeys(username,user);
        wrappers.sendKeys(password,pass);
        wrappers.clickOn(loginButton);
        LoggerUtils.log_info(log,"Login completed");
    }

}
