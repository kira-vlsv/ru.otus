package steps;

import components.popups.HeaderSubMenuPopup;
import components.staticcomponent.CategoriesSideMenu;
import components.staticcomponent.HeaderMenu;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.inject.Inject;
import pages.MainPage;
import java.util.List;

public class MainPageSteps {

    @Inject
    private MainPage mainPage;

    @Inject
    private HeaderMenu headerMenu;

    @Inject
    private HeaderSubMenuPopup headerSubMenuPopup;

    @Inject
    private CategoriesSideMenu categoriesSideMenu;

    @Inject
    private ScenarioContext scenarioContext;

    @Given("I am on the main page")
    public void openMainPage() {
        mainPage.open();
    }

    @When("I click on the {string} menu item in the header")
    public void clickOnMenuItemInHeader(String menuItem) {
        headerMenu.clickOnMenuItem(menuItem);
    }

    @When("I select a random category from the {string} submenu")
    public void selectRandomCategoryFromTheSubmenu(String headerMenuCategory) {
        scenarioContext.setCategoryName(headerSubMenuPopup
                .popupShouldBeVisible(headerMenuCategory)
                .clickOnRandomCategory());
    }

    @Then("Selected category should be displayed in the side menu")
    public void theSelectedCategoryShouldBeDisplayedInTheSideMenu() {
        categoriesSideMenu.checkSelectedCategories(List.of(scenarioContext.getCategoryName()));
    }
}