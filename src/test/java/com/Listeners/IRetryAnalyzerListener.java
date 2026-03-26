package com.Listeners;

import org.testng.ITestResult;
import com.utils.PropertyUtils;

public class IRetryAnalyzerListener implements org.testng.IRetryAnalyzer {

    private int count = 0;
    private static final int max_retry = Integer.parseInt(PropertyUtils.getProperty("FAIlTESTRETRY"));

    @Override
    public boolean retry(ITestResult iTestResult) {
        while(count<max_retry){
            count++;
            return true;
        }
        return false;
    }
}
