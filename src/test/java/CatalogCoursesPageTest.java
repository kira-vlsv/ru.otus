import annotations.UITest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.CatalogCoursesPage;
import pages.CoursePage;
import java.io.IOException;

@UITest
class CatalogCoursesPageTest {

    @Inject
    private CatalogCoursesPage catalogCoursesPage;

    @Inject
    private CoursePage coursePage;

    @Test
    void testSearchAndOpenCoursePage() {
        var courseName = catalogCoursesPage.open().getCourseNameByIndex(1);
        catalogCoursesPage.clickCourseByName(courseName);
        coursePage.pageTitleShouldBeAs(courseName);
    }

    @Test
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
