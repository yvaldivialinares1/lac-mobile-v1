Feature: Scenario 1.3

  @smoke
  Scenario Outline: Fan arrives without an account and no ticket
    Given there is a car with unknown licence plate
    When employee logs in the application using email "<email>" and password "<password>"
    And employee select the "ACCESS" navigation bar option
    And employee select the random garage under parking access category
    And employee select the random line under garage parking queue
    And there is a "UNKNOWN_PLATE" queue notification for selected parking line
    Then employee see "UNKNOWN_PLATE" parking queue notification for selected parking line
    Examples:
      | email                          | password |
      | Globant-Test_user@clippers.com | Yut12878 |