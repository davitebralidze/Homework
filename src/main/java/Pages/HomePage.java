package Pages;

import Data.HomePageData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class HomePage implements HomePageData {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By sortMenuXPath = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select");

    public int actualSortSize() {
        Select selectOptions = new Select(driver.findElement(sortMenuXPath));
        return selectOptions.getOptions().size();
    }

    public boolean checkBurgerMenuVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(burgerMenu)));
        return driver.findElement(burgerMenu).isDisplayed();
    }

    public boolean checkSortDropdownVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(sortMenuXPath)));
        return driver.findElement(sortMenuXPath).isDisplayed();

    }

    public ArrayList<String> getExpectedSortData() {
        ArrayList<String> sortData = new ArrayList<>();
        sortData.add(az);
        sortData.add(za);
        sortData.add(LoHi);
        sortData.add(HiLo);
        return sortData;
    }

    public ArrayList<String> getActualSortData() {
        ArrayList<String> actualSortData = new ArrayList<>();
        for (int i = 0; i < actualSortSize(); i++) {
            Select sortData = new Select(driver.findElement(sortMenuXPath));
            actualSortData.add(sortData.getOptions().get(i).getText());
        }
        return actualSortData;
    }

}
