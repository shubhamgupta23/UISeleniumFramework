package com.factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private DriverFactory(){}

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getDriverInstance(){
        return instance;
    }

    ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>();

    public void setDriver(WebDriver driver){
        tl.set(driver);
    }

    public WebDriver getDriver(){
        return tl.get();
    }

    public void quitDriver(){
        tl.get().quit();
        tl.remove();
    }

}
