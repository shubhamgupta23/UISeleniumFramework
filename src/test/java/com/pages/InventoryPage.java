package com.pages;

import com.factory.LoggerFactory;
import com.utils.LoggerUtils;
import com.wrappers.SeleniumWrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class InventoryPage {

    private static final Logger log = LoggerFactory.getLogger(InventoryPage.class);
    private SeleniumWrappers wrappers;

    public InventoryPage(WebDriver driver){
        this.wrappers = new SeleniumWrappers(driver);
    }

    By header = By.xpath("//*[@class='app_logo' and contains(text(),'Swag Labs')]");
    By title = By.xpath("//*[@class='title' and contains(text(),'Products')]");

    public String getHeader(){
        return wrappers.getText(header);
    }

    public String getTitle(){
        LoggerUtils.log_info(log,"Going to fetch title");
        return wrappers.getText(title);
    }

}
