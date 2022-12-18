package pages.menu;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.helpers.BasePage;
import stepDefinitions.Hooks;
import java.util.List;


import static java.lang.Integer.*;

public class CartPage extends BasePage {

    public CartPage() {
        driver = Hooks.driver;
        PageFactory.initElements(driver, this);
    }

    private static Logger log = LoggerFactory.getLogger("CartPage.class");

    @FindBy(css = "tr > td")
    private List<WebElement> productDetails;

    @FindBy(css = "[class='btn btn-success']")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//td/img")
    private WebElement productImage;

    @FindBy(id = "totalp")
    private WebElement totalP;

    @FindBy(xpath = "//a[text()='Delete']")
    private List<WebElement> deleteButtons;

    public CartPage deleteButtonWorks() {
        int size = deleteButtons.size();
        int randomRemove = random.nextInt(size);
        if (randomRemove > 1) {
            String nameOfRemovedProduct = getTextFromElement(productDetails.get(4));
            log.info("Removed product: " + nameOfRemovedProduct);
        } else {
            String nameOfRemovedProduct = getTextFromElement(productDetails.get(1));
            log.info("Removed product: " + nameOfRemovedProduct);
        }
        clickOnElement(deleteButtons.get(randomRemove));

        waitToBeInvisible(deleteButtons.get(randomRemove));
        waitToBeVisible(productImage);

        return this;
    }

    public CartPage clickOnPlaceOrderButton() {
        clickOnElement(placeOrderButton);
        return this;
    }

    public int getProductPrice() {
        waitToBeVisibleAllElements(productDetails);
        int price = parseInt(getTextFromElement(productDetails.get(2)));
        int price1 = parseInt(getTextFromElement(productDetails.get(6)));
        int sum = price + price1;
        log.info("Sum of product prices in cart page: " + sum);
        return sum;
    }


    public int getProductPriceAfterDeleteOneItem() {
        int price = parseInt(getTextFromElement(productDetails.get(2)));
        log.info("Sum of product prices in cart page: " + price);
        return price;
    }

    public int totalPrice() {
        int total = parseInt(getTextFromElement(totalP));
        log.info("Displayed total price: " + total);
        return total;
    }
}
