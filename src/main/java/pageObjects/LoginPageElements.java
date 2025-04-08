package pageObjects;

import org.openqa.selenium.WebElement;
import utils.ElementFetch;

public interface LoginPageElements {
    ElementFetch ele= new ElementFetch();
    WebElement emailField= ele.getWebElement("xpath", "//input[@name='email']");
    String passwordField = "//input[@name='password']";
    String loginButton = "//div[contains(text(),'Login')]";
    String loginErrorMessage = "//div[contains(text(),'Invalid email or password')]";
    String forgotPasswordLink = "//a[contains(text(),'Forgot your password?')]";
    String signUpLink = "//a[contains(text(),'Sign Up')]";
    String rememberMeCheckbox = "//input[@type='checkbox']";
    String loginPageTitle = "Free CRM - Login"; // Example title, adjust as needed
}
