package components.staticcomponent;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

@Component("xpath://p[text() = 'Направление']/ancestor::div[2]")
public class CategoriesSideMenu extends BaseStaticComponent<CategoriesSideMenu> {
    public CategoriesSideMenu(WebDriver driver) {
        super(driver);
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
