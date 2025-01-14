package otus.pages;

import otus.annotations.PathTemplate;
import otus.context.ScenarioContext;
import com.google.inject.Inject;

@PathTemplate("/lessons/%s")
public class CoursePage extends BasePage<CoursePage> {

    @Inject
    public CoursePage(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }
}
