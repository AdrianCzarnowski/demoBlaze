package pages.menu;

import pages.helpers.BasePage;
import stepDefinitions.Hooks;

public class LoginPage extends BasePage {

    SingUpPage singUpPage = new SingUpPage();

    public LoginPage() {
        driver = Hooks.driver;
    }

    public LoginPage fillForm(String name, String password, String buttonValue, String userName, String userPassword) {
        singUpPage
                .fillForm(name, password, buttonValue, userName, userPassword);
        return this;
    }
}
