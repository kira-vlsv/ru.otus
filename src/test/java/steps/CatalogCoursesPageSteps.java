package steps;

import static org.assertj.core.api.Assertions.assertThat;

import components.popups.BannerPopup;
import components.popups.CookiePopup;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.inject.Inject;
import org.openqa.selenium.WebElement;
import pages.CatalogCoursesPage;
import java.util.List;

public class CatalogCoursesPageSteps {

    @Inject
    private CatalogCoursesPage catalogCoursesPage;

    @Inject
    private CookiePopup cookiePopup;

    @Inject
    private BannerPopup bannerPopup;

    @Inject
    private ScenarioContext scenarioContext;

    @Given("I am on the catalog courses page")
    public void openCatalogCoursesPage() {
        catalogCoursesPage.open();
        cookiePopup.close();
        bannerPopup.close();
    }

    @When("I click on the course with name {string}")
    public void clickOnCourseWithName(String courseName) {
        catalogCoursesPage.clickCourseByName(courseName);
    }

    @When("I retrieve earliest courses")
    public void retrieveEarliestCourses() {
        List<WebElement> earliestCourses = catalogCoursesPage.getEarliestCourses();
        scenarioContext.setEarliestCourses(earliestCourses);
    }

    @Then("All earliest courses should have valid titles and start dates")
    public void validateEarliestCourses() {
        assertThat(scenarioContext.getEarliestCourses())
                .as("All earliest courses should have valid titles and start dates")
                .allSatisfy(course -> {
                    catalogCoursesPage.checkCourseTitle(course);
                    catalogCoursesPage.checkCourseStartDate(course);
                });
    }

    @When("I retrieve latest courses")
    public void getLatestCourses() {
        List<WebElement> latestCourses = catalogCoursesPage.getLatestCourses();
        scenarioContext.setLatestCourses(latestCourses);
    }


    @Then("All latest courses should have valid titles and start dates")
    public void validateLatestCourses() {
        assertThat(scenarioContext.getLatestCourses())
                .as("All latest courses should have valid titles and start dates")
                .allSatisfy(course -> {
                    catalogCoursesPage.checkCourseTitle(course);
                    catalogCoursesPage.checkCourseStartDate(course);
                });
    }
}