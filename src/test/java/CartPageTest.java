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

public class CartPageTest implements LoginPageData, HomePageData {
    WebDriver driver;

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

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
    public void isProductAddedToTheCart() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.addSauceLabsBackPackProductToCart();
        homePage.clickOnCartIcon();

        Assert.assertTrue(cartPage.checkPresenceOfSauceLabsBackPackInCart());

    }

    @Test
    public void isProductRemovedFromTheCart() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.addSauceLabsBackPackProductToCart();
        homePage.clickOnCartIcon();
        cartPage.clickOnRemoveButtonOfSauceLabsBackPackInCart();

        Assert.assertFalse(cartPage.checkPresenceOfSauceLabsBackPackInCart());
    }

    @AfterMethod
    public void finish() {
        takeScreenshot();
        driver.quit();
    }

}