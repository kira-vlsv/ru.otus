package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.popups.HeaderSubMenuPopup;
import components.staticcomponent.CategoriesSideMenu;
import components.staticcomponent.HeaderMenu;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import pages.CatalogCoursesPage;
import pages.CoursePage;
import pages.MainPage;

public class GuiceModules extends AbstractModule {

    private WebDriver driver = WebDriverFactory.create();

    @Provides
    public WebDriver getWebDriver() {
        return driver;
    }

    @Singleton
    @Provides
    public MainPage getMainPage() {
        return new MainPage(driver);
    }

    @Singleton
    @Provides
    public CoursePage getCoursePage() {
        return new CoursePage(driver);
    }

    @Singleton
    @Provides
    public CatalogCoursesPage getCatalogCoursesPage() {
        return new CatalogCoursesPage(driver);
    }

    @Singleton
    @Provides
    public HeaderMenu getHeaderMenu() {
        return new HeaderMenu(driver);
    }

    @Singleton
    @Provides
    public HeaderSubMenuPopup getHeaderSubMenuPopup() {
        return new HeaderSubMenuPopup(driver);
    }

    @Singleton
    @Provides
    public CategoriesSideMenu getCategoriesSideMenu() {
        return new CategoriesSideMenu(driver);
    }
}
