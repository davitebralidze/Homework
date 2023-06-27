package Pages;

import io.qameta.allure.Step;
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

    @Step("Fill the email field with the valid/invalid email, according to the test case")
    public void fillEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Fill the password field with the valid/invalid password, according to the test case")
    public void fillPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click on the Log In button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Check whether the login button is present or not")
    public boolean presenceOfLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginButton)));
        return driver.findElement(loginButton).isDisplayed();
    }

    @Step("Check the error message presence for the locked out user")
    public boolean checkErrorMessagePresenceForLockedOutUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));     /*Explicit wait*/
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(errorMessage)));   /*Explicit wait*/
        return driver.findElement(errorMessage).isDisplayed();
    }


}
