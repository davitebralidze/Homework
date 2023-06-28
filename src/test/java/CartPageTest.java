import Data.HomePageData;
import Data.LoginPageData;
import Pages.CartPage;
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

public class CartPageTest implements LoginPageData, HomePageData {
    WebDriver driver;

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @BeforeMethod(description = "Opening the google chrome browser and logging in with valid credentials", groups = {"Group3 For Presentation",
            "This group is just to show that we can give 2 different groups to 1 test"})
    public void setup() {
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmail(validEmail);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();
    }

    @Test(description = "Checking whether the selected product is added to the cart", groups = "Group3 For Presentation")
    @Severity(SeverityLevel.CRITICAL)
    public void isProductAddedToTheCart() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.addSauceLabsBackPackProductToCart();
        homePage.clickOnCartIcon();

        Assert.assertTrue(cartPage.checkPresenceOfSauceLabsBackPackInCart());

    }

    @Test(description = "Checking whether the selected product is removed from the cart", groups = "Group3 For Presentation")
    @Severity(SeverityLevel.CRITICAL)
    public void isProductRemovedFromTheCart() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.addSauceLabsBackPackProductToCart();
        homePage.clickOnCartIcon();
        cartPage.clickOnRemoveButtonOfSauceLabsBackPackInCart();

        Assert.assertFalse(cartPage.checkPresenceOfSauceLabsBackPackInCart());
    }

    @AfterMethod(description = "Closing the chrome browser and taking the screenshot", groups = "Group3 For Presentation")
    public void finish() {
        takeScreenshot();
        driver.quit();
    }

}
