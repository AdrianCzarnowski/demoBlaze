package pages.helpers;

import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stepDefinitions.Hooks;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;


public class BasePage {
    protected static Random random = new Random();
    private static Logger log = LoggerFactory.getLogger("BasePage.class");
    protected WebDriverWait wait;
    public static WebDriver driver;

    public BasePage() {
        driver = Hooks.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public static String getAttributeValueFromElement(WebElement element, String value) {
        return element.getAttribute(value);
    }

    public static String getTodayDate() {
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), (LocalDate.now().getMonthValue()) - 1, LocalDate.now().getDayOfMonth());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM" + "/yyyy");
        log.info(dateTimeFormatter.format(localDate));
        return dateTimeFormatter.format(localDate);
    }

    @SneakyThrows
    public String getTextFromElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Thread.sleep(1000);
        try {
            return element.getText();
        } catch (StaleElementReferenceException e) {
            return "";
        }
    }

    public void highLightenerMethod(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Hooks.driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: lightred; border: 5px solid red;')", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        highLightenerMethod(element);
        log.info("Clicked: " + element.getText());
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void waitToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitToBeInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitToBeVisibleAllElements(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }


    public void waitForPageLoaded() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
}
