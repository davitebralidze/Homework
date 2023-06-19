import Data.HomePageData;
import Data.LoginPageData;
import Pages.HomePage;
import Pages.LoginPage;
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

    @Test(priority = 1, description = "Sort dropdown presence case")
    public void isSortMenuVisibleTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.checkSortDropdownVisibility(), "The sort dropdown bar is not present");
    }

    @Test(priority = 2, description = "Number of elements in the sort dropdown bar")
    public void sortDropdownSizeTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.actualSortSize(), expectedNumberOfElementsInSort);
    }

    @Test(priority = 3, description = "Elements of the sort dropdown bar")
    public void sortDropdownElementsTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getActualSortData(), homePage.getExpectedSortData());
    }


    @AfterMethod
    public void finish() {
        driver.quit();
    }

}
