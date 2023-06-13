package Pages;

import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    //elements
    By logInEmail = By.xpath("//*[@id=\"user-name\"]");
    By logInPassword = By.xpath("//*[@id=\"password\"]");
    By logInButton = By.xpath("//*[@id=\"login-button\"]");

    @Step("email input:{0}")
    public LoginPage enterValidEmail(String validEmail) {
        driver.findElement(logInEmail).sendKeys(validEmail);
        return this;
    }
    @Step("invalid email input:{0}")
    public LoginPage enterInvalidEmail(String invalidEmail) {
        driver.findElement(logInEmail).sendKeys(invalidEmail);
        return this;
    }

    @Step ("password input:{0}")
    public LoginPage enterPassword(String password) {
        driver.findElement(logInPassword).sendKeys(password);
        return this;
    }

    @Step ("click on log in button")
    public LoginPage clickLogInButton() {
        driver.findElement(logInButton).click();
        return this;
    }

}
