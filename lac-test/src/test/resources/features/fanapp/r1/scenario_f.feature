@missions @missions_r1
Feature: Missions R1 - Scenario F

  @minor_adult_user
  Scenario Outline: Minor Fan (13-17) creates an account without Skips , deletes a payment method and buy different types of tickets
    Given An minor_adult who has personal information generated
    And The user doesn’t have a Clipper account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user skip the game face registration
    And the user complete the identity pass registration
    And the user completes the payment method registration
    And the user completes the registration process and is on the home screen
    And the user changes the view to Concert Fan
    And the user navigates to Tickets Screen
    And the user looks for the game to buy 1 seat without accessibility
    And the user access the option to Buy Tickets
    And the user selects seat to purchase
    And the user uses an existing payment method
    And completes the ticket purchase process
    And the user navigates to my payments
    And the user select payment method management from menu
    And And the user deletes "<number_of_payment_methods_delete>" payment methods
#    And the user checks the information displayed on the kbyg screen
    Then the user is able to see their identity pass on their profile
    And the user is able to see the ticket that was purchased before
    And the user navigates to my payments from my profile
    And the user select payment method management from menu
    And the user validates the list of the payment cards in my profile
    Examples:
      | total_seat | accessibility | number_of_payment_methods_delete |
      | 1          | without       | 1                                |