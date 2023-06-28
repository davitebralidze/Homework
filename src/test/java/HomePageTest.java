import Data.HomePageData;
import Data.LoginPageData;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import io.qameta.allure.Attachment;
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

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @BeforeMethod (description = "Opening the google chrome browser and logging in with valid credentials", groups = "Group2 For Presentation")
    public void setup() {
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmail(validEmail);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();
    }

    @Test (description = "Checking the visibility of the sort dropdown menu", groups = "Group2 For Presentation")
    public void isSortMenuVisible() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.checkSortDropdownVisibility(), "The sort dropdown bar is not present");
    }

    @Test (description = "Checking the size of the sort dropdown menu", groups = "Group2 For Presentation")
    public void sortDropdownSize() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.actualSortSize(), expectedNumberOfElementsInSort);
    }

    @Test (description = "Checking the elements of the sort dropdown menu", groups = "Group2 For Presentation")
    public void sortDropdownElements() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getActualSortData(), homePage.getExpectedSortData());
    }

    @Test (description = "Checking whether the added product count comes in accordance with the indicator itself", groups = "Group2 For Presentation")
    public void doesCartShowTheCorrectCountOfProducts() {
        HomePage homePage = new HomePage(driver);
        homePage.addSauceLabsBikeLightToCart();
        homePage.addSauceLabsBoltTShirt();
        homePage.addSauceLabsBackPackProductToCart();

        int expectedIntegerOfTheIndicator = 3;

        Assert.assertEquals(homePage.getItemCountIndicatorTextOfTheCartIcon(), expectedIntegerOfTheIndicator);

    }

    @Test (description = "Checking the log out function", groups = "Group2 For Presentation")
    public void canUserLogOutFromBurgerMenu() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickOnBurgerMenu();
        homePage.clickOnLogOutFromBurgerMenu();

        Assert.assertTrue(loginPage.presenceOfLoginButton());
    }

    @AfterMethod (description = "Closing the chrome browser and taking the screenshot", groups = "Group2 For Presentation")
    public void finish() {
        takeScreenshot();
        driver.quit();
    }

}
