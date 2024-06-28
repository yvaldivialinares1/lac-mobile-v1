Feature: Staff Incidents

  This scenario tests the following functionalities:
  - Staff member is logged into the app
  - Notice an emergency incident into the venue
  - Open the emergency incident module
  - User choose the incident
  - Fill the mandatory and optional fields
  - Create the incident
  - A notification is send to all Staff

  Scenario Outline: LAC employee has to report an incident into the venue
    Given an user logs in the application using staff credentials and skip game face ID
    And notice an emergency incident into the venue
    When the user taps on the emergency incident icon
    Then the user should see the emergency incident module opened
    When the user chooses the type of incident "<incident type>"
    Then the user sees a form with prefilled data
    When the user completes the mandatory fields
    Then the create incident button is enabled
    When the user taps on create incident button
    Then the user sees the expected banner message "SUCCESS_INCIDENT_CREATION" displayed

    Examples:
      | incident type |
      | Evacuation    |