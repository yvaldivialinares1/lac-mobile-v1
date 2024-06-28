Feature: PLATE_NOT_PROPERLY_READ parking queue issue management.

  This scenario tests the following functionalities:
  - Staff member connect to the parking queue
  - Staff member attend PLATE_NOT_PROPERLY_READ issue when fan has no parking ticket
  - Staff member finish PLATE_NOT_PROPERLY_READ issue
  - Staff member attend PLATE_NOT_PROPERLY_READ issue when fan has valid parking ticket

  @smoke
  Scenario: Fans arrive to parking and licence plate could not be read,
  then employee manually identify and assign plates to accounts, while fans has valid ticket or hasn't.
    Given there is a car with unknown licence plate
    And the following fan accounts exist:
      | type  |
      | ADULT |
    When an user logs in the application using staff credentials and skip game face ID
    And the user selects the "ACCESS" navigation bar option
    And the user connects to the parking queue line
    And there is a "PLATE_NOT_PROPERLY_READ" queue notification for selected parking line
    Then the user sees "PLATE_NOT_PROPERLY_READ" parking queue notification for selected parking line
    When the user manually identifies the licence plate when plate not properly read
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
    And there is a "PLATE_NOT_PROPERLY_READ" queue notification for selected parking line
    And the user manually identifies the licence plate when plate not properly read
    And the user selects a fan account to register the vehicle when plate not properly read
    And the user registers the vehicle for a selected fan account when plate not properly read
    Then the user sees that there is no current issues in the parking queue