package otus.components.staticcomponent;

import com.google.inject.Inject;
import otus.components.BaseComponent;
import otus.context.ScenarioContext;

public abstract class BaseStaticComponent<T> extends BaseComponent<T> {

    @Inject
    public BaseStaticComponent(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

}
