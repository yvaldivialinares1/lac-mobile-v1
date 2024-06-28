Feature: NO_PARKING_TICKET parking queue issue management.

  This scenario tests the following functionalities:
  - Staff member connect to the parking queue
  - Staff member attend NO_PARKING_TICKET issue
  - Staff member finish NO_PARKING_TICKET issue

  @smoke
  Scenario: Fan arrives without ticket receives message in the queue, then employee finish issue.
    Given a fan with a 1 registered vehicle data
    When an user logs in the application using staff credentials and skip game face ID
    And the user selects the "ACCESS" navigation bar option
    And the user connects to the parking queue line
    And there is a "NO_PARKING_TICKET" queue notification for selected parking line
    Then the user sees "NO_PARKING_TICKET" parking queue notification for selected parking line
    When the user taps issue card primary button
    Then the user sees attend NO PARKING TICKET issue screen is properly displayed
    When the user leaves issue from attend issue screen
    Then the user sees "NO_PARKING_TICKET" parking queue notification for selected parking line
    When the user taps issue card primary button
    And the user selects finish issue from attend issue screen
    And the user completes form to finish "NO_PARKING_TICKET" issue
    Then the user sees the issue was finished and removed from the queue message