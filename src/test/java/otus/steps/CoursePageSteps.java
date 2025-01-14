package otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import otus.pages.CoursePage;

public class CoursePageSteps {

    @Inject
    private CoursePage coursePage;

    @Then("The course page title should match course name {string}")
    public void theCoursePageTitleShouldMatchTheCourseName(String expectedName) {
        coursePage.pageTitleShouldBeAs(expectedName);
    }
}
