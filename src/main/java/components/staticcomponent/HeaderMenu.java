package components.staticcomponent;

import annotations.components.Component;
import constants.HeaderMenuData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Component("css:div > nav")
public class HeaderMenu extends BaseStaticComponent<HeaderMenu> {

    private static final String MENU_ITEM_SELECTOR_TEMPLATE = ".//span[contains(text(), '%s')]";

    public HeaderMenu(WebDriver driver) {
        super(driver);
    }

    @Step("Click on menu item")
    public void clickOnMenuItem(HeaderMenuData menuItemData) {
        String selector = String.format(MENU_ITEM_SELECTOR_TEMPLATE, menuItemData.getDisplayName());
        WebElement menuItem = driver.findElement(By.xpath(selector));
        menuItem.click();
    }
}
