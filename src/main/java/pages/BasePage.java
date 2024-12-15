package pages;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.Path;
import annotations.PathTemplate;
import common.BaseCommon;
import exceptions.InvalidPathException;
import exceptions.InvalidPathParametersException;
import exceptions.MissingPathTemplateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BasePage<T> extends BaseCommon<T> {

    private final String baseUrl = System.getProperty("baseUrl").replaceAll("/$", "");

    public BasePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1")
    public WebElement header;

    public T open() {
        driver.get(baseUrl + getPath());
        return (T) this;
    }

    public T open(String... path) {
        driver.get(baseUrl + getPath(path));
        return (T) this;
    }

    private String getPath() {
        Path path = getClass().getAnnotation(Path.class);
        if (path == null) {
            throw new InvalidPathException();
        }
        return path.value().replaceAll("^(?!/)", "/");
    }

    private String getPath(String... path) {
        PathTemplate pathTemplate = getClass().getAnnotation(PathTemplate.class);
        if (pathTemplate == null) {
            throw new MissingPathTemplateException();
        }
        String template = pathTemplate.value();
        int expectedParameters = countNumberOfParameters(template);
        if (path.length != expectedParameters) {
            throw new InvalidPathParametersException(expectedParameters, path.length);
        }
        return String.format(template, (Object[]) path);
    }

    public T pageTitleShouldBeAs(String title) {
        assertThat(header.getText())
                .as(String.format("Page title should be %s", title))
                .isEqualTo(title);
        return (T) this;
    }
}
