Feature: Fan In-Venue XP

  This scenario tests the following functionalities:
  - Staff member is logged into the app
  - Fan arrives and wants to buy a parking ticket for a game/event
  - Staff member search the Fan profile and choose the game/event
  - If the Parking Pass is available, user can choose the garage location
  - User can choose standard/accessible/electric parking max 2
  - The fan has a payment method already registered
  - If the fan accepts the terms and conditions and accepts the price of the tickets can place the order
  - Fan can see a screen for confirm the success payment with information of the game/event and parking location
  - In the Fan information In-Venue Xp can see the purchased ticket

  Scenario Outline: LAC employee buys a Parking ticket in behalf of a Fan
    Given an user logs in the application using staff credentials and skip game face ID
    And is helping a Fan for buy a "<type>" ticket
    And selects fan profile with email "<fan email>"
    When the user taps on In-Venue XP tab showing <number> upcoming tickets
    And initiates a ticket purchase for parking pass option
    And chooses east garage location and standard parking
    And accepts terms and conditions
    Then the user validates the purchase price and payment method
    When the user places the order
    Then the confirmation screen with ticket information is displayed
    When the user views all tickets and filters by "<type>" ticket type
    Then the fan can validate the purchased ticket

    Examples:
      | fan email              | number | type    |
      | lmonsalve@clippers.com | 3      | Parking |