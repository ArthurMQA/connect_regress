package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    @FindBy(xpath = "//*[@id='member_id']")
    public WebElement loginInput;

    @FindBy(xpath = "//*[@id='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//*[@id='turing']")
    public WebElement captchaInput;

    @FindBy(xpath ="//*[@class='btn btn--glare']")
    public WebElement loginButton;

    @FindBy(xpath ="//*[@id='sign-in']")
    public WebElement loginForm;

    @FindBy(xpath ="//*[@id='sign-in']/div/div/h2")
    public WebElement loginFormHeader;

    @FindBy(xpath ="//*[@id='sign-in']/div/div/form/div[4]")
    public  WebElement captchaValidationMessage;

    public LoginPage typeLogin(String login){
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        loginInput.sendKeys(login);
        return this;
    }

    public LoginPage typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage typeCaptcha(String captcha) {
        wait.until(ExpectedConditions.visibilityOf(captchaInput));
        captchaInput.sendKeys(captcha);
        return this;
    }

    public MainPage login(String login, String password, String captcha) {
        wait.until(ExpectedConditions.visibilityOf(loginForm));
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        captchaInput.sendKeys(captcha);
        loginButton.click();
        return new MainPage(driver);
    }

    public String getTimestamp() {
        String timestamp = System.currentTimeMillis() / 1000L + "";
        return timestamp;
    }

    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        return this;
    }

    public String getTitleText() {
        return driver.getTitle();
    }

}
