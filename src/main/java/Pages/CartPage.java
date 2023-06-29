package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By sauceLabsBackPackInCart = By.xpath("//*[@id=\"item_4_title_link\"]/div");
    private final By removeButtonOfSauceLabsBackPackInCart = By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]");

    @Step("Check if the Sauce Labs Backpack is added in the cart")
    public boolean checkPresenceOfSauceLabsBackPackInCart() {
        try {
            return driver.findElement(sauceLabsBackPackInCart).isDisplayed();
        } catch (Exception NoElementFoundException) {
            return false;
        }
    }

    @Step("Click the remove button in the cart for the Sauce Labs backpack")
    public void clickOnRemoveButtonOfSauceLabsBackPackInCart() {
        driver.findElement(removeButtonOfSauceLabsBackPackInCart).click();
    }

}
