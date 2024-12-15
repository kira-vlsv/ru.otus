package components.staticcomponent;

import components.BaseComponent;
import org.openqa.selenium.WebDriver;

public abstract class BaseStaticComponent<T> extends BaseComponent<T> {
    public BaseStaticComponent(WebDriver driver) {
        super(driver);
    }

}
