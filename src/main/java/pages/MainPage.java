package pages;

import annotations.Path;
import com.google.inject.Inject;
import context.ScenarioContext;

@Path("/")
public class MainPage extends BasePage<MainPage> {

    @Inject
    public MainPage(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }
}
