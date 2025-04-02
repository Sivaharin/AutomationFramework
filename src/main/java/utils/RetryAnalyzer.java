package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 1; // Set the maximum number of retries
    @Override
    public boolean retry(ITestResult iTestResult) {
        while (count<maxTry) {
            count++;
            return true;
        }
        return false;
    }
}

