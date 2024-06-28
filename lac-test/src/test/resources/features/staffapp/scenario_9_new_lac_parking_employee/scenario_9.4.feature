Feature: UNKNOWN_PLATE parking queue issue management.

  This scenario tests the following functionalities:
  - Staff member connect to the parking queue
  - Staff member attend UNKNOWN_PLATE issue when fan has no parking ticket
  - Staff member finish UNKNOWN_PLATE issue
  - Staff member attend UNKNOWN_PLATE issue when fan has valid parking ticket

  @smoke
  Scenario: Fans arrive without an account and no ticket, then employee finish issue or register the fan's plate
    Given there is a car with unknown licence plate
    And the following fan accounts exist:
      | type  |
      | ADULT |
    When an user logs in the application using staff credentials and skip game face ID
    And the user selects the "ACCESS" navigation bar option
    And the user connects to the parking queue line
    And there is a "UNKNOWN_PLATE" queue notification for selected parking line
    Then the user sees "UNKNOWN_PLATE" parking queue notification for selected parking line
    When the user taps issue card primary button
    And the user selects a fan account to register the vehicle when plate not properly read
    And the user registers the vehicle for a selected fan account when plate not properly read
    Then the user sees the vehicle added to account but fan has no parking ticket
    When the user selects finish issue from attend issue screen
    And the user completes form to finish "NO_PARKING_TICKET" issue
    Then the user sees the issue was finished and removed from the queue message
    When the following fan accounts exist:
      | type  |
      | ADULT |
    And there is a car with unknown licence plate
    And the fan account has a valid parking ticket
    And there is a "UNKNOWN_PLATE" queue notification for selected parking line
    And the user taps issue card primary button
    And the user selects a fan account to register the vehicle when plate not properly read
    And the user registers the vehicle for a selected fan account when plate not properly read
    Then the user sees that there is no current issues in the parking queue