Feature: Login

  @loginSuccess
  Scenario Outline: Login into Clippers fan app
    Given An adult who has personal information generated
    And The user doesn’t have a Clipper account but is registered on the NBA
    And A user opens the Los Angeles Clippers application
    When A user logs in using the user "<user>" and password "<password>"
    Then A user should be able to see the home screen
    Examples:
      | user                         | password   |
      | test.qa.aut.202311@gmail.com | P1a2s3s4.. |