import static constants.HeaderMenuData.STUDY;

import annotations.UITest;
import components.popups.HeaderSubMenuPopup;
import components.staticcomponent.CategoriesSideMenu;
import components.staticcomponent.HeaderMenu;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import java.util.List;

@UITest
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
    public void navigateToRandomCourseFromHeaderMenu() {
        mainPage.open();
        headerMenu.clickOnMenuItem(STUDY);
        var categoryName = headerSubMenuPopup
                .popupShouldBeVisible(STUDY.getDisplayName())
                .clickOnRandomCategory();
        categoriesSideMenu.checkSelectedCategories(List.of(categoryName));
    }

}
