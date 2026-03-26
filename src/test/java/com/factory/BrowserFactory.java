package com.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory{

    public static WebDriver getBrowser(String browser){

        WebDriver driver;

        if(browser==null || browser.isBlank()){
            throw new IllegalArgumentException("Browser can't be empty or null");
        }

        switch(browser.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                return driver;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                return driver;
            default:
                throw new IllegalArgumentException("Invalid browser passed "+ browser);
        }
    }

}
