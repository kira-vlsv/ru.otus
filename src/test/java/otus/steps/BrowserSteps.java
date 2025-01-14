package otus.steps;

import io.cucumber.java.en.Given;
import jakarta.inject.Inject;
import org.openqa.selenium.WebDriver;
import otus.context.ScenarioContext;
import otus.factory.WebDriverFactory;

public class BrowserSteps {

    @Inject
    private ScenarioContext scenarioContext;

    @Given("I open browser {string}")
    public void openBrowser(String browserName) {
        System.setProperty("browser", browserName);
        WebDriver driver = WebDriverFactory.create();
        scenarioContext.setDriver(driver);
    }
}
