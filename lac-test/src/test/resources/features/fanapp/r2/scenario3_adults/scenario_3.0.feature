@missions @missions_r2 @smoke_r2 @scenario_3_r2 @r2_all
Feature: Missions R2 - Scenario 3.0

  @adult_user @pr_review
  Scenario: Complete Adult New User Registration and Ticket Purchase
    Given the user needs event with 1 seat without accessibility and 0 parking with any garage
    And the user is an adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    # And the user complete the game face registration (BLOCKED: Pending mockup)
    # And the user is automatically verified as an adult in Gamer Face analysis (BLOCKED: Pending mockup)
    And the user skip the game face registration
    And the user completes the identity pass registration
    And the user completes the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my profile
    # And the Game Face reminder is not displayed (BLOCKED: Pending mockup)
    # And the Identity Pass reminder is not displayed (BLOCKED: Pending mockup)
    And the Payment Method reminder is not displayed
    And the user navigates to Mini Accounts
    And the user is able to see the educational for Mini Accounts
    And the user adds 1 mini account profile
    And the user validates the mini account detail added before
    And the user navigates back to Home
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the ticket
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user proceeds to checkout
    And the user uses an existing payment method in checkout screen
    And completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen
    # Then the user is able to see the ticket that was purchased before (Need to review the behavior of this step)
    # And the user is able to see their game face id on their profile (BLOCKED: Pending mockup)
    # And the user has the adult result from age verification on the profile (BLOCKED: Pending mockup)
    # And the user is able to see their identity pass on their profile (BLOCKED: Pending mockup)