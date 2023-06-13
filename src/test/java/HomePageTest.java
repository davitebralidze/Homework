import Data.LoginPageData;
import Data.ProductNameData;
import Data.SortTypes;
import Pages.HomePage;
import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class HomePageTest implements LoginPageData, ProductNameData, SortTypes {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }

    @Test(priority = 1, description = "Does the sort contain every type")
    @Severity(SeverityLevel.NORMAL)
    public void sortTypes() {
        Select selectOptions = new Select(driver.findElement(By.className("product_sort_container")));
        int dropdownSize = selectOptions.getOptions().size();
        ArrayList <WebElement> sortList = new ArrayList<>();

        for (int i = 0; i < dropdownSize; i++) {
            selectOptions = new Select(driver.findElement(By.className("product_sort_container")));
            selectOptions.selectByIndex(i);
        }



    }


    @AfterMethod
    public void finish() {
        driver.quit();
    }

}
