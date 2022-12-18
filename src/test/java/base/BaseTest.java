package base;

import pages.menu.CartPage;
import pages.menu.LoginPage;
import pages.HomePage;
import pages.menu.SingUpPage;
import pages.order.PlaceOrderPage;
import pages.order.ProductDetailsPage;

public class BaseTest {

    public HomePage homePage = new HomePage();
    public SingUpPage singUpPage = new SingUpPage();
    public LoginPage loginPage = new LoginPage();
    public ProductDetailsPage productDetailsPage = new ProductDetailsPage();
    public CartPage cartPage = new CartPage();
    public PlaceOrderPage placeOrderPage = new PlaceOrderPage();
}
