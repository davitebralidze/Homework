import Data.LoginPageData;
import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogInPageTest implements LoginPageData {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Logging in with valid credentials")
    @Link("https://www.saucedemo.com/")
    public void loginWithValidCredentialsTest()  {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.fillEmail(validEmail);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(homePage.checkBurgerMenuVisibility(), "The process of login failed");

    }

    @Test
    public void loginWithInvalidCredentialsTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.fillEmail(invalidEmail);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.checkErrorMessagePresence(), "The error message did not occur");


    }

    @AfterMethod
    public void finish() {
        driver.quit();
    }
}
