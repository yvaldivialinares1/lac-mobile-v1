package com.automation.lac.qa.fanapp.mobile.tasks.tickets;

import static com.automation.lac.qa.fanapp.api.tasks.TicketTask.getBookedSeats;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_PRICE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_ROW;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_SECTION;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SUGGESTED_SEAT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeElementToTheBorder;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.TOP_PAGE;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementDisappear;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.mobile.screens.tickets.SeatsAvailabilityScreen;
import com.automation.lac.qa.fanapp.mobile.screens.tickets.component.SuggestedSeatComponent;
import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.utils.mobile.WaitActions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SeatsAvailabilityTask extends SeatsAvailabilityScreen {

  private static final String ISOLATED_SEAT = "\"You can’t leave a single seat isolated."
    + " Change your selection to proceed.\"\nBackend bug CA-44232:";

  /**
   * Select first suggested seat
   *
   * @param index index from the list to select, provide 0 if you select first index and so on.
   */
  public void selectSuggestedSeat(int index) {
    waitForElementVisibility(getSeatSelectionMainHeaderImage(), 20);
    waitElementDisappear(getLblInformationBanner(), 5);
    swipeElementToTheBorder(TOP_PAGE, getSeatSelectionMainHeaderImage());

    SuggestedSeatComponent suggestedSeat = getListSuggestedSeat().get(index);
    getTestContext().set(SELECTED_TICKET_PRICE, getSeatSelectionSeatPrice(suggestedSeat));
    getTestContext().set(SELECTED_TICKET_SECTION,
      getSeatSelectionSeatPlaceSectionName(suggestedSeat));
    getTestContext().set(SELECTED_TICKET_ROW, getSeatSelectionSeatPlaceRowName(suggestedSeat));
    click(suggestedSeat.getWrappedElement(), SUGGESTED_SEAT.getValue());

    //TODO Verify CustomException when https://laclippers.atlassian.net/browse/CA-44232 is fixed
    if (WaitActions.isTheElementVisible(getLblInformationBanner(), 5)) {
      throw new CustomException(ISOLATED_SEAT);
    }
  }

  /**
   * get Seat Selection Seat Place Section Name
   */
  public String getSeatSelectionSeatPlaceSectionName(SuggestedSeatComponent suggestedSeat) {
    if (isAndroid()) {
      String[] seatInfo =
        suggestedSeat.getSectionSeatInfo().getAttribute("content-desc").split("•")[0].trim()
          .split(" ");
      return seatInfo[seatInfo.length - 1];
    } else
      return suggestedSeat.getSectionSeatInfo().getText().split("Row")[0].split(",")[1].trim();

  }

  /**
   * get Seat Selection Seat Place Row Name
   */
  public String getSeatSelectionSeatPlaceRowName(SuggestedSeatComponent suggestedSeat) {
    if (isAndroid())
      return suggestedSeat.getSectionSeatInfo().getAttribute("content-desc").split("•")[1].trim()
        .split(" ")[1];
    else
      return suggestedSeat.getSectionSeatInfo().getText().split(",")[2].trim();
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
   * get the actual Booked seat count
   */
  public int getBookedSeatsTicketCount(UserInfo userInfo, int eventId) {
    return getBookedSeats(userInfo, eventId);
  }
}