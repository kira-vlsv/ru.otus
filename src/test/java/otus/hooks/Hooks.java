package otus.hooks;

import com.google.inject.Inject;
import otus.context.ScenarioContext;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Inject
    private ScenarioContext scenarioContext;

    @After
    public void close() {
        WebDriver driver = scenarioContext.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}
