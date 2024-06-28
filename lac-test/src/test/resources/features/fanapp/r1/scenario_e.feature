@missions @missions_r1 @r1_all
Feature: Missions R1 - Scenario E

  This scenario tests the following functionalities:
  - Register a new user of type adult
  - Complete payment method and Identity Pass educationals at onboarding
  - Skip Game Face
  - Change app theme to concert fan
  - Purchase an event using the existing payment method
  - Verify that the purchase was made correctly, and that it is displayed correctly
  - Navigates to payment methods and a add 1 payment method
  - Sets nicknames for all existing payment methods
  - Validate list of payment methods
  - Logout
  - Login
  - Completes Game Face Id from reminder
  - Verifies Age verification reminder is not displayed

  @adult_user @missing
  Scenario: Adult fan creates an account skipping all steps, buys tickets adding a new card, logs out and in, and then completes GameFace from the reminder
    Given the user needs event with 1 seat without accessibility and 0 parking with any garage
    And the user is an adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user finalizes the registration process by completing the following stages
      | Payment Methods |
      | Identity Pass   |
    And the user completes the registration process and is on the home screen
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the ticket
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user proceeds to checkout
    And the user uses an existing payment method in checkout screen
    And completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen
    And the user navigates to my payments
    And the user select payment method management from menu
    And the user adds 1 valid payment methods
    And the user sets or edit a nickname for their existing payments method
    And the user navigates back to My Profile
    And the user closes the section in the app
    And the user goes to Login screen from Welcome Home screen
    And user enters credentials to log in
    And the user navigates to my profile
    And the user taps on the Game Face ID reminder
    And the user completes the game face registration from reminder
    And the Age Verification reminder is not displayed
#    And the user checks the information displayed on the kbyg screen
#    Uncomment this 2 steps when the ticket appears on my profile screen
#    Then the user is able to see the ticket that was purchased before

        #  Objective of this scenario:
    #   - Register a new user of type adult --> OK
    #   - Complete OTP registration via email and phone --> OK
    #   - Skip Game face ID and Identity pass registration  --> OK
    #   - Perform the registration of a payment method from user creation --> OK
    #   - Purchase a game ticket from concert fan theme using the payment method added before --> OK
    #   - Perform the registration of a new payment method from menu --> OK
    #   - Edit and validate the information of the existing payment method --> OK
    #   - Sign out and Log in again --> OK
    #   - Verify that the purchase was made correctly, and that it is displayed correctly in the
    #     user's profile --> (Pending to validate, the purchase does not appear immediately )
    #
    #   # Blockers
    #   - kbyg is not reachable yet so that "And the user checks the information displayed on the kbyg screen" step
    #     is blocked