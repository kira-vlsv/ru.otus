Feature: Courses catalog Page validation

  @main_page
  Scenario: User searches and opens a course page by name
    Given I open browser "Chrome"
    Given I am on the catalog courses page
    When I click on the course with name "Разработчик на Spring Framework"
    Then The course page title should match course name "Разработчик на Spring Framework"

  @main_page
  Scenario: Verify that earliest and latest courses have valid data
    Given I open browser "Chrome"
    Given I am on the catalog courses page
    When I retrieve earliest courses
    Then All earliest courses should have valid titles and start dates
    When I retrieve latest courses
    Then All latest courses should have valid titles and start dates