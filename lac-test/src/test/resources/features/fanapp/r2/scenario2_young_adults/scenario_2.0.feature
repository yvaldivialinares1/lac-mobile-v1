@missions @missions_r2 @scenario_2_r2
Feature: Missions R2 - Scenario 2.0

  @young_adult_user
  Scenario: Complete Young-Adult New User Registration, add Mini Account and Ticket Purchase without mini account
    Given An young_adult who has personal information generated and his phone number is from finland
    And The user doesn’t have a Clipper account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
#    And the user complete the game face registration (BLOCKED: Pending mockup)
#    And the user is automatically verified as an adult in Gamer Face analysis (BLOCKED: Pending mockup)
    And the user skip the game face registration
#    And the user complete the identity pass registration (BLOCKED: Pending mockup)
    And the user skip the identity pass registration
#    And the user completes the payment method registration BLOCKED (Bug https://laclippers.atlassian.net/browse/CA-42053)
    And the user skip the payment method registration
    And the user completes the registration process and is on the home screen
    #Add card from profile: The following steps are introduced temporally to compensate the missing payment method registration.
    And the user navigates to my payments
    And the user select payment method management from menu
    And the user adds 1 valid payment methods
    And the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    #Add card block ends here
#    And the user navigates to my profile (SKIPPED: No sense to perform while following steps are blocked)
#    And the Game Face reminder is not displayed (BLOCKED: Pending mockup)
#    And the Identity Pass reminder is not displayed (BLOCKED due to dependency with identity pass registration)
#    And the Payment Method reminder is not displayed (BLOCKED due to dependency with payment method registration)
#    And the age verification is disabled (BLOCKED due to dependency with identity pass)
#    And the user adds 1 mini account profile (BLOCKED: https://laclippers.atlassian.net/browse/CA-44378)
#    And the user navigates from profile to home (SKIPPED: No sense to perform since following steps are blocked)
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the event to buy 1 seat without accessibility
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user uses an existing payment method
    And completes the ticket purchase process
#    Then the user is able to see the ticket that was purchased before (BLOCKED: https://laclippers.atlassian.net/browse/CA-44232)
#    And the user is able to see their game face id on their profile (BLOCKED: Pending mockup)
#    And the user is able to see their identity pass on their profile (BLOCKED: Pending mockup)

  #  Objective of this scenario:
    #   - Register a new user of type young_adult --> OK
    #   - Complete OTP registration via email and phone --> OK
    #   - Perform Game Face registration --> BLOCKED (pending to finish the mock development)
    #   - Validate educational and flow that it is for GF --> BLOCKED
    #   - Perform Identity Pass registration --> BLOCKED (pending to finish the mock development)
    #   - Perform the registration of a payment method in the user account creation process --> BLOCKED (Reported bug https://laclippers.atlassian.net/browse/CA-42053)
    #   - Validation that the reminders are not shown since a complete user registration has been performed --> BLOCKED
    #   - Validation that Age Verification is disabled by user type --> BLOCKED
    #   - Add a mini fan account profile --> BLOCKED (https://laclippers.atlassian.net/browse/CA-44378)
    #   - Purchase an event/game using the payment method charged when creating the account --> OK
    #   - Verify that the purchase was made correctly, and that it is displayed correctly in the user's profile in the My tickets module --> (BLOCKED: https://laclippers.atlassian.net/browse/CA-44232)
    #   - Verify user can see game face and identity pass on their profile --> BLOCKED
    #
    #  Steps defined for REMOVAL when scenario objectives are unlocked
    #   - And the user skip the game face registration
    #   - And the user skip the identity pass registration
    #   - And the user skip the payment method registration
    #   - Add card from profile block