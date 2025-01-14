package otus.components;

import otus.annotations.components.Component;
import otus.annotations.components.ComponentTemplate;
import otus.common.BaseCommon;
import otus.context.ScenarioContext;
import otus.exceptions.InvalidPathParametersException;
import com.google.inject.Inject;
import otus.exceptions.InvalidComponentSelectorException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseComponent<T> extends BaseCommon<T> {

    @Inject
    public BaseComponent(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    public By getComponentBy(String... values) {
        ComponentTemplate componentTemplate = getClass().getAnnotation(ComponentTemplate.class);
        if (componentTemplate != null) {
            int expectedParameters = countNumberOfParameters(componentTemplate.value());
            if (values.length != expectedParameters) {
                throw new InvalidPathParametersException(expectedParameters, values.length);
            }
            String formattedSelector = String.format(componentTemplate.value(), (Object[]) values);
            return parseSelector(formattedSelector);
        }

        Component component = getClass().getAnnotation(Component.class);
        if (component != null) {
            return parseSelector(component.value());
        }

        throw new InvalidComponentSelectorException("No valid component annotation found on class: " + getClass().getName());
    }

    private By parseSelector(String selector) {
        Pattern pattern = Pattern.compile("^([^:]+):(.+)$");
        Matcher matcher = pattern.matcher(selector);

        if (matcher.matches()) {
            String type = matcher.group(1);
            String value = matcher.group(2);

            return switch (type) {
                case "css" -> By.cssSelector(value);
                case "xpath" -> By.xpath(value);
                case "id" -> By.id(value);
                case "name" -> By.name(value);
                default -> throw new InvalidComponentSelectorException("Unsupported selector type: " + type);
            };
        } else {
            throw new InvalidComponentSelectorException("Invalid selector format: " + selector);
        }
    }

    public WebElement getComponentEntity(String... values) {
        return driver.findElement(getComponentBy(values));
    }
}
