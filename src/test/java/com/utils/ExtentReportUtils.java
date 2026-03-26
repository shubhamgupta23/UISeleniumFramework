package com.utils;

import com.aventstack.extentreports.ExtentTest;
import com.factory.ExtentReportFactory;

public class ExtentReportUtils {

    private static ThreadLocal<ExtentTest> parent = new ThreadLocal();
    private static ThreadLocal<ExtentTest> child = new ThreadLocal();

    public static void setParentTest(String classname){
        parent.set(ExtentReportFactory.getReport().createTest(classname));
    }

    public static void setChildTest(String method){
        child.set(parent.get().createNode(method));
    }

    public static ExtentTest getChildTest(){
        return child.get();
    }

    public static void info(String info){
        getChildTest().info(info);
    }

    public static void warning(String info){
        getChildTest().warning(info);
    }

    public static void pass(String info){
        getChildTest().pass(info);
    }

    public static void fail(String info){
        getChildTest().fail(info);
    }

    public static void skip(String info){
        getChildTest().skip(info);
    }

}
