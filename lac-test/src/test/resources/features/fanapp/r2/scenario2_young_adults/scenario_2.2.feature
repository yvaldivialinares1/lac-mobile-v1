@missions @missions_r2 @scenario_2_r2 @r2_all
Feature: Missions R2 - Scenario 2.2

  @young_adult_user
  Scenario: Complete Young-Adult New User Registration and Ticket Purchase
    Given the user is a young_adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the game face registration
    And the user skip the identity pass registration
    And the user skip the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my profile
    And the Game Face ID, Identity Pass, Payment Method reminder is displayed
    And the user taps on the Game Face ID reminder
    And the user is able to see the educational for Game Face ID
    # And the user finishes the game face id flow (BLOCKED: Pending Mockup)
    And the user navigates to Mini Accounts
    And the user is able to see the educational for Mini Accounts
    And the user adds 2 mini account profile
    Then the user validates the mini account detail added before