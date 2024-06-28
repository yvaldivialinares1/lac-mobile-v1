package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TIMESTAMP;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PARKING_COUNT_GARAGE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.ROW_SECTION_DETAILS;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipe;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.RIGHT_TO_LEFT;

import com.automation.lac.qa.fanapp.mobile.screens.purchase.ManageMyTicketsScreen;
import com.automation.lac.qa.fanapp.mobile.utils.TicketScreenUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageMyTicketsQuestions extends ManageMyTicketsScreen {

  /**
   * This method validates purchased ticket datetime, row and section on manage my ticket screen
   */
  public void validatePurchasedTicket() {
    List<String> expectedRowSectionList =
      getTestContext().getOrDefault(ROW_SECTION_DETAILS.name(), new ArrayList<>());
    int parkingTicketCount = getTestContext().getOrDefault(SELECTED_PARKING_TICKET_COUNT, 0);
    Map<String, Integer> parkingGarageMap =
      getTestContext().getOrDefault(PARKING_COUNT_GARAGE.name(), new HashMap<>());
    int eventGameTicketCount = expectedRowSectionList.size();
    String attribute = "content-desc";
    validateEventGameTickets(expectedRowSectionList, eventGameTicketCount, attribute);
    validateParkingTickets(eventGameTicketCount, parkingTicketCount, parkingGarageMap, attribute);
  }

  private void validateEventGameTickets(List<String> expectedRowSectionList,
                                        int eventGameTicketCount, String attribute) {
    if (isAndroid()) {
      for (int i = 0; i < eventGameTicketCount; i++) {
        String ticketDatetime = getTicketDateTimes(i)
          .getAttribute(attribute);
        String ticketRowSection = getTicketRowSection(i)
          .getAttribute(attribute).toUpperCase();
        getSoftAssert().assertEquals(ticketDatetime, TicketScreenUtils
            .convertTimestampFromApiToTicketScreenDate(getTestContext()
              .get(SELECTED_TICKET_TIMESTAMP)),
          String.format("Date Time should be correct for '%s'", ticketRowSection));
        boolean isRemoved = expectedRowSectionList.remove(ticketRowSection);
        getSoftAssert().assertTrue(isRemoved,
          String.format("Ticket Row section details Should match for '%s'", ticketRowSection));
        swipe(RIGHT_TO_LEFT, getTicket(i), 0, 1);
      }
      getSoftAssert().assertTrue(expectedRowSectionList.isEmpty(),
        "All Tickets Row section details Should match");
    } else {
      for (int i = 0; i < eventGameTicketCount; i++) {
        String ticketDetails = getTicket(i).getText();
        getSoftAssert().assertTrue(ticketDetails.contains(TicketScreenUtils
            .convertTimestampFromApiToTicketScreenDate(getTestContext()
              .get(SELECTED_TICKET_TIMESTAMP))),
          String.format("Date Time should be correct for '%s'", ticketDetails));
        getSoftAssert().assertTrue(ticketDetails.contains(
            expectedRowSectionList.get(i).replaceAll("\\|", "/")),
          String.format("Row Section details should be correct for '%s'", ticketDetails));
        swipe(RIGHT_TO_LEFT, getTicket(i), 0, 1);
      }
    }
  }

  private void validateParkingTickets(int eventGameTicketCount, int parkingTicketCount,
                                      Map<String, Integer> parkingGarageMap, String attribute) {
    if (isAndroid()) {
      for (int i = eventGameTicketCount; i < eventGameTicketCount + parkingTicketCount; i++) {
        String ticketDatetime = getTicketDateTimes(i)
          .getAttribute(attribute);
        String garageName = getTicketGarageName(i).getAttribute("text");
        getSoftAssert().assertEquals(ticketDatetime, TicketScreenUtils
            .convertTimestampFromApiToTicketScreenDate(getTestContext()
              .get(SELECTED_TICKET_TIMESTAMP)),
          String.format("Date Time should be correct for '%s' garage", garageName));

        if (parkingGarageMap.containsKey(garageName) && parkingGarageMap.get(garageName) >= 1)
          parkingGarageMap.put(garageName, parkingGarageMap.get(garageName) - 1);
        swipe(RIGHT_TO_LEFT, getTicket(i), 0, 1);
      }
      parkingGarageMap.forEach((k, v) -> getSoftAssert().assertTrue(v.equals(0),
        String.format("Parking count should match for '%s' garage", k)));
    } else {
      for (int i = eventGameTicketCount; i < eventGameTicketCount + parkingTicketCount; i++) {
        String ticketDetails = getTicket(i).getText();
        String[] ticketDetailsSplitArray = ticketDetails.split(",");
        String garageName = ticketDetailsSplitArray[ticketDetailsSplitArray.length - 1].trim();
        getSoftAssert().assertTrue(ticketDetails.contains(TicketScreenUtils
            .convertTimestampFromApiToTicketScreenDate(getTestContext()
              .get(SELECTED_TICKET_TIMESTAMP))),
          String.format("Date Time should be correct for '%s' garage", garageName));
        if (parkingGarageMap.containsKey(garageName) && parkingGarageMap.get(garageName) >= 1)
          parkingGarageMap.put(garageName, parkingGarageMap.get(garageName) - 1);
        swipe(RIGHT_TO_LEFT, getTicket(i), 0, 1);
      }
      parkingGarageMap.forEach((k, v) -> getSoftAssert().assertTrue(v.equals(0),
        String.format("Parking count should match for '%s' garage", k)));
    }
  }
}