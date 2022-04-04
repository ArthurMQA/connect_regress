package steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public abstract class BaseTests {

    // Config data.
    protected final static String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    protected static String url;
    protected static String correctLogin;
    protected static String correctPassword;

    // Page object variables.
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static LoginPage loginPage;
    protected static MainPage mainPage;
    protected static ConnectMeetingPage meetingPage;
    protected static ConnectMeetingSpaces connectMeetingSpaces;
    protected static ConnectDashboard connectDashboard;
    protected static ConnectConferencePage conferencePage;


    protected static void setUp() {

        FileInputStream fileInputStream;
        Properties prop = new Properties();

        try {

            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            url = prop.getProperty("site_url");
            correctLogin = prop.getProperty("member_id");
            correctPassword = prop.getProperty("password");

        } catch (IOException e) {
            System.out.println("Error: file " + PATH_TO_PROPERTIES + " not found");
            e.printStackTrace();
        }
    }

    protected void openBrowser() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        conferencePage = PageFactory.initElements(driver, ConnectConferencePage.class);
        connectDashboard = PageFactory.initElements(driver, ConnectDashboard.class);
        meetingPage = PageFactory.initElements(driver, ConnectMeetingPage.class);
        connectMeetingSpaces = PageFactory.initElements(driver, ConnectMeetingSpaces.class);

    }

    protected void closeBrowser() {
        driver.quit();
    }

    protected void isElementDisplayed(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        assertTrue(webElement.isDisplayed());
    }

}
