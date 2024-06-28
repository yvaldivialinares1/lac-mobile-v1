Feature: MagnifAi test execution

  @magnifai
  Scenario: Verify magnifAi integration
    Given An adult who has personal information generated
    And The user has a Clipper account
    And The user opens the Los Angeles Clippers application and try to create an account
      | currentCreateAccountButton.png |
      | AllButtons.png                 |
    Then The user should be able to see the create account screen
      | currentCreateAccountEducationalScreen.png |