package pages;

import annotations.PathTemplate;
import org.openqa.selenium.WebDriver;

@PathTemplate("/lessons/%s")
public class CoursePage extends BasePage<CoursePage> {

    public CoursePage(WebDriver driver) {
        super(driver);
    }
}
