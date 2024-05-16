@missions @missions_r2 @smoke_r2 @scenario_3_r2
Feature: Missions R2 - Scenario 3.0

  @adult_user
  Scenario: Complete Adult New User Registration and Ticket Purchase
    Given An adult who has personal information generated
    And The user doesn’t have a Clipper account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    # And the user complete the game face registration (BLOCKED: Pending mockup)
    # And the user is automatically verified as an adult in Gamer Face analysis (BLOCKED: Pending mockup)
    And the user skip the game face registration
    #And the user complete the identity pass registration (BLOCKED: Pending mockup)
    And the user skip the identity pass registration
    #And the user completes the payment method registration (BLOCKED: Pending mockup)
    And the user skip the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my profile
    # And the Game Face reminder is not displayed (BLOCKED: Pending mockup)
    #And the Identity Pass reminder is not displayed (BLOCKED: Pending mockup)
    #And the Payment Method reminder is not displayed (BLOCKED: Pending mockup)
    And the user navigates to Mini Accounts
    And the user is able to see the educational for Mini Accounts
    And the user adds 1 mini account profile
    And the user validates the mini account detail added before
    And the user navigates to Home from Mini Accounts
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the event to buy 1 seat without accessibility
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user uses an existing payment method
    And completes the ticket purchase process
    Then the user is able to see the ticket that was purchased before
    #And the user is able to see their game face id on their profile (BLOCKED: Pending mockup)
    #And the user has the adult result from age verification on the profile (BLOCKED: Pending mockup)
    #And the user is able to see their identity pass on their profile (BLOCKED: Pending mockup)