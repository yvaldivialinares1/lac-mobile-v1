@missions @missions_r1 @r1_all
Feature: Missions R1 - Scenario D

  This scenario tests the following functionalities:
  - Register a new user of type adult
  - Complete payment method educational at onboarding
  - Skip Game Face, Identity Pass educational screens
  - Change app theme to concert fan
  - Purchase an event adding the payment method in the process
  - Verify that the purchase was made correctly, and that it is displayed correctly
  - Adds a vehicle and verifies the list of vehicles
  - In profile, Identity Pass, Game Face ID reminder are displayed
  - Complete the Identity Pass
  - Validate list of payment methods

  @adult_user
  Scenario: Fan older than 21 creates an account adding a payment method and buys tickets adding a new payment method
    Given the user needs event with 2 seat without accessibility and 0 parking with any garage
    And the user is an adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user finalizes the registration process by completing the following stages
      | Payment Methods |
    And the user completes the registration process and is on the home screen
    And the user navigates to my payments
    And the user select payment method management from menu
    Then the user validates the list of the payment cards in my profile
    And the user navigates back to Home
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the ticket
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user proceeds to checkout
    And the user adds a new payment method for the purchase of the ticket
    Then the user validates the added new payment card for the purchase of the ticket
    When completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen
#    And the user checks the information displayed on the kbyg screen
    And the user navigates to My Vehicles
    And the user adds 1 vehicles
    And the user sees the updated list of vehicles
    And the user navigates back to My Profile
    And the Game Face ID, Identity Pass reminder is displayed
    And the user taps on the Game Face ID reminder
    And the user completes the game face registration from reminder
    And the user taps on the Identity Pass reminder
    And the user completes the identity pass registration from reminder
    #Then the user is able to see their identity pass on their profile (blocked : mock pending)
    #And the user is able to see the ticket that was purchased before
    And the user navigates to my payments from my profile
    And the user select payment method management from menu
    And the user validates the list of the payment cards in my profile


  	  #    And the user taps on the identity pass reminder (blocked : mock pending)
	  #    And the user finishes the identity pass flow
	  #    Then the user is able to see their identity pass on their profile

	  #  Steps defined for improvement
  	  #    And the user is able to see the ticket that was purchased before --> need to improve or discard this step when multiple tickets are purchased as on my profile screen we can see only 1 recent ticket
      #    And the user checks the information displayed on the kbyg screen -> KBYG requires very specific ticket data wrt purchase date so not automatable