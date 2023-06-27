package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By emailField = By.xpath("//*[@id=\"user-name\"]");
    private final By errorMessage = By.xpath("//div[@class='error-message-container error']");
    private final By passwordField = By.id("password");
    private final By loginButton = By.name("login-button");
    private final String expectedErrorText = "The password, or the email, you’ve entered is incorrect.";

    public String getActualErrorMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));     /*Explicit wait*/
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(errorMessage)));   /*Explicit wait*/
        return driver.findElement(errorMessage).getText();
    }

    public String getExpectedErrorMessageText() {
        return expectedErrorText;
    }

    public void fillEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void fillPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean presenceOfLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginButton)));
        return driver.findElement(loginButton).isDisplayed();
    }

    public boolean checkErrorMessagePresenceForLockedOutUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));     /*Explicit wait*/
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(errorMessage)));   /*Explicit wait*/
        return driver.findElement(errorMessage).isDisplayed();
    }


}
