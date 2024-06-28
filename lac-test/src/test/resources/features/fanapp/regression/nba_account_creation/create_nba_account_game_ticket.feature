Feature: Create Account with NBA Credentials

  This scenario tests the following functionalities:
  - Register a new user of type adult, with NBA credentials
  - Skip GF, ID educational screens
  - Complete OTP registration via email and phone
  - Perform the registration of a payment method
  - Purchase an event using the payment method recently added
  - Verify the purchased ticket on manage my ticket screen

  @regression @create_account_nba_credentials @scenario_2_nba
  Scenario: Create Account with NBA Credentials (Full data) and buy Game Ticket. Starting the flow by LOG IN
    Given the user needs event with 1 seat without accessibility and 0 parking with any garage
    And the user is an adult who has personal information generated
    And the user doesn't have a Clippers account but is registered on the NBA with full data
    And the user is on the Welcome Home screen
    When The user logs in with NBA credentials coming from Login flow
    And the user starts the account registration process with NBA credentials by Login
    And the NBA user completes the account registration with his email and all preloaded data
    And the user completes the phone verification
    And the user skip the game face registration
    And the user skip the identity pass registration
    And the user completes the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to Tickets Screen
    And the user looks for the ticket
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user proceeds to checkout
    And the user uses an existing payment method in checkout screen
    And completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen