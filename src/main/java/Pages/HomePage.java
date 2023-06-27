package Pages;

import Data.HomePageData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    private final By cartButton = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    private final By sauceLabsBackPackAddToCartButton = By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]");
    private final By logOutButtonFromBurgerMenu = By.xpath("//*[@id=\"logout_sidebar_link\"]");

    public int actualSortSize() {
        Select selectOptions = new Select(driver.findElement(sortMenuXPath));
        return selectOptions.getOptions().size();
    }

    public By getBurgerMenu() {
        return burgerMenu;
    }

    public ArrayList<String> getExpectedSortData() {
        ArrayList<String> sortMenuData = new ArrayList<>();
        sortMenuData.add(az);
        sortMenuData.add(za);
        sortMenuData.add(LoHi);
        sortMenuData.add(HiLo);
        return sortMenuData;
    }

    public ArrayList<String> getActualSortData() {
        ArrayList<String> actualSortData = new ArrayList<>();
        for (int i = 0; i < actualSortSize(); i++) {
            Select sortData = new Select(driver.findElement(sortMenuXPath));
            actualSortData.add(sortData.getOptions().get(i).getText());
        }
        return actualSortData;
    }

    @Step("Check if the burger menu is present on the page (this can also be used to check whether the user is logged in or not)")
    public boolean checkBurgerMenuVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(burgerMenu)));
        return driver.findElement(burgerMenu).isDisplayed();
    }

    @Step("Click on the burger menu")
    public void clickOnBurgerMenu() {
        driver.findElement(burgerMenu).click();
    }

    @Step("Click on the log out button that is located in the burger menu")
    public void clickOnLogOutFromBurgerMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(logOutButtonFromBurgerMenu)));
        driver.findElement(logOutButtonFromBurgerMenu).click();
    }

    @Step("Check whether the dropdown bar of the sort is present or not")
    public boolean checkSortDropdownVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(sortMenuXPath)));
        return driver.findElement(sortMenuXPath).isDisplayed();

    }

    @Step("Click on the cart icon")
    public void clickOnCartIcon() {
        driver.findElement(cartButton).click();
    }

    @Step("Click on the add button of the Sauce Labs Backpack on the main page")
    public void addSauceLabsBackPackProductToCart() {
        driver.findElement(sauceLabsBackPackAddToCartButton).click();
    }

}
