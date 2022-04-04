package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MyStepdefs extends BaseTests {

    @BeforeStep
    public static void initialization() {
        setUp();
    }

    @Before
    public void start() {
        openBrowser();
    }

    @After
    public void finish() {
        closeBrowser();
    }

    @Given("I go to the ADX login page")
    public void iGoToTheADXLoginPage() {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @And("I see login page")
    public void iSeeLoginPage() {
        Assert.assertEquals("ADX - Log in to continue.", loginPage.getTitleText());
    }

    @When("I type my credentials and click Login button")
    public void iTypeMyCredentialsAndClickLoginButton() {
        loginPage.login(correctLogin, correctPassword, loginPage.getTimestamp());
    }

    @And("I log into the ADX Sales service")
    public void iLogIntoTheADXSalesService() {
        mainPage.isLoginCorrect();
    }

    @And("I move on the ADX Connect service")
    public void iMoveOnTheADXConnectService() {
        mainPage.moveToAdxConnect();
    }

    @And("I see ADX Connect dashboard")
    public void iSeeADXConnectDashboard() {
        isElementDisplayed(connectDashboard.manageSpacesButton);
    }

    @And("I move to My meeting spaces page")
    public void iMoveToMyMeetingSpacesPage() throws InterruptedException {
        Thread.sleep(7000);
        connectDashboard.moveToMeetingSpaces();
        connectMeetingSpaces.checkThatPresentationUploadButtonIsDisplayed();
    }

    @And("I start new Conference")
    public void iStartNewConference() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        connectMeetingSpaces.createNewConference();
        Thread.sleep(5000);
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> it = tabs.iterator();
        String tab1 = it.next();
        String tab2 = it.next();
        String tab3 = it.next();
        driver.switchTo().window(tab1);
        driver.close();
        driver.switchTo().window(tab2);
        driver.close();
        driver.switchTo().window(tab3);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        conferencePage.allowAudio();
    }

    @Then("I see that new Conference room is successfully started")
    public void iSeeThatNewConferenceRoomIsSuccessfullyStarted() {
        conferencePage.checkThatConferenceStartedSuccessfully();
    }

    @And("I can chat in the Conference room")
    public void iCanChatInTheRoom() throws InterruptedException {
        Thread.sleep(5000);
        conferencePage.checkThatChatIsWorks("hello");
    }

    @And("I can to upload PDF files in the Conference room")
    public void iCanToUploadPDFFilesInTheRoom() {
        conferencePage.checkThatPdfIsUploaded();
    }

    @And("I can end the Conference")
    public void iCanEndTheSession() throws InterruptedException {
        conferencePage.endConference();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Thread.sleep(1000);
        //Assert.assertEquals("ADX Connect", driver.getTitle());
        Assert.assertTrue(driver.getTitle().endsWith("Webinar / Conference is over"));
    }

    @And("I start new Meeting")
    public void iStartNewMeeting() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        connectMeetingSpaces.createNewMeeting();
        Thread.sleep(5000);
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> it = tabs.iterator();
        String tab1 = it.next();
        String tab2 = it.next();
        String tab3 = it.next();
        driver.switchTo().window(tab1);
        driver.close();
        driver.switchTo().window(tab2);
        driver.close();
        driver.switchTo().window(tab3);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        meetingPage.allowAudio();
    }

    @Then("I see that new Meeting room is successfully started")
    public void iSeeThatNewMeetingRoomIsSuccessfullyStarted() {
        meetingPage.checkThatMeetingStartedSuccessfully();
    }

    @And("I can chat in the Meeting room")
    public void iCanChatInTheMeetingRoom() throws InterruptedException {
        Thread.sleep(7000);
        meetingPage.checkThatChatIsWorks("hello");
    }

    @And("I can to upload PDF files in the Meeting room")
    public void iCanToUploadPDFFilesInTheMeetingRoom() {
        meetingPage.checkThatPdfIsUploaded();
    }

    @And("I can end the Meeting")
    public void iCanEndTheMeeting() throws InterruptedException {
        meetingPage.endMeeting();
        Thread.sleep(7000);
        Assert.assertTrue(driver.getTitle().endsWith("Meeting is over"));
    }
}
