package testCase;
import base.baseTest;
import org.testng.annotations.Test;
import pageEvents.HomePageEvents;
import pageEvents.LoginPateEvents;
import utils.ElementFetch;

public class testCase2 extends baseTest {
    ElementFetch ele= new ElementFetch();
    HomePageEvents homePageEvents= new HomePageEvents();
    LoginPateEvents loginPateEvents= new LoginPateEvents();

    @Test
    public void sampleMethod() throws InterruptedException {
        System.out.println("This is test case 1");
        homePageEvents.login();
    }
}