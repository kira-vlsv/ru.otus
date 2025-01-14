package otus.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import otus.pages.MainPage;
import otus.components.popups.HeaderSubMenuPopup;
import otus.components.staticcomponent.CategoriesSideMenu;
import otus.components.staticcomponent.HeaderMenu;
import otus.context.ScenarioContext;
import otus.pages.CatalogCoursesPage;
import otus.pages.CoursePage;

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
