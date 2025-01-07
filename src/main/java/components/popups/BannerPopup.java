package components.popups;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.components.ComponentTemplate;
import com.google.inject.Inject;
import components.BaseComponent;
import context.ScenarioContext;
import org.openqa.selenium.By;

@ComponentTemplate("xpath://div[@class = 'sticky-banner js-sticky-banner']")
public class BannerPopup extends BaseComponent<BannerPopup> implements IPopup<BannerPopup> {

    @Inject
    public BannerPopup(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    public void close() {
        driver.findElement(By.xpath("//div[@class = 'sticky-banner js-sticky-banner']//div[contains(@class, 'sticky-banner__close')]")).click();
        popupShouldNotBeVisible();
    }

    @Override
    public BannerPopup popupShouldBeVisible(String... values) {
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
