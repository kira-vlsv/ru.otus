package otus.components.popups;

import static org.assertj.core.api.Assertions.assertThat;

import otus.annotations.components.ComponentTemplate;
import com.google.inject.Inject;
import otus.components.BaseComponent;
import otus.context.ScenarioContext;
import org.openqa.selenium.By;

@ComponentTemplate("xpath://a[@href='/legal/cookie']/../..")
public class CookiePopup extends BaseComponent<CookiePopup> implements IPopup<CookiePopup> {

    @Inject
    public CookiePopup(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    public void close() {
        driver.findElement(By.xpath("//a[@href='/legal/cookie']/../..//button")).click();
        popupShouldNotBeVisible();
    }

    @Override
    public CookiePopup popupShouldBeVisible(String... values) {
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
}
