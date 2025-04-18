package pageEvents;

import base.baseTest;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebElement;
import pageObjects.HomePageElements;
import pageObjects.LoginPageElements;
import utils.ElementFetch;
import utils.ElementFetch.*;
import utils.Reporter;

import static base.baseTest.driver;

public class HomePageEvents {
    ElementFetch ele= new ElementFetch();
    Reporter reporterObject= new Reporter();
    public void login() throws InterruptedException {
        baseTest.logger.info("Clicking the login button");
        if(ele.checkElementPresenceWithoutReport(HomePageElements.loginButton)){
            HomePageElements.loginButton.click();
        }
        Thread.sleep(5000);
        String mainWindow=driver.getWindowHandle();
        baseTest.logger.info("Main window handle is: " + mainWindow);
        System.out.println("Main window handle is: "+mainWindow);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!mainWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                System.out.println("Switched to new window: " + windowHandle);
//                baseTest.logger.pass("Switched to new window: " + windowHandle);
                reporterObject.reportStep("Switched to new window: " + windowHandle, "PASS");
                break;
            }
        }
        if (ele.checkElementPresenceWithReport("Email",LoginPageElements.emailField)) {
            reporterObject.reportStep("Email field is displayed", "PASS");
//            baseTest.logger.pass("Email field is displayed");
            System.out.println("Email field is displayed");
        } else {
//            baseTest.logger.fail("Email field is not displayed");
            System.out.println("Email field is not displayed");
            reporterObject.reportStep("Email field is not displayed", "FAIL");
        }
    }
}
