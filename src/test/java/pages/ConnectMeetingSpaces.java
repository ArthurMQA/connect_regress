package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ConnectMeetingSpaces {

    private WebDriver driver;
    private WebDriverWait wait;

    public ConnectMeetingSpaces(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    @FindBy(xpath ="//*[@data-tip='create-space']")
    public WebElement createNewSpaceDropdown;

    @FindBy(xpath ="//*[@class='dropdown__container create-room-dropdown']/div[1]")
    public WebElement createConference;

    @FindBy(xpath ="//*[@class='dropdown__container create-room-dropdown']/div[2]")
    public WebElement createMeeting;

    @FindBy(xpath ="//*[@id='roomPresentation']/div/div[1]/div[1]/div[1]/span")
    public WebElement uploadFileButton;

    @FindBy(xpath ="//*[@id='roomPresentation']/div/div[1]/div[1]/div[2]/div[1]/div")
    public WebElement presentationDropdown;

    @FindBy(xpath ="//*[@data-tip='Policies and Procedures.pdf']")
    public WebElement presentationName;

    String selectLinkOpenInNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);

    public ConnectConferencePage createNewConference() {
        wait.until(ExpectedConditions.visibilityOf(createNewSpaceDropdown));
        createNewSpaceDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(createConference));
        createConference.click();
        return new ConnectConferencePage(driver);
    }

    public ConnectMeetingPage createNewMeeting() {
        wait.until(ExpectedConditions.visibilityOf(createNewSpaceDropdown));
        createNewSpaceDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(createMeeting));
        createMeeting.click();
        return new ConnectMeetingPage(driver);
    }

    public void checkThatPresentationUploadButtonIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(uploadFileButton));
        uploadFileButton.click();
    }

}
