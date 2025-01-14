package otus.context;

import io.cucumber.guice.ScenarioScoped;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

@Getter
@Setter
@ScenarioScoped
public class ScenarioContext {

    private WebDriver driver;
    private List<WebElement> earliestCourses;
    private List<WebElement> latestCourses;
    private String categoryName;
}
