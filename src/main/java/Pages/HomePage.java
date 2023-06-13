package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    WebDriver driver;
    ArrayList <WebElement> productList;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By productListXPath = By.xpath("//div[@class='inventory_item_name']");

    public boolean checkBurgerMenuVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(burgerMenu)));
        return driver.findElement(burgerMenu).isDisplayed();
    }

}
