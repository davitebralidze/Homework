import Pages.LoginPage;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import static Data.LoginPageData.url;
import static Data.LoginPageData.password;
import static Data.LoginPageData.validEmail;
import static Data.LoginPageData.invalidEmail;

public class LogIn {

    LoginPage loginPage;
    WebDriver driver;

    @BeforeMethod
    public void beforeTest() {

        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().fullscreen();

    }

    @Test
    public void login() {

        loginPage.enterInvalidEmail(validEmail);
        loginPage.enterPassword(password);
        loginPage.clickLogInButton();

    }


}
