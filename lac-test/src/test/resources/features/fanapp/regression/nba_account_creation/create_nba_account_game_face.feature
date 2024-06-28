Feature: Create Account with NBA Credentials

  This scenario tests the following functionalities:
  - Register a new user of type adult, with NBA credentials
  - Complete OTP registration via email and phone
  - Complete the game face registration
  - Complete the identity pass registration
  - Complete the the payment method registration
  - Complete the registration process and is on the home screen

  @regression @create_account_nba_credentials @scenario_1_nba
  Scenario: Create Account with NBA Credentials (Partial data) and add Game Face Id. Starting the flow by Login
    Given the user is an adult who has personal information generated
    And the user doesn't have a Clippers account but is registered on the NBA with partial data
    And the user is on the Welcome Home screen
    When The user logs in with NBA credentials coming from Login flow
    And the user starts the account registration process with NBA credentials by Login
    And the NBA user completes the account registration with his email and partial preloaded data
    And the user completes the phone verification
    And the user completes the game face registration
    And the user completes the identity pass registration
    And the user completes the payment method registration
    Then the user completes the registration process and is on the home screen