package otus.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import otus.modules.GuiceModules;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

    private Injector injector;

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        extensionContext.getTestInstance()
                .ifPresent(instance -> {
                            injector = Guice.createInjector(new GuiceModules());
                            injector.injectMembers(instance);
                        }
                );
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        WebDriver driver = injector.getProvider(WebDriver.class).get();
        if (driver != null) {
            driver.quit();
        }
    }
}
