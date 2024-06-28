#TODO Add mission tags when ready to execute
@scenario_7_r2 @game_only
Feature: Missions R2 - Scenario 7.1

  @adult_user
  Scenario: Fan older than 21 creates an account with incomplete profile and buy Parking tickets for sold out event/game with a valid payments method
    Given the user needs 2 parking seat with any availability and any garage for game with sold out
    And the user is an adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the identity pass registration
    And the user skip the payment method registration
    And the user skip the game face registration
#   And the user complete the game face registration
    And the user completes the registration process and is on the home screen
    When the user navigates to my payments
    And the user select payment method management from menu
    And the user adds 1 valid payment methods
    Then the user validates the list of the payment cards in my profile
    And the user navigates to the home screen from any part of the profile screen
    When the user navigates to Tickets Screen
    Then user should see clippers games section is selected by default
    And the user looks for the ticket
    And the user access the option to Buy Tickets
    And the user selects 2 parking tickets
    And the user selects continue button to purchase
    And the user proceeds to checkout
    And the user uses an existing payment method in checkout screen
    And completes the ticket purchase process
#   And the user is able to see the ticket that was purchased before (BLOCKED: https://laclippers.atlassian.net/browse/CA-44232)
    And the user skip the manage your tickets screen
    And the user navigates to My Vehicles
    And the user adds 1 vehicles
    And the user sees the updated list of vehicles
#   And the user buy a drink on store
#   Then validate payment history



    #  Steps defined for REMOVAL when scenario objectives are unlocked
    #   - And the user skip the game face registration
    #   - And the user skip the manage your tickets screen

  	#  Steps defined for ADD when scenario objectives are unlocked
    #  - And the user complete the game face registration
    #  - When the user buy a drink on store
    #  - Then validate payment history