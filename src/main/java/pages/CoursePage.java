package pages;

import annotations.PathTemplate;
import com.google.inject.Inject;
import context.ScenarioContext;

@PathTemplate("/lessons/%s")
public class CoursePage extends BasePage<CoursePage> {

    @Inject
    public CoursePage(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }
}
