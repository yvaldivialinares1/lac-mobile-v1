package com.automation.lac.qa.staffapp.api.tasks;

import static com.automation.lac.qa.staffapp.constants.ContextConstants.DEVICE_ID;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.GARAGE_CATEGORY;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.INTUIT_DOME_ACCOUNT_1;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.LICENCE_PLATE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.enums.GarageCategoryType;
import com.automation.lac.qa.staffapp.api.models.accesscontrol.LanesResponse;
import com.automation.lac.qa.staffapp.api.models.accesscontrol.ParkingAccessValidationRequest;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.services.accesscontrol.ParkingAccessControlService;
import com.automation.lac.qa.staffapp.api.utils.FileUtil;
import com.automation.lac.qa.staffapp.mobile.enums.QueueEventType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParkingAccessControlApiTask {

  /**
   * Return the parking lanes.
   *
   * @param garage String indicating the garage for which we get the actual lines.
   * @return LanesResponse
   */
  public LanesResponse getParkingLines(String garage) {
    return ParkingAccessControlService.getParkingLines(garage);
  }

  /**
   * Generate the specific parking access validation notification.
   *
   * @param queueEventType QueueEventType indicating the type of event.
   */
  public void createParkingAccessValidationEvent(QueueEventType queueEventType) {
    String licensePlatePhoto = FileUtil.getBase64DecodedString(
      "src/test/resources/test_data/staffapp/file/parking_access_event.png");

    final String deviceId = getTestContext().get(DEVICE_ID);
    final GarageCategoryType garageCategory = getTestContext().get(GARAGE_CATEGORY);
    final LicensePlateDto licensePlate = getTestContext().get(LICENCE_PLATE);
    IntuitDomeAccountDto account;

    int confidence;
    switch (queueEventType) {
      case PLATE_NOT_PROPERLY_READ:
        confidence = 50;
        break;
      case INCORRECT_GARAGE:
        account = getTestContext().get(INTUIT_DOME_ACCOUNT_1);
        confidence = 90;
        String tmUserId = TicketingApiTask.generateTicketMasterUserId(account);
        IntuitDomeAccountApiTask.addTicketingIdToIntuitDomAccount(account.getId(), tmUserId);
        TicketingApiTask.setUpTicketingMockResponse(
          tmUserId,
          "FAILURE",
          "INCORRECT GARAGE",
          garageCategory.getParking(),
          "PARKING");
        break;
      default:
        confidence = 90;
    }
    sendParkingAccessValidationEvent(
      licensePlate, garageCategory, deviceId, confidence, licensePlatePhoto);
  }

  /**
   * send Parking Access Validation Event.
   *
   * @param licensePlate      LicensePlateDto indicating fan account data.
   * @param garageCategory    GarageCategoryType indicating parking garage.
   * @param deviceId          String indicating id of queue device.
   * @param confidence        String indicating plate visibility.
   * @param licensePlatePhoto String indicating base 64 decoded string of car photo.
   */
  private void sendParkingAccessValidationEvent(LicensePlateDto licensePlate,
                                                GarageCategoryType garageCategory,
                                                String deviceId, int confidence,
                                                String licensePlatePhoto) {
    ParkingAccessValidationRequest request = ParkingAccessValidationRequest.builder()
      .licensePlate(licensePlate.getLicensePlate())
      .state(licensePlate.getState())
      .garage(garageCategory.getGarage())
      .laneId(deviceId)
      .color(licensePlate.getColor())
      .make(licensePlate.getMake())
      .confidence(confidence)
      .eventType("ENTRY")
      .transactionId("123456789")
      .licensePlateFrontPhoto(licensePlatePhoto)
      .licensePlateBackPhoto(licensePlatePhoto)
      .vehicleFrontPhoto(licensePlatePhoto)
      .vehicleBackPhoto(licensePlatePhoto)
      .build();
    ParkingAccessControlService.createParkingAccessValidationEvent(request);
  }
}
