@missions @missions_r2 @smoke_r2 @scenario_3_r2 @r2_all
Feature: Missions R2 - Scenario 3.1

  @adult_user
  Scenario: Registration of a new adult user skipping only the payment method process
    Given the user is an adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    # And the user complete the game face registration (BLOCKED: Pending mockup)
    # And the user is NOT automatically verified on Gamer Face, age verification is required (BLOCKED: Pending mockup)
    And the user skip the game face registration
    And the user completes the identity pass registration
    And the user skip the payment method registration
    And the user completes the registration process and is on the home screen
    And the user navigates to my profile
    And the Payment Method reminder is displayed
    And the user taps on the Payment Method reminder
    And the user is able to see the educational for Payment Method
    And the user adds 1 valid payment methods from educational
    # Then the user is able to see their identity pass on their profile (BLOCKED: Pending mockup)
    # And the user is able to see their game face id on their profile (BLOCKED: Pending mockup)
    # And the user has the adult result from age verification on the profile (BLOCKED: Pending mockup)
    And the user navigates to my payments from my profile
    And the user select payment method management from menu
    Then the user validates the list of the payment cards in my profile