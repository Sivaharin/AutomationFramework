package utils;

import base.baseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SuiteListener implements ITestListener, IAnnotationTransformer {

    public void onTestFailure(ITestResult result) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        String filename = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + result.getMethod().getMethodName() + "_" + dtf.format(now) + ".jpg";
        File f1= ((TakesScreenshot)baseTest.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f1, new File(filename));
            String screenshotPath = filename;
            baseTest.logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(filename).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onTestSuccess(ITestResult result) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        String filename=System.getProperty("user.dir")+ File.separator+"screenshots"+File.separator+result.getMethod().getMethodName()+ "_" + dtf.format(now) + ".jpg";
        File f1= ((TakesScreenshot)baseTest.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f1, new File(filename+".png"));
            String screenshotPath = filename;
            baseTest.logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onTestSkipped(ITestResult result) {
        String filename=System.getProperty("user.dir")+ File.separator+"screenshots"+File.separator+result.getMethod().getMethodName();
        File f1= ((TakesScreenshot)baseTest.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f1, new File(filename+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
//        annotation.setRetryAnalyzer(RetryAnalyzer.class);
//    }
}
