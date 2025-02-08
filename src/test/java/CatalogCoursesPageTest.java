import annotations.UITest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.CatalogCoursesPage;
import pages.CoursePage;
import java.io.IOException;

@UITest
@Feature("Catalog course page")
class CatalogCoursesPageTest {

    @Inject
    private CatalogCoursesPage catalogCoursesPage;

    @Inject
    private CoursePage coursePage;

    @Test
    @AllureId("1")
    @DisplayName("Find course by name and open it")
    void testSearchAndOpenCoursePage() {
        var courseName = catalogCoursesPage.open().getCourseNameByIndex(1);
        catalogCoursesPage.clickCourseByName(courseName);
        coursePage.pageTitleShouldBeAs(courseName);
    }

    @Test
    @AllureId("2")
    @DisplayName("Find earliest and latest courses. Check their titles and start dates")
    void testFindAndVerifyEarliestAndLatestCourses() throws IOException {
        catalogCoursesPage.open();
        var earliestCourses = catalogCoursesPage.getEarliestCourses();
        var latestCourses = catalogCoursesPage.getLatestCourses();
        for (WebElement courseItem : earliestCourses) {
            catalogCoursesPage.checkCourseTitle(courseItem);
            catalogCoursesPage.checkCourseStartDate(courseItem);
        }
        for (WebElement courseItem : latestCourses) {
            catalogCoursesPage.checkCourseTitle(courseItem);
            catalogCoursesPage.checkCourseStartDate(courseItem);
        }
    }
}
