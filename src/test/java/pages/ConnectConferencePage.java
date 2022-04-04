package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class ConnectConferencePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ConnectConferencePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    @FindBy(xpath ="//*[@class='leave']")
    public WebElement endButton;

    @FindBy(xpath ="//*[@class='chat-search']")
    public WebElement chatSearch;

    @FindBy(xpath ="//*[@id='confInput']")
    public WebElement chatInput;

    @FindBy(xpath ="//*[@class='archive']")
    public WebElement filesUploadButton;

    @FindBy(xpath ="//*[@id='app-container']/div[1]/div[2]/div[1]/div[4]/div[2]/div/div/div/div[1]/div/input")
    public WebElement dropZone;

    @FindBy(xpath ="//*[@class='message-text']")
    public WebElement message;

    @FindBy(xpath ="//*[@class='file-name']")
    public WebElement fileName;

    @FindBy(xpath ="//*[@class='btn btn--next']")
    public WebElement confirmEndButton;

    @FindBy(xpath ="//*[@class='autoplay-prompt']")
    public WebElement allowAudioWindow;

    @FindBy(xpath ="//*[@class='chat-send']")
    public WebElement sendMessageButton;

    public ConnectConferencePage checkThatConferenceStartedSuccessfully() {
        assertTrue(endButton.isDisplayed());
        assertTrue(chatSearch.isDisplayed());
        assertTrue(chatInput.isDisplayed());
        return this;
    }

    public void checkThatChatIsWorks(String phrase) {
        wait.until(ExpectedConditions.visibilityOf(chatInput));
        chatInput.sendKeys(phrase);
        sendMessageButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(message.getText(), phrase);
    }

    public void checkThatPdfIsUploaded() {
        wait.until(ExpectedConditions.visibilityOf(filesUploadButton));
        filesUploadButton.click();
        File file = new File("src/main/resources/Policies.pdf");
        dropZone.sendKeys(file.getAbsolutePath());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(fileName.getText(), "Policies.pdf");
    }

    public void endConference() {
        wait.until(ExpectedConditions.visibilityOf(endButton));
        endButton.click();
        wait.until(ExpectedConditions.visibilityOf(confirmEndButton));
        confirmEndButton.click();
    }

    public void allowAudio() {
        wait.until(ExpectedConditions.visibilityOf(allowAudioWindow));
        if (driver.findElements(By.xpath("//*[@id='start-meeting']/span")).size() > 0) {
            driver.findElement(By.xpath("//*[@id='start-meeting']/span")).click();
        } else {  }
    }

}
