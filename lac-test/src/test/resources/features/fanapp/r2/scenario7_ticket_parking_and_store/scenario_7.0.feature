#TODO Add mission tags when ready to execute
@scenario_7_r2
Feature: Missions R2 - Scenario 7.0

  @adult_user
  Scenario: Fan older than 21 creates an account with incomplete profile and buy tickets with parking addon through a new payment method
    Given the user needs event with 2 seat without accessibility and 2 parking with any garage
    And the user is an adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
     # And the user complete the game face registration (BLOCKED: Pending mockup)
    And the user skip the game face registration
    And the user skip the identity pass registration
    And the user skip the payment method registration
    And the user completes the registration process and is on the home screen
    And the user changes the view to Concert Fan
    Then user should see events section is selected by default
    When the user navigates to Tickets Screen
    And the user looks for the event
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user selects 2 parking tickets from parking add-on
    And the user proceeds to checkout
    And the user adds a new payment method for the purchase of the ticket
    Then the user validates the added new payment card for the purchase of the ticket
    When completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen
    When the user navigates to My Vehicles
    And the user adds 1 vehicles
    Then the user sees the updated list of vehicles
#    When the user buy a drink on store
#    Then validate payment history



    #  Steps defined for REMOVAL when scenario objectives are unlocked
    #   - And the user skip the game face registration

  	#  Steps defined for ADDITION when scenario objectives are unlocked
    #  - And the user complete the game face registration
    #  - When the user buy a drink on store
    #  - Then validate payment history