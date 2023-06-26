package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By sauceLabsBackPackInCart = By.xpath("//*[@id=\"item_4_title_link\"]/div");
    private final String expectedNameOfTheProductSauceLabsBackPackInCart = "Sauce Labs Backpack";
    private final By removeButtonOfSauceLabsBackPackInCart = By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]");


    public String getTheTextOfSauceLabsBackPackInCart() {
        return driver.findElement(sauceLabsBackPackInCart).getText();
    }

    public void clickOnRemoveButtonOfSauceLabsBackPackInCart() {
        driver.findElement(removeButtonOfSauceLabsBackPackInCart).click();
    }

    public String getExpectedNameOfTheProductSauceLabsBackPackInCart() {
        return expectedNameOfTheProductSauceLabsBackPackInCart;
    }

    public String getActualNameOfTheProductSauceLabsBackPackInCart() {
        return driver.findElement(sauceLabsBackPackInCart).getText();
    }

    public boolean checkPresenceOfSauceLabsBackPackInCart() {
        try {
            return driver.findElement(sauceLabsBackPackInCart).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


}
