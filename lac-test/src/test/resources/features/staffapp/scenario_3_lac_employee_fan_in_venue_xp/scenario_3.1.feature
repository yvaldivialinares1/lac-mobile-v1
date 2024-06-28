Feature: Fan In-Venue XP

  This scenario tests the following functionalities:
  - Staff member is logged into the app
  - Fan arrives with doubts about their tickets already purchased
  - Staff member search the Fan profile and search the tickets
  - User can filter by upcoming or past tickets
  - User can filter by Games/Parking/Club tickets
  - Fan can see information about their purchased tickets

  Scenario Outline: LAC employee validates already purchased tickets from a Fan
    Given an user logs in the application using staff credentials and skip game face ID
    And is helping a Fan with information about already purchased "<type>" tickets
    And selects fan profile with email "<fan email>"
    When the user taps on In-Venue XP tab showing <number> upcoming tickets
    And the user taps on the view all button showing all the tickets with filter options
    When the user taps on "<time>" tab filter
    Then the results only shows tickets in "<time>" time
    When the user taps on "<type>" ticket type filter
    Then the results only shows tickets of the "<type>" type

    Examples:
      | type    | fan email              | number | time     |
      | Parking | lmonsalve@clippers.com | 3      | upcoming |