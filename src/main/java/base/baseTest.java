package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.Constants;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class baseTest {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public static ExtentTest logger;
    public static WebDriver driver;

@BeforeTest
public void beforeTestMethod(){
    System.out.println("Before Test");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    LocalDateTime now = LocalDateTime.now();
    sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "test-output" + File.separator + "Report_" + dtf.format(now) + ".html");
    extent=new ExtentReports();
    extent.attachReporter(sparkReporter);
    extent.setSystemInfo("Host Name","localhost");
    extent.setSystemInfo("Username","root");
    sparkReporter.config().setTheme(Theme.DARK);
    sparkReporter.config().setDocumentTitle("Extent Report");
    sparkReporter.config().setReportName("TestReport");
    sparkReporter.config().setEncoding("utf-8");
}
@BeforeMethod
@Parameters("browser")
public void beforeMethod(String browser, Method testMethod){
    System.out.println("Before Method");
    System.out.println("Browser is: "+browser);
    logger=extent.createTest(testMethod.getName());
    setUpDriver(browser);
    driver.manage().window().maximize();
    driver.get(Constants.URL);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


}
@AfterMethod
public void afterMethod(ITestResult result){
    System.out.println("After Method");
    if (result.getStatus()==ITestResult.SUCCESS){
        logger.pass("Test Passed");
        logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+ " - Test Passed", ExtentColor.GREEN));
    }else if (result.getStatus()==ITestResult.FAILURE){
        logger.fail("Test Failed");
        logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ " - Test Failed", ExtentColor.RED));
        logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+ " - Test Failed", ExtentColor.RED));
    }else if (result.getStatus()==ITestResult.SKIP){
        logger.skip("Test Skipped");
        logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ " - Test Failed", ExtentColor.ORANGE));
    }
    driver.quit();

}
@AfterTest
public void afterTestMethod(){
    System.out.println("After Test");
    extent.flush();
}
public void setUpDriver(String browser){
    if (browser.equalsIgnoreCase("chrome")) {
        System.out.println("Setting up Chrome driver");
        System.setProperty("WebDriver.chrome.driver", "C:\\Users\\sanje\\IdeaProjects\\SeleniumFramework\\src\\main\\resources\\chromedriver.exe");
//        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
            // Set up Chrome driver
    } else if (browser.equalsIgnoreCase("firefox")) {
        System.out.println("Setting up Firefox driver");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
            // Set up Firefox driver
    }else if (browser.equalsIgnoreCase("edge")) {
        System.out.println("Setting up edge driver");
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
            // Set up Edge driver
    } else {
        System.out.println("Invalid browser specified");
    }
}


}



