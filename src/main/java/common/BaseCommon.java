package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import wait.ElementWait;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseCommon<T> {

    protected WebDriver driver;
    protected Actions actions;
    protected ElementWait elementWait;
    protected Random random;

    public BaseCommon(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        elementWait = new ElementWait(driver);
        random = new Random();

        PageFactory.initElements(driver, this);
    }

    public int countNumberOfParameters(String template) {
        Pattern pattern = Pattern.compile("%s");
        Matcher matcher = pattern.matcher(template);
        int expectedParameters = 0;
        while (matcher.find()) {
            expectedParameters++;
        }
        return expectedParameters;
    }
}
