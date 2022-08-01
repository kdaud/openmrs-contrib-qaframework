Feature: Location Management

  @selenium
  @location
  Scenario: Adding and deleting location
    Given a user clicks on configure metadata app
    Then the system saves the new location
    And the user clicks on delete location button
    Then the system deletes the location

  @selenium
  @location
  Scenario: Retiring a location
    When a user clicks on configure metadata app
    Then the system loads the manage location page
    And the user clicks on retire location button
    Then the system retires the location
