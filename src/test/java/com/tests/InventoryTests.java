package com.tests;

import com.base.BaseClass;
import com.common.CommonMethods;
import com.factory.LoggerFactory;
import com.pages.InventoryPage;
import com.utils.ExtentReportUtils;
import com.utils.JSONUtils;
import com.utils.LoggerUtils;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InventoryTests extends BaseClass {

    private static final Logger log = LoggerFactory.getLogger(InventoryTests.class);

    private InventoryPage inventory;

    @BeforeClass
    public void init(){
        inventory = new InventoryPage(getDriver());
    }

    @Test
    public void TC001_GetHeader(){
        LoggerUtils.log_info(log,"Going to extract header");
        CommonMethods.performLogin(getDriver());
        String header = inventory.getHeader();
        ExtentReportUtils.info("Inventory header is : "+header);
        Assert.assertEquals(header, JSONUtils.readJsonString(expected_json,"$.inventory.header"));
    }

    @Test
    public void TC002_GetTile(){
        String header = inventory.getTitle();
        ExtentReportUtils.info("Inventory title is : "+header);
        Assert.assertEquals(header,JSONUtils.readJsonString(expected_json,"$.inventory.title"));
    }

}
