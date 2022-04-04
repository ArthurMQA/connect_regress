package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    @FindBy(xpath ="//*[@id='logoutBtn']")
    public WebElement logoutButton;

    @FindBy(xpath ="//*[@data-original-title='ADX Connect']")
    public WebElement adxConnectService;

    public String getTitleText() {
        return driver.getTitle();
    }

    public MainPage isLoginCorrect() {
        assertTrue(logoutButton.isDisplayed());
        Assert.assertEquals("ADX", getTitleText());
        return this;
    }

    public ConnectDashboard moveToAdxConnect() {
        adxConnectService.click();
        for (String tab : driver.getWindowHandles()){
            driver.switchTo().window(tab);
        }
        return new ConnectDashboard(driver);
    }

}
