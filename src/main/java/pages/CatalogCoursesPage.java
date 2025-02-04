package pages;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.Path;
import io.qameta.allure.Step;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/catalog/courses")
public class CatalogCoursesPage extends BasePage<CatalogCoursesPage> {
    public CatalogCoursesPage(WebDriver driver) {
        super(driver);
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", Locale.forLanguageTag("ru"));

    @FindBy(css = "section:has(h1) a[href*='lessons']")
    private List<WebElement> courseItems;

    public void clickCourseByName(String name) {
        actions.moveToElement(getCourseByName(name)).click().perform();
    }

    public String getCourseNameByIndex(int index) {
        return courseItems.get(index).findElement(By.xpath(".//h6")).getText();
    }

    public WebElement getCourseByName(String name) {
        return courseItems.stream()
                .filter(lesson -> lesson.findElement(By.xpath(".//h6")).getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Course with name '" + name + "' was not found"));
    }

    @Step("Get earliest courses")
    public List<WebElement> getEarliestCourses() {
        return getCoursesByExtremeDate(true);
    }

    @Step("Get latest courses")
    public List<WebElement> getLatestCourses() {
        return getCoursesByExtremeDate(false);
    }

    private List<WebElement> getCoursesByExtremeDate(boolean isEarliest) {
        return courseItems.stream()
                .collect(Collectors.groupingBy(this::getCourseStartDate))
                .entrySet().stream()
                .reduce((entry1, entry2) -> isEarliest
                        ? (entry1.getKey().isBefore(entry2.getKey()) ? entry1 : entry2)
                        : (entry1.getKey().isAfter(entry2.getKey()) ? entry1 : entry2))
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new RuntimeException("No courses found"));
    }

    public Document getPageDocument(WebElement courseItem) throws IOException {
        return Jsoup.connect(getCourseUrl(courseItem)).get();
    }

    public String getCourseUrl(WebElement courseItem) {
        return courseItem.getAttribute("href");
    }

    public String getCourseTitle(WebElement courseElement) {
        return courseElement.findElement(By.xpath(".//h6")).getText();
    }

    public LocalDate getCourseStartDate(WebElement courseItem) {
        WebElement element = courseItem.findElement(By.xpath(".//div[contains(text(), ',')]"));
        String dateText = element.getText().split(" · ")[0].trim();
        return LocalDate.parse(dateText, formatter);
    }

    @Step("Verify course title")
    public void checkCourseTitle(WebElement courseItem) throws IOException {
        Document pageDocument = getPageDocument(courseItem);
        assertThat(pageDocument.select("h1").text())
                .as("Course page title should be same as course tile title")
                .isEqualTo(getCourseTitle(courseItem));
    }

    @Step("Verify course start date")
    public void checkCourseStartDate(WebElement courseItem) throws IOException {
        Document pageDocument = getPageDocument(courseItem);
        String dateText = pageDocument.selectXpath("(//p[contains(text(), 'месяц')]/ancestor::div[1]/preceding-sibling::div//p)[1]").text();
        assertThat(parseDate(dateText))
                .as("Course page start date should be same as course tile start date")
                .isEqualTo(getCourseStartDate(courseItem));
    }

    private static LocalDate parseDate(String input) {
        DateTimeFormatter formatterWithYear = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.forLanguageTag("ru"));

        try {
            LocalDate date = LocalDate.parse(input + " " + LocalDate.now().getYear(), formatterWithYear);
            return Optional.of(date).get();
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(input, formatterWithYear);
                return Optional.of(date).get();
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Invalid date format: " + input, ex);
            }
        }
    }
}
