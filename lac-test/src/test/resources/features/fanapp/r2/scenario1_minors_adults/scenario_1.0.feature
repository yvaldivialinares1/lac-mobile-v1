@missions @missions_r2 @scenario_1_r2 @r2_all
Feature: Missions R2 - Scenario 1.0

  @minor_adult_user @test_ab
  Scenario: Complete Minor-Adult New User Registration and Ticket Purchase
    Given the user needs event with 1 seat with accessibility and 0 parking with any garage
    And the user is an minor_adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the game face registration
    And the user completes the identity pass registration
    And the user completes the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my payments
    And the user select payment method management from menu
    Then the user validates the list of the payment cards in my profile
    When the user navigates back to Home
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the ticket
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user proceeds to checkout
    And the user uses an existing payment method in checkout screen
    And completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen

  @scenario_with_login
  Scenario: Complete Minor-Adult New User Registration and Ticket Purchase
    Given the user is on the Welcome Home screen
    And the user logs in with credentials for scenario "1.0"
    When the user navigates to my payments
    And the user select payment method management from menu
    Then the user validates the list of the payment cards in my profile
    When the user navigates back to Home
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the ticket
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user proceeds to checkout
    And the user uses an existing payment method in checkout screen
    And completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen