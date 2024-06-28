Feature: INCORRECT_GARAGE parking queue issue management.

  This scenario tests the following functionalities:
  - Staff member connect to the parking queue
  - Staff member attend INCORRECT_GARAGE issue

  @smoke
  Scenario: Fan arrives to the wrong parking employee receives this message
    Given a fan with a 1 registered vehicle data
    When an user logs in the application using staff credentials and skip game face ID
    And the user selects the "ACCESS" navigation bar option
    And the user connects to the parking queue line
    And there is a "INCORRECT_GARAGE" queue notification for selected parking line
    Then the user sees "INCORRECT_GARAGE" parking queue notification for selected parking line