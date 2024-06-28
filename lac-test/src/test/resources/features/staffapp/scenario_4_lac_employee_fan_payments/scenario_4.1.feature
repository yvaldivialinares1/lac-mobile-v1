Feature: Fan Payments

  This scenario test the following functionalities:
  - Staff member is logged into the app
  - Fan arrives and wants to validate their payment methods
  - Staff member search the Fan profile and taps on payment methods
  - The payment methods screen shows max 3
  - If the user wants to validate the default cards showed, can tap on them
  - If the user wants to validate a card that is not showed, cap tap on View All option and tap on the card
  - The fan can see the details of each card

  Scenario Outline: LAC employee validates already saved payments methods from a Fan
    Given an user logs in the application using staff credentials and skip game face ID
    And is helping a Fan for review their payment methods
    And selects fan profile with email "<fan email>"
    When the user goes to Payment module showing <number> payment methods
    And the user goes on the view all for search on all the payment methods <number>
    And selects the preferred card showing the general information <number>
    When other card different to preferred card is selected shows the general information
    Then the card is marked as the preferred payment method

    Examples:
      | fan email                | number |
      | lina.barrero@globant.com | 3      |