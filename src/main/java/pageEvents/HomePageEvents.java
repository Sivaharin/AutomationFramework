package pageEvents;

import base.baseTest;
import pageObjects.HomePageElements;
import pageObjects.LoginPageElements;
import utils.ElementFetch;

import static base.baseTest.driver;

public class HomePageEvents {
    ElementFetch ele= new ElementFetch();
    public void login() throws InterruptedException {
        baseTest.logger.info("Clicking the login button");
        ele.getWebElement("xpath", HomePageElements.loginButton).click();
        Thread.sleep(5000);
        String mainWindow=driver.getWindowHandle();
        baseTest.logger.info("Main window handle is: " + mainWindow);
        System.out.println("Main window handle is: "+mainWindow);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!mainWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                System.out.println("Switched to new window: " + windowHandle);
                baseTest.logger.pass("Switched to new window: " + windowHandle);
                break;
            }
        }
        if (ele.getWebElement("xpath", LoginPageElements.emailField).isDisplayed()) {
            baseTest.logger.pass("Email field is displayed");
            System.out.println("Email field is displayed");
        } else {
            baseTest.logger.fail("Email field is not displayed");
            System.out.println("Email field is not displayed");
        }
    }
}
