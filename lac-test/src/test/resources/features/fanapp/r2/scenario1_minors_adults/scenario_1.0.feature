@missions @missions_r2 @scenario_1_r2
Feature: Missions R2 - Scenario 1.0

  @minor_adult_user @test_ab
  Scenario: Complete Minor-Adult New User Registration and Ticket Purchase
    Given An minor_adult who has personal information generated
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
    And the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the game to buy 1 seat without accessibility
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user uses an existing payment method
    And completes the ticket purchase process
    Then the user is able to see the ticket that was purchased before


    #  Objective of this scenario:
    #   - Register a new user of type minor_adult --> OK
    #   - Complete OTP registration via email and phone --> OK
    #   - Perform Game Face registration --> BLOCKED (pending to finish the mock development)
    #   - Validate educational and flow that it is for GF --> BLOCKED
    #   - Perform Identity Pass registration --> BLOCKED (pending to finish the mock development)
    #   - Validate educational and flow that it is for IP --> BLOCKED
    #   - Perform the registration of a payment method in the user account creation process --> BLOCKED (Reported bug https://laclippers.atlassian.net/browse/CA-42053)
    #   - Validation that the reminders are not shown since a complete user registration has been performed --> BLOCKED
    #   - Validation that Age Verification is disabled by user type --> BLOCKED
    #   - Purchase an event/game using the payment method charged when creating the account --> OK
    #   - Verify that the purchase was made correctly, and that it is displayed correctly in the user's profile in the My tickets module --> IN PROGRESS
    #
    #
    #  Steps defined for ADD when scenario objectives are unlocked
    #   - And the user complete the game face registration
    #   - And the user is automatically verified as an minor_adult in Gamer Face analysis (Review correct flow for the type user)
    #   - And the user complete the identity pass registration
    #   - And the user completes the payment method registration
    #   - And the user navigates to my profile
    #   - And the Game Face reminder is not displayed
    #   - And the Identity Pass reminder is not displayed
    #   - And the Payment Method reminder is not displayed
    #   - And the age verification is disabled
    #
    #
    #  Steps defined for REMOVAL when scenario objectives are unlocked
    #   - And the user skip the game face registration
    #   - And the user skip the identity pass registration
    #   - And the user skip the payment method registration
    #   - And the user select payment method management from menu
    #   - And the user adds 1 valid payment methods
    #
    #
    #  Steps defined for improvement
    #   - And the user taps on back button to return to the previous screen --> try not to repeat consecutively some step, make a step that indicates the action, e.g.: The user navigate from point A to point B