package com.Listeners;

import com.factory.DriverFactory;
import com.utils.ExtentReportUtils;
import com.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class ITestListener implements org.testng.ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportUtils.getChildTest().info("Test has been started : "+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportUtils.getChildTest().pass("Test has been successfully executed : "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportUtils.getChildTest().fail("Test has been failed : "+result.getName()).addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshotAsBytes(DriverFactory.getDriverInstance().getDriver()),"Failed Screenshot using byte for method "+result.getName());
        ExtentReportUtils.getChildTest().fail("Test has been failed : "+result.getName()).addScreenCaptureFromPath(ScreenshotUtils.getScreenshotAsBase64(DriverFactory.getDriverInstance().getDriver()),"Failed Screenshot using base64 for method "+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportUtils.getChildTest().skip("Test has been skipped : "+result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
