package com.wrappers;

import com.utils.ExtentReportUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumWrappers {

    private WebDriver driver;
    private WebDriverWait wait;

    public SeleniumWrappers(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebElement getElement(By locator){
        try{
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch(Exception e){
            ExtentReportUtils.fail("Element not found using locator : "+locator +" due to error : "+e.getMessage());
            throw new RuntimeException("Failed to find element : " + locator, e);
        }
    }

    public void sendKeys(By locator, String input){
        try{
            getElement(locator).sendKeys(input);
            ExtentReportUtils.info("Input field is set for locator : "+locator+" with value : "+input);
        }catch(Exception e){
            ExtentReportUtils.fail("Failed to set the element for locator : "+locator+" with value : "+input);
            throw new RuntimeException("Failed to set element value : " + locator, e);
        }
    }

    public void clickOn(By locator){
        try{
            getElement(locator).click();
            ExtentReportUtils.info("Click on successful : "+locator);
        }catch(Exception e){
            ExtentReportUtils.fail("Failed to click on locator : "+locator);
            throw new RuntimeException("Failed to click on locator : " + locator, e);
        }
    }

    public String getText(By locator){
        try{
            return getElement(locator).getText();
        }catch(Exception e){
            ExtentReportUtils.fail("Failed to get text on locator : "+locator);
            throw new RuntimeException("Failed to get text on locator : " + locator, e);
        }
    }



}
