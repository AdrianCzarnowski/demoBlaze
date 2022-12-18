package pages.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.helpers.BasePage;
import stepDefinitions.Hooks;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage() {
        driver = Hooks.driver;
        PageFactory.initElements(driver, this);
    }

    private static Logger log = LoggerFactory.getLogger("ProductPage.class");

    @FindBy(xpath = "//h2")
    private WebElement productName;

    @FindBy(xpath = "//h3")
    private WebElement productPrice;

    @FindBy(css = "[class='btn btn-success btn-lg']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='item active']/img")
    private WebElement productImage;

    public ProductDetailsPage clickOnAddToCart() {
        clickOnElement(addToCartButton);
        return this;
    }

    public String getProductName() {
        waitToBeVisible(productImage);
        String name = productName.getText();
        log.info("Name of product in product page: " + name);
        return name;
    }

    public String getProductPrice() {
        String price = productPrice.getText().replace(" *includes tax", "");
        log.info("Product price in product page: " + price);
        return price;
    }

    public String getProductImageSrc() {
        String source = getAttributeValueFromElement(productImage, "src");
        log.info("Product image source in product page: " + source);
        return source;
    }
}
