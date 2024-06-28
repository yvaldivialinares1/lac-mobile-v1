@user_creation
Feature: User Creation

  Scenario: On demand user creation
    Given there are a number and type of users who have personal information generated
    When the users completes all the flow according to the given parameters
    Then the users flow is completed