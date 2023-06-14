import Data.HomePageData;
import Data.LoginPageData;
import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest implements LoginPageData, HomePageData {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmail(validEmail);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();
    }

    @Test(priority = 1, description = "Sort dropdown presence case")
    @Severity(SeverityLevel.NORMAL)
    public void isSortMenuVisible() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.checkSortDropdownVisibility(), "The sort dropdown bar is not present");
    }

    @Test(priority = 2, description = "Number of elements in the sort dropdown bar")
    @Severity(SeverityLevel.CRITICAL)
    public void sortDropdownSizeTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.actualSortSize(), expectedNumberOfElementsInSort);
    }

    //does sort menu have right elements

    @AfterMethod
    public void finish() {
        driver.quit();
    }

}
