package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_AVAILABILITY_RESPONSE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_COUNT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.enums.ParkingType;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.ParkingPassTask;
import io.cucumber.java.en.And;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class ParkingPassStepsDefinition {
  private final ParkingPassTask parkingPassTask = new ParkingPassTask();

  /**
   * User selects parking tickets
   */
  @And("the user selects (\\d+) parking tickets( from parking add-on)?$")
  public void theUserSelectsParkingTickets(int parkingTicketCount, String parkingAddon) {
    getTestContext().set(SELECTED_PARKING_TICKET_COUNT, parkingTicketCount);
    Map<ParkingType, Map<String, Integer>> availabilityMapWithTypeOfParkingAsKey =
      parkingPassTask.getParkingAvailabilityAsTypeOfParkAsKeyMap(
        getTestContext().get(SELECTED_PARKING_TICKET_AVAILABILITY_RESPONSE));
    if (StringUtils.isEmpty(parkingAddon)) {
      parkingPassTask.waitForParkingDetailsScreenToLoad();
    }
    parkingPassTask.selectParkingTickets(availabilityMapWithTypeOfParkingAsKey, parkingTicketCount);
  }
}
