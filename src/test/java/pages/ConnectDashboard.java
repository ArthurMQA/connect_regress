package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConnectDashboard {

    private WebDriver driver;
    private WebDriverWait wait;

    public ConnectDashboard(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    @FindBy(xpath ="//*[@id='manage-spaces_item']")
    public WebElement manageSpacesButton;

    public ConnectMeetingSpaces moveToMeetingSpaces() {
        wait.until(ExpectedConditions.visibilityOf(manageSpacesButton));
        manageSpacesButton.click();
        return new ConnectMeetingSpaces(driver);
    }

}
