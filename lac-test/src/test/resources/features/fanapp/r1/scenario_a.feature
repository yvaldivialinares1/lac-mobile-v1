@missions @missions_r1 @r1_all
Feature: Missions R1 - Scenario A

  This scenario tests the following functionalities:
  - Register a new user of type adult
  - Skips Game Face, Identity, Payment Method educational screens
  - Complete OTP registration via email and phone
  - Perform the registration of a payment method from profile
  - Change app theme to concert fan
  - Purchase an event using the payment method recently added
  - Verify that the purchase was made correctly, and that it is displayed correctly in the
  - Display Identity Pass, Game Face ID, Age Verification reminder
  - Complete the game face registration

  @adult_user @scenarioA
  Scenario: Fan older than 21 creates an account with incomplete profile and buy tickets with a valid payments method
    Given the user needs event with 2 seat without accessibility and 0 parking with any garage
    And the user is an adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user finalizes the registration process by completing the following stages
      | |
    And the user completes the registration process and is on the home screen
    And the user navigates to my payments
    And the user select payment method management from menu
    And the user adds 1 valid payment methods
    Then the user validates the list of the payment cards in my profile
    When the user navigates back to Home
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the ticket
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user proceeds to checkout
    And the user uses an existing payment method in checkout screen
    And completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen
    And the user navigates to "My Profile" from "Home"
    And the Game Face ID, Age Verification, Identity Pass reminder is displayed
    And the user taps on the Game Face ID reminder
    And the user completes the game face registration from reminder
#    And the user is able to see the ticket that was purchased before
#    And the user is able to see the ticket that was purchased before -> This step needs to be validated from profile - My Tickets screen

    #  Objective of this scenario:
    #   - Register a new user of type adult --> OK
    #   - Skip GA, ID, AP educational screens --> OK
    #   - Complete OTP registration via email and phone --> OK
    #   - Perform the registration of a payment method from profile --> OK
    #   - Change app theme to concert fan --> OK
    #   - Purchase an event using the payment method recently added --> OK
    #   - Verify that the purchase was made correctly, and that it is displayed correctly in the
    #   - user's profile --> (Pending to validate, the purchase does not appear immediately )# - Navigate back to My Profile --> OK
    #   - Display Identity Pass, Game Face ID, Age Verification reminder --> OK
    #   - Complete the game face registration --> OK
    #   - Steps defined for ADD when scenario objectives are unlocked
    #   - Uncomment this step: the user is able to see the ticket that was purchased before  <- When ticket appers
    #     immediately