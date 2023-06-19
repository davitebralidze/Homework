import Data.HomePageData;
import Data.LoginPageData;
import Pages.HomePage;
import Pages.LoginPage;
import io.qameta.allure.Description;
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
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmail(validEmail);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();
    }

    @Test
    public void isSortMenuVisibleTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.checkSortDropdownVisibility(), "The sort dropdown bar is not present");
    }

    @Test
    public void sortDropdownSizeTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.actualSortSize(), expectedNumberOfElementsInSort);
    }

    @Test
    public void sortDropdownElementsTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getActualSortData(), homePage.getExpectedSortData());
    }


    @AfterMethod
    public void finish() {
        driver.quit();
    }

}
