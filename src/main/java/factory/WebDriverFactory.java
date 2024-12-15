package factory;

import exceptions.BrowserNotFoundException;
import factory.browsersettings.ChromeSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private static final String BROWSER_NAME = System.getProperty("browser");
    private static final String BROWSER_VERSION = System.getProperty("browserVersion");

    public static WebDriver create() {
        switch (BROWSER_NAME.trim().replaceAll("[^a-zA-Z]", "").toLowerCase()) {
            case "chrome":
                if ("latest".equalsIgnoreCase(BROWSER_VERSION)) {
                    WebDriverManager.chromedriver().setup();
                } else {
                    WebDriverManager.chromedriver().browserVersion(BROWSER_VERSION).setup();
                }
                return new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
            default:
                throw new BrowserNotFoundException(BROWSER_NAME);
        }
    }

}
