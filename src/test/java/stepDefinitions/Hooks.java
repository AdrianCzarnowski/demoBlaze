package stepDefinitions;

import configuration.factory.DriverFactory;
import configuration.properties.App;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.System.out;


public class Hooks {
    public static WebDriver driver;
    private static App app;
    private static DriverFactory driverFactory = new DriverFactory();

    @Before
    public void setUp(Scenario scenario) {
        app = new App();
        driver = driverFactory.getDriver(app.setBrowserFromYaml());
    }

    @After
    public void tearDown(Scenario scenario) {
        out.println(scenario.getName() + " " + scenario.getStatus());
        driver.quit();
    }
}


