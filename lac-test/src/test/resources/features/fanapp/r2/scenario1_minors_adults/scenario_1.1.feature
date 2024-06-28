@missions @missions_r2 @scenario_1_r2 @r2_all
Feature: Missions R2 - Scenario 1.1

  @minor_adult_user
  Scenario: Complete Minor-Adult New User Registration and Ticket Purchase
    Given the user needs event with 1 seat without accessibility and 0 parking with any garage
    And the user is a minor_adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the game face registration
    And the user skip the identity pass registration
    And the user completes the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my profile
    And the Game Face ID, Identity Pass reminder is displayed
    And the user taps on the Identity Pass reminder
    And the user is able to see the educational for Identity Pass
    And the user completes the identity pass registration
    And the user taps on the Game Face ID reminder
    And the user is able to see the educational for Game Face ID
    #And the user finishes the game face id flow (BLOCKED: Pending mockup)
    And the user navigates to the home screen from any part of the profile screen
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the ticket
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user proceeds to checkout
    And the user uses an existing payment method in checkout screen
    And completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen
    #Uncomment this 2 steps when the ticket appears on my profile screen
    #And the user skip the manage your tickets screen
    #And the user navigates to my profile
    #Then the user is able to see the ticket that was purchased before