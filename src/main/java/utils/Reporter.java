package utils;

import base.baseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.internal.LogManagerStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import static base.baseTest.driver;
import static base.baseTest.logger;

public class Reporter {
    public void reportStep(String desc, String status){
        if(status.toUpperCase().equals("PASS")){
            try {
                logger.pass(desc, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot((RemoteWebDriver) driver)).build());
            }catch(Exception e){
                e.printStackTrace();
            }
        } else if (status.toUpperCase().equals("FAIL")) {
            try{
                logger.fail(desc, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot((RemoteWebDriver) driver)).build());
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (status.toUpperCase().equals("INFO")) {
            try {
                logger.info(desc, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot((RemoteWebDriver) driver)).build());
            }catch(Exception e){
                e.printStackTrace();
            }
        } else if (status.toUpperCase().equals("WARNING")) {
            try {
                logger.warning(desc, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot((RemoteWebDriver) driver)).build());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public String captureScreenShot(RemoteWebDriver webDriver) {
        Date now = new Date();
        DateFormat shortDf = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        String currentDate = shortDf.format(now).toString().replace("/", "_");
        currentDate = currentDate.toString().replace(" ", "_");
        currentDate = currentDate.toString().replace(":", "_");
        String imageName = "screenshot_"+currentDate + ".png";
        File screenshotFile = webDriver.getScreenshotAs(OutputType.FILE);
        String screenShotsDirectory = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "test-output" + File.separator + "screenshots"+ File.separator;
        File targetFile = new File(screenShotsDirectory, imageName);
        try {
            FileUtils.copyFile(screenshotFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String screenshotsPath = "./screenshots/" + targetFile.getName();
        return screenshotsPath;
    }
}
