package otus.pages;

import otus.annotations.Path;
import com.google.inject.Inject;
import otus.context.ScenarioContext;

@Path("/")
public class MainPage extends BasePage<MainPage> {

    @Inject
    public MainPage(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }
}
