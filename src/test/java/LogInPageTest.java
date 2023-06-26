import Data.LoginPageData;
import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogInPageTest implements LoginPageData {

    WebDriver driver;

    @BeforeMethod(description = "Opening the chrome browser")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();

    }

    @Test
    public void loginWithValidCredentialsTest() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.fillEmail(validEmail);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(homePage.checkBurgerMenuVisibility(), "The process of login failed");

    }

    @Test
    public void loginWithLockedOutUser() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.fillEmail(lockedOutUser);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.checkErrorMessagePresenceForLockedOutUser(), "The error message did not occur");

    }

    @Test
    public void checkTheErrorMessageInCaseOfInvalidUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.fillEmail(nonexistentUser);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getActualErrorMessageText(), loginPage.getExpectedErrorMessageText());
    }

//    @Test
//    public void performanceOfLoggingIn() {
//        LoginPage loginPage = new LoginPage(driver);
//        HomePage homePage = new HomePage(driver);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1));
//
//        loginPage.fillEmail(performanceGlitchUser);
//        loginPage.fillPassword(password);
//        loginPage.clickLoginButton();
//
//
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(homePage.getBurgerMenu())));
//
//    }


    @AfterMethod(description = "Closing chrome browser")
    public void finish() {
        driver.quit();
    }
}
