package wait;

import exceptions.ElementVisibilityException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ElementWait {

    private WebDriver driver;

    public ElementWait(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitForVisibility(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            throw new ElementVisibilityException("Element was not visible within the specified timeout.");
        }
    }

    public boolean waitForInvisibility(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            throw new ElementVisibilityException("Element is still visible within the specified timeout.");
        }
    }

}
