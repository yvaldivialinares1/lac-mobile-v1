@missions @missions_r2 @scenario_2_r2
Feature: Missions R2 - Scenario 2.1

  @young_adult_user
  Scenario: Complete Young-Adult New User Registration and Ticket Purchase
    Given A young_adult who has personal information generated
    And The user doesn’t have a Clipper account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the game face registration
    And the user skip the identity pass registration
    And the user skip the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my profile
    And the Identity Pass reminder is displayed
    And the Payment Method reminder is displayed
    And the user taps on the Payment Method reminder
    And the user is able to see the educational for Payment Method
    And the user adds 1 valid payment methods from educational
    And the user taps on the Identity Pass reminder
    And the user is able to see the educational for Identity Pass
    And the user navigates to Tickets Screen from my profile
    And the user looks for the event to buy 1 seat without accessibility
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user adds a new payment method for the purchase of the ticket
    And completes the ticket purchase process
    And the user navigates to my profile
    Then the user is able to see the ticket that was purchased before
    And the user navigates to my payments from my profile
    And the user select payment method management from menu
    And the user validates the list of the payment cards in my profile

    #  Objective of this scenario:
    #   - Register a new user of type young_adult --> OK
    #   - Complete OTP registration via email and phone --> OK
    #   - Perform Game Face registration --> BLOCKED (pending to finish the mock development)
    #   - Validate educational and flow that it is for GF --> BLOCKED
    #   - Perform Identity Pass registration --> BLOCKED (pending to finish the mock development)
    #   - Validate educational and flow that it is for IP --> BLOCKED
    #   - Validate identity pass reminder is displayed --> OK
    #   - Validate add payment method reminder is displayed --> OK
    #   - Perform the registration of a payment method from reminders --> OK
    #   - Purchase an event/game using a different payment method from the one added on the reminders --> OK
    #   - Verify that the purchase was made correctly, and that it is displayed correctly in the
    #     user's profile --> (Pending to validate, the purchase does not appear immediately )
    #   - Verify that the payment methods list is displayed on the profile --> OK
    #
    #  Steps defined for ADD when scenario objectives are unlocked
    #   - 1 And the user complete the game face registration (BLOCKED: Pending mockup)
    #     - put this step after: the user completes the phone verification
    #   - 2 And the user is automatically verified as an adult in Gamer Face analysis (BLOCKED: Pending mockup)
    #     - put this step after: the user complete the game face registration
    #   - 3 And the Game Face reminder is not displayed (BLOCKED: Pending mockup)
    #     - put this step after the first: the user navigates to my profile
    #   - 4 And the user finishes the identity pass flow
    #     - put this step after: the user is able to see the educational for Identity Pass
    #
    #  Steps defined for REMOVAL when scenario objectives are unlocked
    #   - And the user skip the game face registration
    #   - And the user skip the identity pass registration
    #