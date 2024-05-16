@missions @missions_r2 @scenario_1_r2
Feature: Missions R2 - Scenario 1.1

  @minor_adult_user
  Scenario: Complete Minor-Adult New User Registration and Ticket Purchase
    Given A minor_adult who has personal information generated
    And The user doesn’t have a Clipper account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the game face registration
    And the user skip the identity pass registration
    And the user completes the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my profile
    And the Game Face ID reminder is displayed
    And the Identity Pass reminder is displayed
    And the Payment Method reminder is not displayed
    And the user taps on the Identity Pass reminder
    And the user is able to see the educational for Identity Pass
    #And the user finishes the identity pass flow (BLOCKED: Pending solution)
    And the user taps on the Game Face ID reminder
    And the user is able to see the educational for Game Face ID
    #And the user finishes the game face id flow (BLOCKED: Pending mockup)
    And the user changes the view to Concert Fan
    And the user looks for the event to buy 1 seat without accessibility
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user uses an existing payment method
    And completes the ticket purchase process
    Then the user is able to see the ticket that was purchased before