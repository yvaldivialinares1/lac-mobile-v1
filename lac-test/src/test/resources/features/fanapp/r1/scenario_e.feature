@missions @missions_r1
Feature: Missions R1 - Scenario E

  @adult_user
  Scenario: Adult fan creates an account skipping all steps, buys tickets adding a new card, logs out and in, and then completes GameFace from the reminder
    Given An adult who has personal information generated
    And The user doesn’t have a Clipper account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the game face registration
    And the user skip the identity pass registration
    And the user completes the payment method registration
    And the user completes the registration process and is on the home screen
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the game to buy 1 seat without accessibility
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user uses an existing payment method
    And completes the ticket purchase process
    And the user navigates to my payments
    And the user select payment method management from menu
    And the user adds 1 valid payment methods
    And the user sets or edit a nickname for their existing payments method
    And the user returns to the Profile screen from My Payment Methods Management
    And the user closes the section in the app
    And the user goes to Login screen from Welcome Home screen
    And user enters credentials to log in
#    And the user checks the information displayed on the kbyg screen
    Then the user is able to see the ticket that was purchased before