package com.automation.lac.qa.fanapp.mobile.tasks.tickets;

import static com.automation.lac.qa.fanapp.api.tasks.TicketTask.getBookedSeats;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_PRICE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_ROW;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_SECTION;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SUGGESTED_SEAT;
import static com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections.DOWN_TO_UP;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.swipe;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.waitForElementBeClickable;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.waitForElementVisibility;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.waitForListWidgetsIsNotEmpty;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.tickets.SeatsAvailabilityScreen;
import com.automation.lac.qa.fanapp.mobile.screens.tickets.component.SuggestedSeatComponent;

public class SeatsAvailabilityTask extends SeatsAvailabilityScreen {

  /**
   * Select first suggested seat
   *
   * @param index index from the list to select, provide 0 if you select first index and so on.
   */
  public void selectSuggestedSeat(int index) {
    waitForElementVisibility(getSeatSelectionMainHeaderImage(), 20);
    swipe(DOWN_TO_UP, getSeatSelectionMainHeaderImage(), 0.1, 0.5);
    waitForListWidgetsIsNotEmpty(20, getListSuggestedSeat());
    waitForElementBeClickable(20, getListSuggestedSeat().get(index).getWrappedElement());

    SuggestedSeatComponent suggestedSeat = getListSuggestedSeat().get(index);
    getTestContext().set(SELECTED_TICKET_PRICE, getSeatSelectionSeatPrice(suggestedSeat));
    getTestContext().set(SELECTED_TICKET_SECTION,
      getSeatSelectionSeatPlaceSectionName(suggestedSeat));
    getTestContext().set(SELECTED_TICKET_ROW, getSeatSelectionSeatPlaceRowName(suggestedSeat));
    click(suggestedSeat.getWrappedElement(), SUGGESTED_SEAT.getValue());
  }

  /**
   * get Seat Selection Seat Place Section Name
   */
  public String getSeatSelectionSeatPlaceSectionName(SuggestedSeatComponent suggestedSeat) {
    if (isAndroid())
      return suggestedSeat.getSectionSeatPlace().getText().split(" ")[0].trim();
    else
      return suggestedSeat.getSectionSeatPlace().getText().split("Row")[0].split(",")[1].trim();

  }

  /**
   * get Seat Selection Seat Place Row Name
   */
  public String getSeatSelectionSeatPlaceRowName(SuggestedSeatComponent suggestedSeat) {
    if (isAndroid())
      return suggestedSeat.getSectionSeatPlace().getText().split(" ")[3].trim();
    else
      return suggestedSeat.getSectionSeatPlace().getText().split(",")[2].trim();
  }

  /**
   * get Seat Selection Seat Price with currency
   */
  public String getSeatSelectionSeatPriceText(SuggestedSeatComponent suggestedSeat) {
    if (isAndroid())
      return suggestedSeat.getSectionSeatPrice().getText();
    else
      return suggestedSeat.getSectionSeatPrice().getText().split(",")[1].trim();
  }

  /**
   * get Seat Selection Seat Price without currency
   */
  public double getSeatSelectionSeatPrice(SuggestedSeatComponent suggestedSeat) {
    return Double.parseDouble(getSeatSelectionSeatPriceText(suggestedSeat).split("\\$")[1]);
  }

  /**
   * get Seat Selection Seat Price
   */
  public int getSeatSelectionTicketCount(SuggestedSeatComponent suggestedSeat) {
    if (isAndroid())
      return Integer.parseInt(suggestedSeat.getSectionSeatCount().getText().split(" ")[1].trim());
    else
      return Integer.parseInt(
        suggestedSeat.getSectionSeatPrice().getText().split(",")[0].split(" ")[1].trim());
  }

  /**
   * get Seat Selection Seat Price
   */
  public int getBookedSeatsTicketCount(int eventId) {
    return getBookedSeats(eventId);
  }
}