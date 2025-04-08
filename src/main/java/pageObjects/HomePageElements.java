package pageObjects;

import org.openqa.selenium.WebElement;
import utils.ElementFetch;

public interface HomePageElements {
    ElementFetch ele= new ElementFetch();
    WebElement loginButton = ele.getWebElement("xpath","//a[contains(text(),'Login')]");
}
