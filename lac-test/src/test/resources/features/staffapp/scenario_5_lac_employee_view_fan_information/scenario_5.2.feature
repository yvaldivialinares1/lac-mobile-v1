Feature: Fan access information management

  This scenario tests the following functionalities:
  - Staff member is logged into the app
  - Staff member search the fan by email
  - Staff member view fan basic information
  - Staff member view and edit fan teammates section

  @smoke
  Scenario: View fan basic information for different types of ADULT account, view and edit linked teammates.
    Given the following fan accounts exist:
      | type        | teammates_linked | teammates_wearable_linked |
      | ADULT       | 3                | true                      |
      | MINOR_ADULT | 0                | false                     |
    When an user logs in the application using staff credentials and skip game face ID
    And the user searches and finds the fan "MINOR_ADULT" account from the home screen
    Then the user views the fan basic information and teammates section for "MINOR_ADULT"
    And the user searches and finds the fan "ADULT" account from the header section
    Then the user views the fan basic information and teammates section for "ADULT"
    When the user views fan teammates accounts list
    Then the user sees the teammates list of the "ADULT" account type properly displayed
    When the user selects the random teammate note from "ADULT" account teammates list
    Then the user sees the teammate information properly displayed
    When the user unlinks the fan clipper band
    Then the user sees the expected banner message "SUCCESS_CLIPPER_BAND_UNLINKED" displayed
    And the user sees there is no clipper band linked to fan account