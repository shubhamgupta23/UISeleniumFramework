package com.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ScreenshotUtils {

    public static String getScreenshotAsFile(WebDriver driver){
        try {
            TakesScreenshot ts = (TakesScreenshot)driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File des = new File(System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png");
            FileUtils.copyFile(src,des);
            return des.getAbsolutePath() ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String getScreenshotAsBase64(WebDriver driver){
        try {
            TakesScreenshot ts = (TakesScreenshot)driver;
            String src = ts.getScreenshotAs(OutputType.BASE64);
            return "data:image/png;base64,"+src;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String getScreenshotAsBytes(WebDriver driver){
        try{
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] base64 = ts.getScreenshotAs(OutputType.BYTES);
            return Base64.getEncoder().encodeToString(base64);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
