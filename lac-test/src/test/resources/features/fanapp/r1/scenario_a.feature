@missions @missions_r1
Feature: Missions R1 - Scenario A

  @adult_user
  Scenario: Fan older than 21 creates an account with incomplete profile and buy tickets with a valid payments method
    Given An adult who has personal information generated
    And The user doesn’t have a Clipper account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the game face registration
    And the user skip the identity pass registration
    And the user skip the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my payments
    And the user select payment method management from menu
    And the user adds 1 valid payment methods
    Then the user validates the list of the payment cards in my profile
    When the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the game to buy 2 seat without accessibility
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user uses an existing payment method
    And completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen
#    Then the user is able to see the ticket that was purchased before -> This step needs to be validated from profile - My Tickets screen
#    And the user checks the information displayed on the kbyg screen -> KBYG requires very specific ticket data wrt purchase date so not automatable


