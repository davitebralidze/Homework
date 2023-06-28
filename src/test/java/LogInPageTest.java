import Data.LoginPageData;
import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogInPageTest implements LoginPageData {

    WebDriver driver;

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @BeforeMethod (description = "Opening the google chrome browser", groups = "Group1 For Presentation")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();

    }

    @Test (description = "Checking the process of logging in with valid credentials", groups = "Group1 For Presentation", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void loginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.fillEmail(validEmail);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(homePage.checkBurgerMenuVisibility(), "The process of login failed");

    }

    @Test (description = "Checking the error message presence while logging in with locked out user", groups = "Group1 For Presentation", priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithLockedOutUser() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.fillEmail(lockedOutUser);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.checkErrorMessagePresenceForLockedOutUser(), "The error message did not occur");

    }

    @Test (description = "Checking the error message while logging in with invalid credentials", groups = "Group1 For Presentation", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void checkTheErrorMessageInCaseOfInvalidUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.fillEmail(nonexistentUser);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getActualErrorMessageText(), loginPage.getExpectedErrorMessageText());
    }

    @AfterMethod (description = "Closing the google chrome browser and taking a screenshot", groups = "Group1 For Presentation")
    public void finish() {
        takeScreenshot();
        driver.quit();
    }
}
