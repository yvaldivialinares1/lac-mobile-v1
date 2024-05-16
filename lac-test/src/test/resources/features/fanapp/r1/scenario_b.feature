@missions @missions_r1
Feature: Missions R1 - Scenario B

  @young_adult_user
  Scenario: Adult fan under 21 creates an account with complete profile and buys tickets with a new card added only for that transaction
    Given An young_adult who has personal information generated
    And The user doesn’t have a Clipper account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the game face registration
    And the user skip the identity pass registration
#    And the user complete the identity pass registration
    And the user completes the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my payments
    And the user select payment method management from menu
    And the user validates the list of the payment cards in my profile
    And the user sets or edit a nickname for their existing payments method
    And the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the game to buy 2 seat without accessibility
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user adds a new payment method for the purchase of the ticket
    Then the user validates the added new payment card for the purchase of the ticket
    And completes the ticket purchase process
#    And the user checks the information displayed on the kbyg screen
    Then the user validate the purchased ticket on manage my ticket screen
    And the user navigates to my payments
    And the user select payment method management from menu
    And the user validates the list of the payment cards in my profile
    And the user returns to the Profile screen from My Payment Methods Management
    And the user closes the section in the app

    #     Objective of this scenario:
    #   - Register a new user of type young_adult --> OK
    #   - Complete OTP registration via email and phone --> OK
    #   - complete the identity pass registration --> BLOCKED (pending to finish the mock development)
    #   - Perform the registration of a payment method in the user account creation process --> BLOCKED (Reported bug https://laclippers.atlassian.net/browse/CA-42053)
    #   - validates the list of the payment cards in my profile
    #   - sets or edit a nickname for their existing payments method
    #   - change the theme to concert
    #   - user selects seat to purchase the ticket for event/game
    #   - adds a new payment method for the purchase of the ticket in checkout screen
    #   - completes the purchase
    #   - checking for the ticket that was purchased before
    #   - validate the updated list of the payment cards in my profile
    #   - closes the app
    #
    #  Steps defined for ADD when scenario objectives are unlocked
    #   - And the user complete the identity pass registration
    #
    #
    #  Steps defined for REMOVAL when scenario objectives are unlocked
    #   - And the user skip the identity pass registration -- line no 15
    #
    #  Steps defined for improvement
    #   - And the user taps on back button to return to the previous screen --> try not to repeat consecutively some step, make a step that indicates the action, e.g.: The user navigate from point A to point B