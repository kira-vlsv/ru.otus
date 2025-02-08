import static constants.HeaderMenuData.STUDY;

import annotations.UITest;
import components.popups.HeaderSubMenuPopup;
import components.staticcomponent.CategoriesSideMenu;
import components.staticcomponent.HeaderMenu;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import java.util.List;

@UITest
@Feature("Main page")
public class MainPageTest {

    @Inject
    private MainPage mainPage;

    @Inject
    private HeaderMenu headerMenu;

    @Inject
    private HeaderSubMenuPopup headerSubMenuPopup;

    @Inject
    private CategoriesSideMenu categoriesSideMenu;

    @Test
    @AllureId("3")
    @DisplayName("Navigate to a random category from header menu")
    public void navigateToRandomCategoryFromHeaderMenu() {
        mainPage.open();
        headerMenu.clickOnMenuItem(STUDY);
        var categoryName = headerSubMenuPopup
                .popupShouldBeVisible(STUDY.getDisplayName())
                .clickOnRandomCategory();
        categoriesSideMenu.checkSelectedCategories(List.of(categoryName));
    }

}
