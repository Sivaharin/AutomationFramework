package utils;

import com.aventstack.extentreports.model.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import utils.Reporter;

public class ElementFetch {
    Reporter reporterObject= new Reporter();
    public WebElement getWebElement(String identifierType,String element) {
        switch(identifierType) {
            case "xpath":
                return base.baseTest.driver.findElement(By.xpath(element));
            case "id":
                return base.baseTest.driver.findElement(By.id(element));
            case "cssSelector":
                return base.baseTest.driver.findElement(By.cssSelector(element));
            case "name":
                return base.baseTest.driver.findElement(By.name(element));
            case "linkText":
                return base.baseTest.driver.findElement(By.linkText(element));
            case "partialLinkText":
                return base.baseTest.driver.findElement(By.partialLinkText(element));
            case "tagName":
                return base.baseTest.driver.findElement(By.tagName(element));
            default:
                throw new IllegalArgumentException("Invalid identifier type: " + identifierType);
        }
    }
    public List<WebElement> getWebElements(String identifierType, String element) {
        switch(identifierType) {
            case "xpath":
                return base.baseTest.driver.findElements(By.xpath(element));
            case "id":
                return base.baseTest.driver.findElements(By.id(element));
            case "cssSelector":
                return base.baseTest.driver.findElements(By.cssSelector(element));
            case "name":
                return base.baseTest.driver.findElements(By.name(element));
            case "linkText":
                return base.baseTest.driver.findElements(By.linkText(element));
            case "partialLinkText":
                return base.baseTest.driver.findElements(By.partialLinkText(element));
            case "tagName":
                return base.baseTest.driver.findElements(By.tagName(element));
            default:
                throw new IllegalArgumentException("Invalid identifier type: " + identifierType);
        }
    }
    public boolean checkElementPresenceWithoutReport(WebElement element){
        Boolean isElementPresent = false;
        try{
            Wait<WebElement> wait = new FluentWait<WebElement>(element).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class);
            if (element.isDisplayed()){
                return true;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean checkElementPresenceWithReport(String desc,WebElement element){
        Boolean isElementPresent = false;
        try{
            Wait<WebElement> wait = new FluentWait<WebElement>(element).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class);
            if (element.isDisplayed()){
                reporterObject.reportStep(desc+" is displayed", "PASS");
                return true;
            }
            return true;
        }catch (Exception e){
            reporterObject.reportStep(desc+" is not displayed", "FAIL");
            return false;
        }
    }

}
