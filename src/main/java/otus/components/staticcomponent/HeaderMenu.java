package otus.components.staticcomponent;

import otus.annotations.components.Component;
import otus.context.ScenarioContext;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Component("css:div > nav")
public class HeaderMenu extends BaseStaticComponent<HeaderMenu> {

    private static final String MENU_ITEM_SELECTOR_TEMPLATE = ".//span[contains(text(), '%s')]";

    @Inject
    public HeaderMenu(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    public void clickOnMenuItem(String menuItemData) {
        String selector = String.format(MENU_ITEM_SELECTOR_TEMPLATE, menuItemData);
        WebElement menuItem = driver.findElement(By.xpath(selector));
        menuItem.click();
    }
}