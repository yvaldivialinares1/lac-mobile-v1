Feature: Create Account with NBA Credentials

  This scenario tests the following functionalities:
  - Register a new user of type adult, with NBA credentials
  - Skip GF, IP, PM educational screens
  - Complete OTP registration via email and phone
  - Perform the registration of a payment method from profile
  - Purchase an event using the payment method recently added
  - Verify the list of the payment cards in my profile

  @regression @create_account_nba_credentials @scenario_3_nba
  Scenario: Create Account with NBA Credentials (Full data) and add payment method. Starting the flow by LOG IN
    Given the user is an adult who has personal information generated
    And the user doesn't have a Clippers account but is registered on the NBA with full data
    And the user is on the Welcome Home screen
    When The user logs in with NBA credentials coming from Login flow
    And the user starts the account registration process with NBA credentials by Login
    And the NBA user completes the account registration with his email and all preloaded data
    And the user completes the phone verification
    And the user skip the game face registration
    And the user skip the identity pass registration
    And the user skip the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my profile
    And the user navigates to my payments from my profile
    And the user select payment method management from menu
    Then the user adds 1 valid payment methods
    And the user navigates back to My Profile
    And the user navigates to my payments from my profile
    And the user select payment method management from menu
    And the user validates the list of the payment cards in my profile