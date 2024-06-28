Feature: Fan Payments

  This scenario test the following functionalities:
  - Staff member is logged into the app
  - Fan arrives and wants to add a new payment method
  - Staff member search the Fan profile and taps on payment methods
  - Staff members adds a new payment method filling the mandatory information
  - The user sees the new payment method added and can use it for purchase

  Scenario Outline: LAC employee adds a new payment method from a Fan
    Given an user logs in the application using staff credentials and skip game face ID
    And the following fan accounts exist:
      | type  |
      | ADULT |
    And the user is helping a fan for add a new payment method
    And selects a fan account of "ADULT" searching by email
    When the user goes to Payment module showing <number> payment methods
    And the user completes the adding card process <number>
    Then the user sees a confirmation message
    And the new payment method is added

    Examples:
      | number |
      | 0      |