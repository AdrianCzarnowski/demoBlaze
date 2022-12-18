package pages.order;

import configuration.model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.helpers.BasePage;
import stepDefinitions.Hooks;

public class PlaceOrderPage extends BasePage {

    public PlaceOrderPage() {
        driver = Hooks.driver;
        PageFactory.initElements(driver, this);
    }

    private static Logger log = LoggerFactory.getLogger("PlaceOrderPage.class");

    @FindBy(css = "[class='modal-content']")
    private WebElement modal;

    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "country")
    private WebElement country;
    @FindBy(id = "city")
    private WebElement city;
    @FindBy(id = "card")
    private WebElement card;

    @FindBy(id = "month")
    private WebElement month;

    @FindBy(id = "year")
    private WebElement year;

    @FindBy(id = "totalm")
    private WebElement totalm;

    @FindBy(css = "[onclick='purchaseOrder()']")
    private WebElement purchaseButton;

    @FindBy(css = "[class='lead text-muted ']")
    private WebElement textWithData;

    @FindBy(css="[class='sa-placeholder']")
    public WebElement  greenMark;

    @FindBy(css="[class='confirm btn btn-lg btn-primary']")
    public WebElement okButton;

    @FindBy(css=".visible > h2")
    private WebElement thankYouMessage;

    public PlaceOrderPage fillForm(String userName, String userCountry, String userCity, int userCardNumber, String fakeMonth, int fakeYear) {
        waitToBeClickable(name);
        sendKeys(name, userName);
        sendKeys(country, userCountry);
        sendKeys(city, userCity);
        sendKeys(card, String.valueOf(userCardNumber));
        sendKeys(month, fakeMonth);
        sendKeys(year, String.valueOf(fakeYear));
        log.info("Data used to fill form: " +"\n"+"User name: " +userName +"\n"+"Country: "+ userCountry+"\n"+"City: "
                + userCity +"\n" + "Card number: "+ userCardNumber +"\n"+"Month: "+fakeMonth+"\n"+"Year: "+fakeYear);
        clickOnElement(purchaseButton);
        return this;
    }

    public PlaceOrderPage confirmPurchaseDetails(){
        clickOnElement(okButton);
        return this;
    }

    public String getThankYouMessage(){
        String message = getTextFromElement(thankYouMessage);
        log.info("Thank you message: "+ message);
        return message;
    }
    public boolean isDisplayedGreenImg(WebElement element){
        boolean isDisplayed = false;
        if(element.isDisplayed()){
            isDisplayed = true;
        }
        return isDisplayed;
    }

    public String purchaseDetailsMessage() {
        String t = getTextFromElement(textWithData);
        log.info(t);
        return t;
    }

    public String currentDateMinusOneMonth(){
        String d = getTodayDate();
        return d;
    }


    public String totalAmount() {
        String total = getTextFromElement(totalm).replace("Total: ", "");
        log.info("Total amount: " + total);
        return total;
    }

    public String generateRandomCountry(User user) {
        String country = user.getCountry();
        return country;
    }

    public String generateRandomCity(User user) {
        String city = user.getCity();
        return city;
    }

    public int generateRandomCardNumber(User user) {
        int card = user.getCard();
        return card;
    }
}
