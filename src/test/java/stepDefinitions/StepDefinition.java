package stepDefinitions;

import base.BaseTest;
import configuration.factory.UserFactory;
import configuration.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static java.lang.System.getProperty;
import static org.testng.Assert.*;

public class StepDefinition extends BaseTest {

    private static Logger logger = LoggerFactory.getLogger("StepDefinition.class");

    User user = new UserFactory().getRandomUser();
    private final String randomName = singUpPage.generateRandomName(user);
    private final String randomPassword = singUpPage.generateRandomPassword(user);

    @Given("I have data to create new account with random value and user can be login")
    public void i_have_data_to_create_new_account_with_random_value_and_user_can_be_login() {
        homePage
                .clickButtonViaID(getProperty("sing_in_id"));
        singUpPage
                .fillForm(randomName, randomPassword, getProperty("register_button_value"), getProperty("user_name_id"), getProperty("user_password_id"))
                .closeAlert();
        homePage
                .clickButtonViaID(getProperty("login_id"));
        loginPage
                .fillForm(randomName, randomPassword, getProperty("login_button_value"), getProperty("login_user_id"), getProperty("password_user_id"));
        assertEquals(homePage.getTextFromWelcomePage(), randomName);

    }

    @When("User should choose product category and random product from category")
    public void user_should_choose_product_category_and_random_product_from_category() {
        homePage
                .selectProductCategory(getProperty("category"));

        int number = getRandomNumber();
        String productName = homePage.getProductName(number);
        String productPrice = homePage.getProductPrice(number);
        String productImage = homePage.getProductImageSrc(number);

        homePage
                .clickOnRandomProduct(number);
        assertEquals(productName, productDetailsPage.getProductName());
        assertEquals(productPrice, productDetailsPage.getProductPrice());
        assertEquals(productImage, productDetailsPage.getProductImageSrc());
    }

    @When("User should perform add to basket and remove from basket")
    public void user_should_perform_add_to_basket_and_remove_from_basket() {
        String productName = productDetailsPage.getProductName();
        String productPrice = productDetailsPage.getProductPrice().replace("$", "");
        productDetailsPage
                .clickOnAddToCart();
        singUpPage
                .closeAlert();
        homePage
                .goToHomePage()
                .selectProductCategory(getProperty("category"));
        int number = getRandomNumber();
        homePage
                .clickOnRandomProduct(number);
        productDetailsPage
                .clickOnAddToCart();
        singUpPage
                .closeAlert();
        homePage
                .clickButtonViaID(getProperty("cart_button_id"));
        assertEquals(cartPage.getProductPrice(), cartPage.totalPrice());
        cartPage
                .deleteButtonWorks();
        assertEquals(cartPage.getProductPriceAfterDeleteOneItem(), cartPage.totalPrice());
        cartPage
                .clickOnPlaceOrderButton();
    }

    @Then("User should fill order form and see order confirm")
    public void user_should_fill_order_form_and_see_order_confirm() {

        String country = placeOrderPage.generateRandomCountry(user);
        String city = placeOrderPage.generateRandomCity(user);
        int card = placeOrderPage.generateRandomCardNumber(user);
        String totalAm = getProperty("amount_message") + ": " + placeOrderPage.totalAmount() + " " + getProperty("currency");

        placeOrderPage
                .fillForm(randomName, country, city, card, getProperty("month"), 2022);

        String message = placeOrderPage.purchaseDetailsMessage();

        assertTrue(placeOrderPage.isDisplayedGreenImg(placeOrderPage.greenMark));
        assertEquals(placeOrderPage.getThankYouMessage(), getProperty("purchase_message"));
        assertTrue(message.contains(totalAm));
        assertTrue(message.contains(placeOrderPage.currentDateMinusOneMonth()));
        assertTrue(message.contains(getProperty("card_message") + ": " + card));
        assertTrue(message.contains(getProperty("name_message") + ": " + randomName));
        assertTrue(message.contains(getProperty("date_message") + ": " + placeOrderPage.currentDateMinusOneMonth()));
        assertTrue(placeOrderPage.isDisplayedGreenImg(placeOrderPage.okButton));

        placeOrderPage
                .confirmPurchaseDetails();
        homePage
                .clickButtonViaID(getProperty("logout_id"));
    }

    private int getRandomNumber() {
        Random random = new Random();
        int randomProductNumber = random.nextInt(homePage.sizeOfProductList() - 1);
        return randomProductNumber;
    }
}


