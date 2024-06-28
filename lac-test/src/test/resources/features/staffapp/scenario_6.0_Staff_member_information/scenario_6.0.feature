Feature: Staff Incidents

  This scenario tests the following functionalities:
  - Staff member is logged into the app
  - Staff member navigate to  create game face id
  - Staff member navigates to My Identity Pass
  - Staff member navigates to My Vehicle screen
  - Staff member add/remove/edit vehicle details

  Scenario: LAC Staff member information
    Given a staff with a 2 registered vehicle data
    And an user logs in the application using staff credentials
    And the user starts creating game face id
    And the user selects 'DO IT LATER' option while creating game face id
    Then the user is navigated to a home screen
    When the user selects staff member profile option
    #Then user see Staff member basic profile details
    And the user selects the "MY_IDENTITY_PASS" option from staff member profile menu
    Then the user sees the QR code image and informative message
    When the user taps the button to close the fan vehicle list
    And the user selects the "MY_VEHICLE" option from staff member profile menu
    Then the user sees staff profile vehicles list is properly displayed
    When the user selects the first vehicle from the staff vehicles list
    Then the user sees the staff member's vehicle details properly displayed
    And the user removes fan vehicle note
    Then the user sees the expected banner message 'SUCCESS_VEHICLE_REMOVED' displayed
    Then the user sees staff profile vehicles list is properly displayed

#    When user select random vehicle from the list
#    And user edit staff member vehicle details
#    When user update the License Plate detail with already present vehicle license plate detail
#    Then user won't be able to edit vehicle details with the repeated vehicle details
#    When user update the License Plate details with correct details
#    And staff member apply the changes
#    Then staff member see the vehicle data is updated in the staff member vehicle list
#    When staff member select ADD MY VEHICLE option from the staff member vehicle list
#    And staff member add the License Plate details with already present vehicle license plate detail
#    Then staff member won't be able to add vehicle with the repeated vehicle details
#    And staff member enter the correct License Plate details
#    And staff member add the vehicle information
#    Then staff member see the new vehicle is properly added to a staff member a vehicles list
#    When staff member close My vehicles side bar
#    And staff member logout from a staff member profile
#    Then staff member is navigated to a Welcome screen