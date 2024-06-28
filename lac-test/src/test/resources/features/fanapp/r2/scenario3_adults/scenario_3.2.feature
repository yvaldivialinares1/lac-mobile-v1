@missions @missions_r2 @smoke_r2 @scenario_3_r2 @r2_all
Feature: Missions R2 - Scenario 3.2

  @adult_user
  Scenario: Registration of a new adult user skipping all process that are possible
    Given the user is an adult who has personal information generated
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
    And the Game Face ID, Identity Pass, Payment Method, Age Verification reminder is displayed
    And the user taps on the Game Face ID reminder
    And the user is able to see the educational for Game Face ID
    # And the user finishes the game face id flow (BLOCKED: Pending mockup)
    And the user taps on the Age Verification reminder
    And the user is able to see the educational for Age Verification
    # And the user finishes the age verification flow (BLOCKED: Pending mockup)
    And the user navigates to my payments from my profile
    And the user select payment method management from menu
    And the user adds 1 valid payment methods
    And the user navigates back to My Profile
    And the user taps on the Identity Pass reminder
    And the user is able to see the educational for Identity Pass
    And the user completes the identity pass registration
    And the user navigates to my payments from my profile
    And the user select payment method management from menu
    Then the user validates the list of the payment cards in my profile
    # And the user is able to see their game face id on their profile (BLOCKED: Pending mockup)
    # And the user has the adult result from age verification on the profile (BLOCKED: Pending mockup)
    # And the user is able to see their identity pass on their profile (BLOCKED)