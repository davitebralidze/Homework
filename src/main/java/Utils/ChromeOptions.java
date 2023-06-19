package Utils;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static Data.LoginPageData.*;

public class ChromeOptions {

    public static WebDriver driver = new ChromeDriver();

    @BeforeMethod
    public void openChrome() {
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeChrome() {
        driver.quit();
    }

}
