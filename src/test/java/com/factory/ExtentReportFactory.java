package com.factory;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportFactory {

    private static ExtentReports reports;
    public static ExtentReports getReport(){
        if(reports == null){
            try{
                String path = System.getProperty("user.dir")+"/reports/extent-html-report.html";
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
                reports = new ExtentReports();
                reports.attachReporter(sparkReporter);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return reports;
    }

    public static void flushReport(){
        getReport().flush();
    }

}
