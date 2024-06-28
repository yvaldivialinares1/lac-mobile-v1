@missions @missions_r1 @r1_all
Feature: Missions R1 - Scenario G

  This scenario tests the following functionalities:
  - Register a new user of type adult
  - Complete Game face, Payment method and Identity Pass at onboarding
  - Change app theme to concert fan
  - Purchase an event using the existing payment method
  - Verify that the purchase was made correctly, and that it is displayed correctly
  - Navigates to payment methods and a adds 3 payment methods
  - Sets nicknames for all existing payment methods
  - Validate list of payment methods

  @minor_adult_user
  Scenario Outline: Minor Fan (13-17) creates an account only adding Game Face, buys a tickets with accessibility seats and parking pass and adds a Vehicle
    Given the user needs event with 1 seat without accessibility and 0 parking with any garage
    And the user is a minor_adult who has personal information generated
    And the user doesn't have a Clippers account
    When the user is on the Welcome Home screen
    And the user starts the account registration process
    And the user completes the account registration data with his email address
    And the user completes the phone verification
    And the user finalizes the registration process by completing the following stages
      | Payment Methods |
      | Game Face ID    |
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
    And the user adds <number_of_payment_methods> valid payment methods
    And the user sets or edit a nickname for their existing payments method
    # And the user checks the information displayed on the kbyg screen
    # Then the user is able to see their identity pass on their profile
    # And the user is able to see the ticket that was purchased before  (BLOCKED: https://laclippers.atlassian.net/browse/CA-44232)
    # TODO Uncomment next step when previous steps are completed
    #  And the user navigates to my payments from my profile
    And the user validates the list of the payment cards in my profile
    Examples:
      | total_seat | accessibility | number_of_payment_methods |
      | 1          | without       | 3                         |