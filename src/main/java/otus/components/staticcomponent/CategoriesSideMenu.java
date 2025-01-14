package otus.components.staticcomponent;

import static org.assertj.core.api.Assertions.assertThat;

import otus.context.ScenarioContext;
import otus.annotations.components.Component;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

@Component("xpath://p[text() = 'Направление']/ancestor::div[2]")
public class CategoriesSideMenu extends BaseStaticComponent<CategoriesSideMenu> {
    @Inject
    public CategoriesSideMenu(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    public List<WebElement> getCategories() {
        return getComponentEntity().findElements(By.xpath(".//div[@value]"));
    }

    public List<String> getSelectedCategories() {
        return getCategories()
                .stream()
                .filter(e -> Boolean.parseBoolean(e.getAttribute("value")))
                .map(WebElement::getText)
                .toList();
    }

    public void checkSelectedCategories(List<String> expectedCategories) {
        assertThat(getSelectedCategories())
                .as("Categories do not match")
                .isEqualTo(expectedCategories);
    }

}
