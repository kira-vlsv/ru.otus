Feature: Main page navigation

  @main_page
  Scenario: User navigates to a random course category from the header menu
    Given I am on the main page
    When I click on the "Обучение" menu item in the header
    And I select a random category from the "Обучение" submenu
    Then Selected category should be displayed in the side menu