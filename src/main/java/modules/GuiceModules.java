package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.popups.HeaderSubMenuPopup;
import components.staticcomponent.CategoriesSideMenu;
import components.staticcomponent.HeaderMenu;
import context.ScenarioContext;
import pages.CatalogCoursesPage;
import pages.CoursePage;
import pages.MainPage;

public class GuiceModules extends AbstractModule {

    private ScenarioContext scenarioContext = new ScenarioContext();

    @Singleton
    @Provides
    public ScenarioContext getScenarioContext() {
        return this.scenarioContext;
    }

    @Singleton
    @Provides
    public MainPage getMainPage() {
        return new MainPage(scenarioContext);
    }

    @Singleton
    @Provides
    public CoursePage getCoursePage() {
        return new CoursePage(scenarioContext);
    }

    @Singleton
    @Provides
    public CatalogCoursesPage getCatalogCoursesPage() {
        return new CatalogCoursesPage(scenarioContext);
    }

    @Singleton
    @Provides
    public HeaderMenu getHeaderMenu() {
        return new HeaderMenu(scenarioContext);
    }

    @Singleton
    @Provides
    public HeaderSubMenuPopup getHeaderSubMenuPopup() {
        return new HeaderSubMenuPopup(scenarioContext);
    }

    @Singleton
    @Provides
    public CategoriesSideMenu getCategoriesSideMenu() {
        return new CategoriesSideMenu(scenarioContext);
    }
}
