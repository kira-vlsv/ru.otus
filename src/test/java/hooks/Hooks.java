package hooks;

import com.google.inject.Inject;
import context.ScenarioContext;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Inject
    private ScenarioContext scenarioContext;

    @After
    public void close() {
        WebDriver driver = scenarioContext.getWebDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}
