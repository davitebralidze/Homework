package Utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static Data.LoginPageData.*;

public class ChromeSettings {

    public static WebDriver driver = new ChromeDriver();


    @BeforeMethod
    public void openChrome () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeChrome () {
        driver.close();
    }

}
