Feature: Scenario 1.0

  @smoke
  Scenario Outline: Fan arrives without ticket receives message in the queue
    Given a fan with a 1 registered vehicle data
    When employee logs in the application using email "<email>" and password "<password>"
    And employee select the "ACCESS" navigation bar option
    And employee select the random garage under parking access category
    And employee select the random line under garage parking queue
    And there is a "NO_PARKING_TICKET" queue notification for selected parking line
    Then employee see "NO_PARKING_TICKET" parking queue notification for selected parking line
    Examples:
      | email                          | password |
      | Globant-Test_user@clippers.com | Yut12878 |