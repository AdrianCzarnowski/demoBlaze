package pages.menu;


import configuration.model.User;
import lombok.SneakyThrows;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.helpers.BasePage;
import stepDefinitions.Hooks;

public class SingUpPage extends BasePage {

    public SingUpPage() {
        driver = Hooks.driver;
    }

    private static Logger log = LoggerFactory.getLogger("SingUpPage.class");

    public String generateRandomName(User user) {
        String name = user.getName();
        return name;
    }

    public String generateRandomPassword(User user) {
        String password = user.getPassword();
        return password;
    }

    public SingUpPage fillForm(String name, String password, String buttonValue, String userName, String userPassword) {
        waitToBeVisible(driver.findElement(By.id(userName)));
        sendKeys(driver.findElement(By.id(userName)), name);
        sendKeys(driver.findElement(By.id(userPassword)), password);
        clickOnElement(driver.findElement(By.cssSelector("[onclick='" + buttonValue + "']")));
        log.info("User: " + name);
        return this;
    }

    @SneakyThrows
    public SingUpPage closeAlert() {
        Thread.sleep(1500);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }
}
