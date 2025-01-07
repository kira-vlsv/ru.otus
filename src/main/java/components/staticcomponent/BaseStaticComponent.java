package components.staticcomponent;

import com.google.inject.Inject;
import components.BaseComponent;
import context.ScenarioContext;

public abstract class BaseStaticComponent<T> extends BaseComponent<T> {

    @Inject
    public BaseStaticComponent(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

}
