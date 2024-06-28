Feature: Create Account with NBA Credentials

  This scenario tests the following functionalities:
  - Log in a user with NBA credentials by starting the flow from "Create an account".
    * The NBA account was created earlier and the credentials are read from the credentials.json file
  - Verify that the "Email Exist For NBA" banner appears
  - Verify that the user with NBA credentials is redirected to the Login screen
  - Verify that the user can start the account creation flow from login

  @regression @create_account_nba_credentials @scenario_4_nba
  Scenario: Use NBA credentials to start the account creation flow. Starting the flow by CREATE AN ACCOUNT
    Given the user is an adult who has personal information generated
    And the user doesn't have a Clippers account but is registered on the NBA
    And the user is on the Welcome Home screen
    When the user starts the account registration process with NBA credentials by Create An Account
    Then the banner Email Exist For NBA is showed
    And the user is in the login screen
    And The user logs in with NBA credentials coming from Create an account flow
    And the user starts the account registration process with NBA credentials by Login again