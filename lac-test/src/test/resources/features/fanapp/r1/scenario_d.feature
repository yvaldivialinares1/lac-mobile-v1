@missions @missions_r1
Feature: Missions R1 - Scenario D

  @adult_user
  Scenario: Fan older than 21 creates an account adding a payment method and buys tickets adding a new payment method
    Given An adult who has personal information generated
    And The user doesn’t have a Clipper account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the game face registration
    And the user skip the identity pass registration
    And the user completes the payment method registration
    And the user skip the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my payments
    And the user select payment method management from menu
    And the user validates the list of the payment cards in my profile
    When the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    And the user taps on back button to return to the previous screen
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the game to buy 2 seat without accessibility
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user adds a new payment method for the purchase of the ticket
    And completes the ticket purchase process
#    And the user checks the information displayed on the kbyg screen
    And the user navigates to My Vehicles
    And the user adds 1 vehicles
    And the user navigates back from my vehicles to my profile
#    And the user taps on the identity pass reminder (blocked : mock pending)
#    And the user finishes the identity pass flow
#    Then the user is able to see their identity pass on their profile
#    And the user is able to see the ticket that was purchased before
    And the user navigates to my payments from my profile
    And the user select payment method management from menu
    And the user validates the list of the payment cards in my profile


  	  #    And the user taps on the identity pass reminder (blocked : mock pending)
	  #    And the user finishes the identity pass flow
	  #    Then the user is able to see their identity pass on their profile

	  #  Steps defined for improvement
  	  #    And the user is able to see the ticket that was purchased before --> need to improve or discard this step when multiple tickets are purchased as on my profile screen we can see only 1 recent ticket
      #    And the user checks the information displayed on the kbyg screen -> KBYG requires very specific ticket data wrt purchase date so not automatable