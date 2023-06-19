import Data.LoginPageData;
import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LogInPageTest implements LoginPageData {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }

    @Test(priority = 1, description = "Log in case, valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithValidCredentialsTest()  {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.fillEmail(validEmail);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(homePage.checkBurgerMenuVisibility(), "The process of login failed");

    }

    @Test(priority = 2, description = "Log in case, invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
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
