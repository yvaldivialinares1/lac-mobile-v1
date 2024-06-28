@missions @missions_r1 @r1_all
Feature: Missions R1 - Scenario C

	This scenario tests the following functionalities:
	- Register a new user of type adult
	- Complete Game Face
	- Skip Identity Id and payment method educational screens
	- Change app theme to concert fan
	- Purchase an event adding the payment method in the process
	- Verify that the purchase was made correctly, and that it is displayed correctly
	- Navigates to payment methods and a add 2 payment methods
	- Validate list of payment methods
	- Deletes 2 payment methods
	- Adds a vehicle and verifies the list of vehicles
	- Complete the Identity Pass

  @adult_user
  Scenario Outline: Fan older than 21 creates an account skips adding identity pass adds payment method and adds a vehicle completing identity pass reminder
	Given the user needs event with 2 seat without accessibility and 0 parking with any garage
  	And the user is an adult who has personal information generated
	And the user doesn't have a Clippers account
	When the user is on the Welcome Home screen
	And the user starts the account registration process
	And the user completes the account registration data with his email address
	And the user completes the phone verification
	  And the user finalizes the registration process by completing the following stages
		  | Game Face ID    |
	And the user completes the registration process and is on the home screen
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
	When the user navigates to my payments
	And the user select payment method management from menu
	And the user adds <number_of_payment_methods_add> valid payment methods
	Then the user validates the list of the payment cards in my profile
	When the user deletes <number_of_payment_methods_delete> payment methods
	And the user navigates back to My Profile
	And the user navigates to My Vehicles from My Profile
	And the user adds 1 vehicles
	And the user sees the updated list of vehicles
    And the user navigates back from my vehicles to my profile
	And the user taps on the Identity Pass reminder
	And the user completes the identity pass registration from reminder
	#Then the user is able to see their identity pass on their profile (blocked : mock pending)
	#And the user is able to see the ticket that was purchased before


	Examples:
	  | total_seat | accessibility | number_of_payment_methods_add | number_of_payment_methods_delete | number_of_vehicles |
	  | 1          | without       | 2                             | 1                                | 1                  |

  	  #    And the user taps on the identity pass reminder (blocked : mock pending)
	  #    And the user finishes the identity pass flow
	  #    Then the user is able to see their identity pass on their profile

	  #  Steps defined for improvement
	  #   - And the user taps on back button to return to the previous screen --> try not to repeat consecutively some step, make a step that indicates the action, e.g.: The user navigate from point A to point B
  	  #    And the user is able to see the ticket that was purchased before --> need to improve or discard this step when multiple tickets are purchased as on my profile screen we can see only 1 recent ticket