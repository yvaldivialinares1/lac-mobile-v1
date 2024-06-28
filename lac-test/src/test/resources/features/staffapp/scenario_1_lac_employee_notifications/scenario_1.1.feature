Feature: Notifications management

  This scenario tests the following functionalities:
  - Staff member is logged into the app
  - Have read/unread notifications
  - Open the notification module
  - User can mark as read the unread
  - User can filter by categories

  @allure.label.owner=DiegoGarcia
  Scenario Outline: LAC employee has read and unread notifications
    Given an user logs in the application using staff credentials and skip game face ID
    And the user has read and unread notifications
    When the user goes to notifications module
    Then the user sees the notification module with the notifications
    When the user taps on unread notifications
    Then the notifications are mark as read
    When the user filters the notification by the category "<category>"
    Then the user should see only the notifications by category "<category>"
    When the user removes the applied filter
    Then the user should see all the notifications read

    Examples:
      | category |
      | Sales    |
