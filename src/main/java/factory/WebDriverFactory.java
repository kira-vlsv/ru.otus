package factory;

import exceptions.BrowserNotFoundException;
import factory.browsersettings.ChromeSettings;
import factory.browsersettings.IBrowserSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    private static final String BROWSER_NAME = System.getProperty("browser").trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
    private static final String BROWSER_VERSION = System.getProperty("browserVersion");
    private static final String REMOTE_URL = System.getProperty("remoteUrl");

    public static WebDriver create() {
        switch (BROWSER_NAME) {
            case "chrome":
                if (Boolean.parseBoolean(System.getProperty("isRemoteWebDriver"))) {
                    return createRemoteWebDriver(new ChromeSettings());
                } else {
                    if ("latest".equalsIgnoreCase(BROWSER_VERSION)) {
                        WebDriverManager.chromedriver().setup();
                    } else {
                        WebDriverManager.chromedriver().browserVersion(BROWSER_VERSION).setup();
                    }
                    return new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
                }
            default:
                throw new BrowserNotFoundException(BROWSER_NAME);
        }
    }

    private static WebDriver createRemoteWebDriver(IBrowserSettings browserSettings) {
        AbstractDriverOptions options = browserSettings.settings();

        boolean isMobile = Boolean.parseBoolean(System.getProperty("isMobile"));

        if (isMobile) {
            configureMobileEmulation(browserSettings, options);
        }

        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", false);

        Map<String, Object> capabilities = new HashMap<>();
        capabilities.put("browserName", BROWSER_NAME);
        capabilities.put("browserVersion", BROWSER_VERSION != null ? BROWSER_VERSION : "128.0");
        capabilities.put("selenoid:options", selenoidOptions);
        capabilities.putAll(options.asMap());
        try {
            return new RemoteWebDriver(new URL(REMOTE_URL), new ImmutableCapabilities(capabilities));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenoid URL: " + REMOTE_URL, e);
        }
    }

    private static void configureMobileEmulation(IBrowserSettings browserSettings, AbstractDriverOptions<?> options) {
        if (browserSettings instanceof ChromeSettings) {
            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "Pixel 4");
            ((ChromeOptions) options).setExperimentalOption("mobileEmulation", mobileEmulation);
        } else {
            throw new UnsupportedOperationException("Mobile emulation is not supported for the selected browser");
        }
    }
}
