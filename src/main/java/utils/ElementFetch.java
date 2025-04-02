package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementFetch {
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

}
