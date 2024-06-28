@missions_r2 @scenario_4_r2
Feature: Missions R2 - Scenario 4.0

  @young_adult_user
  Scenario: Complete Young-Adult New User Registration, add Mini Account and share payment method with Mini account
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
    And the user navigates to my payments
    And the user select payment method management from menu
    And the user adds 1 valid payment methods
    Then the user validates the list of the payment cards in my profile
    And the user navigates back to My Profile
    And the user navigates to Mini Accounts
    And the user is able to see the educational for Mini Accounts
    When the user adds 1 mini account profile
    Then the user validates the mini account detail added before
#    And the user skip the identity pass registration for Mini Accounts
    And the user navigates back to My Profile
    And the user navigates to my payments from my profile
    And the user select share your payment methods from menu
    And the user clicks on share card with Mini account
    And the user is able to see the educational screen for Mini shared card
    And the user uses an existing payment method in share payment method screen
    When the user shares a payment method with the Mini account
    Then the user validates that the correct payment method is shared with the Mini account


     # Steps defined for ADD when scenario objectives are unlocked
     # And the user skip the identity pass registration for Mini Accounts