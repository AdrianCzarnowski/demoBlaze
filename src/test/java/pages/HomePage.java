package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.helpers.BasePage;
import stepDefinitions.Hooks;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(){
        driver = Hooks.driver;
        PageFactory.initElements(driver, this);
    }

    private static Logger log = LoggerFactory.getLogger("MenuPage.class");

    @FindBy(css="[id='nameofuser']")
    private WebElement welcomeText;
    @FindBy(css="[class='card-block'] [class='card-title']")
    private List<WebElement> productNameList;

    @FindBy(xpath = "//div[@class='card-block']/h5")
    private List<WebElement> priceList;

    @FindBy(css="[class='hrefch']")
    private List<WebElement> productList;

    @FindBy(css="li.nav-item.active > a")
    private WebElement homeButton;

    @FindBy(css="[class='carousel-item active']")
    private WebElement homePageProductsImageOnTop;

    @FindBy(css="[class='card-img-top img-fluid']")
    List<WebElement> productImages;

    public HomePage goToHomePage(){
        clickOnElement(homeButton);
        waitToBeInvisible(homePageProductsImageOnTop);
        return this;
    }
    public HomePage clickButtonViaID(String idValue){
        waitForPageLoaded();
        clickOnElement(driver.findElement(By.cssSelector("[id='"+idValue+"']")));
        return this;
    }


    public HomePage selectProductCategory(String category){
        WebElement element = driver.findElement(By.xpath("//a[text()='"+category+"']"));
        waitToBeClickable(element);
        clickOnElement(element);
        waitToBeInvisible(productImages.get(productImages.size() -1));
        waitToBeVisibleAllElements(productImages);
        return this;
    }

    public int sizeOfProductList(){
        int number = priceList.size();
        log.info("Size: " + number);
        return number;
    }
    public HomePage clickOnRandomProduct(int i){
        waitForPageLoaded();
        clickOnElement(productList.get(i));
        return this;
    }

    public String getProductName(int i){
        String name = getTextFromElement(productNameList.get(i));
        log.info("Product name: " + name);
        return name;
    }

    public String getProductPrice(int i){
        String price = getTextFromElement(priceList.get(i));
        log.info("Product price: " + price);
        return price;
    }
    public String getTextFromWelcomePage(){
        String text = getTextFromElement(welcomeText).replace("Welcome ","");
        log.info(text);
        return text;
    }

    public String getProductImageSrc(int i){
        String image = getAttributeValueFromElement(productImages.get(i), "src");
        log.info("Image: " + image);
        return image;
    }

}
