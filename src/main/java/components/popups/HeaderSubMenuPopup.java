package components.popups;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.components.ComponentTemplate;
import com.google.inject.Inject;
import components.BaseComponent;
import context.ScenarioContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

@ComponentTemplate("xpath://span[contains(text(), '%s')]/ancestor::div/following-sibling::div[1]")
public class HeaderSubMenuPopup extends BaseComponent<HeaderSubMenuPopup> implements IPopup<HeaderSubMenuPopup> {

    @Inject
    public HeaderSubMenuPopup(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    @FindBy(xpath = "//nav//a[contains(@href, '/categories')]")
    private List<WebElement> categoriesMenuItems;

    @Override
    public HeaderSubMenuPopup popupShouldBeVisible(String... values) {
        assertThat(elementWait.waitForVisibility(getComponentEntity(values)))
                .as("Element should be visible")
                .isTrue();
        return this;
    }

    @Override
    public void popupShouldNotBeVisible(String... values) {
        assertThat(elementWait.waitForInvisibility(getComponentBy(values)))
                .as("Element should not be visible")
                .isTrue();
    }

    private WebElement getRandomCategoryItem() {
        return categoriesMenuItems.get(random.nextInt(categoriesMenuItems.size()));
    }

    public String clickOnRandomCategory() {
        WebElement category = getRandomCategoryItem();
        String categoryName = category.getText().split(" \\(")[0];
        category.click();
        return categoryName;
    }
}
