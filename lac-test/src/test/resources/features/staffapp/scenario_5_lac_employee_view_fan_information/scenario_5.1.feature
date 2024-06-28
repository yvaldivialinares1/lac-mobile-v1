Feature: Fan access information management

  This scenario tests the following functionalities:
  - Staff member is logged into the app
  - Staff member search the fan by email
  - Staff member view and edit fan identity section
  - Staff member view and edit fan vehicles section
  - Staff member view and edit fan clipper band section

  @smoke @this
  Scenario: View and edit fan access information for different types of accounts(ADULT, MINOR_ADULT)

    Given the following fan accounts exist:
      | type       | identity_completed | registered_vehicles | wearable_linked | game_face_id |
      | ADULT      | true               | 3                   | true            | false        |
      | MINOR_ADULT| false              | 3                   | true            | true         |
    When an user logs in the application using staff credentials and skip game face ID
    And the user searches and finds the fan "MINOR_ADULT" account from the home screen
    Then the user views the fan "FAN_IDENTITY" identity section
    And the user should see fan identity information of the "MINOR_ADULT" account type properly displayed
    When the user stops viewing fan identity section
    And the user searches and finds the fan "ADULT" account from the header section
    Then the user views the fan "FAN_IDENTITY" identity section
    And the user should see fan identity information of the "ADULT" account type properly displayed
    When the user removes age verification note from a fan information screen
    Then the user sees the expected banner message "SUCCESS_AGE_VERIFICATION_REMOVAL" displayed
    And the user views the fan "FAN_IDENTITY" identity section
    When the user performs manual age verification till the end of the day
    Then the user should see the fan profile vehicles section of the "ADULT" account type properly displayed
    When the user adds a new vehicle to the "ADULT" account
    Then the user sees the expected banner message "SUCCESS_VEHICLE_ADDED" displayed
    When the user edits the fan "ADULT" vehicle details
    Then the user sees the expected banner message "SUCCESS_CHANGES_MADE" displayed
    When the user removes fan "ADULT" vehicle note
    Then the user sees the expected banner message "SUCCESS_VEHICLE_WAS_REMOVED" displayed
    And the user should see the fan profile vehicles list of the "ADULT" account type properly displayed
    When the user taps the button to close the fan vehicle list
    And the user views the fan "CLIPPER_BAND" identity section
    Then the user should see the correct clipper band id of "ADULT" account type displayed
    When the user unlinks the fan clipper band
    Then the user sees the expected banner message "SUCCESS_CLIPPER_BAND_UNLINKED" displayed
    And the user sees there is no clipper band linked to fan account