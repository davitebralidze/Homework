import Data.HomePageData;
import Data.LoginPageData;
import Pages.HomePage;
import Pages.LoginPage;
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

public class HomePageTest implements LoginPageData, HomePageData {
    WebDriver driver;


    @BeforeMethod(description = "Opening the google chrome browser and logging in with valid credentials", groups = "Group2 For Presentation")
    public void setup() {
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmail(validEmail);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();
    }

    @Test(description = "Checking the visibility of the sort dropdown menu", groups = "Group2 For Presentation", priority = 5)
    @Severity(SeverityLevel.MINOR)
    public void isSortMenuVisible() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.checkSortDropdownVisibility(), "The sort dropdown bar is not present");
    }

    @Test(description = "Checking the size of the sort dropdown menu", groups = "Group2 For Presentation", priority = 3)
    @Severity(SeverityLevel.MINOR)
    public void sortDropdownSize() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.actualSortSize(), expectedNumberOfElementsInSort);
    }

    @Test(description = "Checking the elements of the sort dropdown menu", groups = "Group2 For Presentation", priority = 1)
    @Severity(SeverityLevel.MINOR)
    public void sortDropdownElements() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getActualSortData(), homePage.getExpectedSortData());
    }

    @Test(description = "Checking whether the added product count comes in accordance with the indicator itself", groups = "Group2 For Presentation", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void doesCartShowTheCorrectCountOfProducts() {
        HomePage homePage = new HomePage(driver);
        homePage.addSauceLabsBikeLightToCart();
        homePage.addSauceLabsBoltTShirt();
        homePage.addSauceLabsBackPackProductToCart();

        Assert.assertEquals(homePage.getItemCountIndicatorTextOfTheCartIcon(), homePage.getExpectedNumberOfTheAddedProducts());

    }

    @Test(description = "Checking the log out function", groups = "Group2 For Presentation", priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    public void canUserLogOutFromBurgerMenu() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickOnBurgerMenu();
        homePage.clickOnLogOutFromBurgerMenu();

        Assert.assertTrue(loginPage.presenceOfLoginButton());
    }

    @AfterMethod(description = "Closing the chrome browser and taking the screenshot", groups = "Group2 For Presentation")
    public void finish() {
        driver.quit();
    }

}
