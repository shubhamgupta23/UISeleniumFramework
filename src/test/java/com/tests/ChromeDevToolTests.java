package com.tests;

import com.base.BaseClass;
import com.utils.ExtentReportUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.fetch.Fetch;
import org.openqa.selenium.devtools.v134.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v134.fetch.model.RequestStage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class ChromeDevToolTests extends BaseClass {

    @Test
    public void TC001_interactWithDevTools(){
        ChromeDriver driver = (ChromeDriver) getDriver();
        driver.navigate().to("https://jsonplaceholder.typicode.com/");
        ExtentReportUtils.info("Fetch Response before using mock");
        click_ele_and_print(driver);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Fetch.enable(Optional.of(List.of(new RequestPattern(Optional.of("*"),Optional.empty(),Optional.of(RequestStage.REQUEST)))), Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), request -> {
            if(request.getRequest().getUrl().contains("/todos/1")){
                System.out.println("Intercepted");
                System.out.println(request.getRequestId());
                System.out.println(request.getRequest());
                System.out.println(request.getResponseStatusCode());
                System.out.println(request.getResponseStatusText());
                System.out.println(request.getResponseHeaders());
                String response = """
                        {
                          "userId": 2,
                          "id": 2,
                          "title": "delectus aut autem 2",
                          "completed": true
                        }
                        """;
                String encoded = Base64.getEncoder()
                        .encodeToString(response.getBytes());
                devTools.send(Fetch.fulfillRequest(request.getRequestId(),200,Optional.empty(),Optional.empty(),Optional.of(encoded),Optional.empty()));
            }else{
                devTools.send(Fetch.continueRequest(request.getRequestId(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()));
            }
        });
        driver.navigate().to("https://jsonplaceholder.typicode.com/");
        ExtentReportUtils.info("Fetch Response after using mock");
        click_ele_and_print(driver);
        devTools.close();
    }

    public void click_ele_and_print(WebDriver driver){
        WebElement ele = driver.findElement(By.id("run-button"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",ele);
        ele.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement ele_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='token number']")));
        System.out.println(ele_1.getText());
        ExtentReportUtils.info(driver.findElement(By.id("result")).getText());
    }



}
