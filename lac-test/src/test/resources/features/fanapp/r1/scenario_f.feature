@missions @missions_r1 @r1_all
Feature: Missions R1 - Scenario F
  As a new user who is a minor,
  I want to register an account, verify my email, add a payment method, purchase tickets, and manage my payment methods,
  So that I can enjoy a seamless experience using the app's features and participate in events.

  @minor_adult_user @scenarioF
  Scenario: Minor Fan (13-17) creates an account without Skips , deletes a payment method and buy different types of tickets
    Given the user needs event with 2 seat without accessibility and 0 parking with any garage
    And the user is a minor_adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user completes the game face registration
    And the user does not need to do Age Verification
    And the user completes the payment method registration
    And the user skip the identity pass registration
    And the user completes the registration process and is on the home screen
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the ticket
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user proceeds to checkout
    And the user uses an existing payment method in checkout screen
    And completes the ticket purchase process
    Then the user validate the purchased ticket on manage my ticket screen
    And the user navigates to my payments
    And the user select payment method management from menu
    And the user deletes 1 payment methods
#    And the user checks the information displayed on the kbyg screen
#    Then the user is able to see their identity pass on their profile
#    Uncomment this step when the ticket appears on my profile screen
#    And the user is able to see the ticket that was purchased before
#    TODO: Uncomment this following steps when previous steps are completed
#    And the user navigates to my payments from my profile
#    And the user select payment method management from menu
    And the user validates the list of the payment cards in my profile


 # Objective of this scenario:
    # - To register a new user who is a minor without skipping only Game Face Id in the registration process
    # - To complete OTP verification through email
    # - To potentially complete the identity pass registration (currently commented out pending future implementation by mocking)
    # - To register a payment method during the account creation
    # - To purchase tickets using an existing payment method
    # - To navigate the app to delete a payment method
    # - To validate the remaining payment methods in the user's profile
    #
    # Steps defined for addition when scenario objectives are unlocked:
    # - And the user completes the identity pass registration (Waiting for mock implementation on STG environment)
    # - Then the user is able to see their identity pass on their profile
    #
    # Steps defined for confirmation:
    #
    # Steps defined for improvement:
    # - Consider avoiding consecutive navigation steps by creating a direct step for navigation from one point to another.