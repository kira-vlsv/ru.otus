package factory.browsersettings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeSettings implements IBrowserSettings {

    private final String browserSize = System.getProperty("browserSize");

    @Override
    public AbstractDriverOptions settings() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments(String.format("window-size=%s", browserSize));
        return options;
    }
}
