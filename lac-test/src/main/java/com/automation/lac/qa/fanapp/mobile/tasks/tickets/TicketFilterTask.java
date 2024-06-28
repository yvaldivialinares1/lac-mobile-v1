package com.automation.lac.qa.fanapp.mobile.tasks.tickets;

import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TYPE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SEARCH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.TICKET_SEARCH;
import static com.automation.lac.qa.utils.Constants.IS_EVENT_ENABLED;
import static com.automation.lac.qa.utils.Constants.IS_GAME_ENABLED;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;
import static java.util.Objects.isNull;

import com.automation.lac.qa.fanapp.api.models.tickets.TicketResponse;
import com.automation.lac.qa.fanapp.api.tasks.TicketTask;
import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import com.automation.lac.qa.fanapp.mobile.screens.tickets.TicketFilterScreen;
import io.qameta.allure.Allure;
import java.util.List;
import org.testng.SkipException;

public class TicketFilterTask extends TicketFilterScreen {

  /**
   * Search ticket from Search filter on ticket list screen
   *
   * @param ticketName String
   */
  public void searchTicket(String ticketName) {
    waitForElementVisibility(getBtnSearchIcon(), 5);
    click(getBtnSearchIcon(), SEARCH.getValue());
    sendKeys(getTxtTicketSearch(), ticketName, TICKET_SEARCH.getValue());
    hideKeyboard("search");
  }

  /**
   * Change the ticket type if the requested ticket type seat is not available
   * @param clubType Any
   * @param ticketType "GAME", "EVENT"
   */
  public void changeTicketType(String clubType, TicketType ticketType) {
    String msg;
    if (clubType != null)
      msg = "Club Ticket";
    else msg = "ticket";

    if (ticketType.equals(TicketType.EVENT)) {
      Allure.step(String
        .format("No Event %s is available, continuing test for Game %s", msg, msg));
      getTestContext().set(SELECTED_TICKET_TYPE, TicketType.GAME);
    }
    if (ticketType.equals(TicketType.GAME)) {
      Allure.step(String
        .format("No Game %s is available, continuing test for Event %s", msg, msg));
      getTestContext().set(SELECTED_TICKET_TYPE, TicketType.EVENT);
    }
  }

  /**
   * check mission is eligible to run for given ticket type
   * @param ticketType "game", "event"
   * @return true/false
   */
  public boolean checkTestEligibility(TicketType ticketType) {
    List<String> tags = getTestContext().get("tags");
    return (!tags.contains("@event_only") || !ticketType.equals(TicketType.GAME))
      && (!tags.contains("@game_only") || !ticketType.equals(TicketType.EVENT));
  }

  /**
   * get ticker for club ticket or the regular ticket
   * @param clubType any
   * @param ticketType game, event
   * @return ticketResponse
   */
  public TicketResponse getTicket(String clubType, TicketType ticketType) {
    if (clubType != null)
      return TicketTask.getAvailableClubTicket(clubType);
    else  return TicketTask.getAvailableTicket(ticketType);
  }

  /**
   * Get Any eligible ticket to purchase
   * @param clubType any
   * @param ticketType game, event
   * @param alternateTicketType event, game
   * @return ticketResponse
   */
  public TicketResponse getAnyEligibleTicket(String clubType, TicketType ticketType,
                                             TicketType alternateTicketType) {
    TicketResponse ticketResponse;
    ticketResponse = getTicket(clubType,ticketType);
    if (isNull(ticketResponse) && checkTestEligibility(alternateTicketType)) {
      changeTicketType(clubType, ticketType);
      ticketResponse = getTicket(clubType,ticketType);
    }
    return ticketResponse;
  }

  /**
   * Get Any Eligible ticket according to feature availability for the application
   * @param clubType any
   * @param ticketType game, event
   * @return ticketResponse
   */
  public TicketResponse getTicketByFeature(String clubType, TicketType ticketType) {
    TicketType alternateTicketType = ticketType.equals(TicketType.GAME)
      ? TicketType.EVENT : TicketType.GAME;
    if ((IS_GAME_ENABLED && ticketType.equals(TicketType.GAME))
      || (IS_EVENT_ENABLED && ticketType.equals(TicketType.EVENT))
      || (IS_GAME_ENABLED && IS_EVENT_ENABLED)) {
      return getAnyEligibleTicket(clubType, ticketType, alternateTicketType);
    } else if ((!IS_GAME_ENABLED && ticketType.equals(TicketType.GAME))
      || (!IS_EVENT_ENABLED && ticketType.equals(TicketType.EVENT))) {
      if (!checkTestEligibility(alternateTicketType))
        throw new SkipException(String.format("%s Feature is not enabled for this Env,"
          + " This test is eligible only for %s", ticketType.name(), ticketType.name()));
      Allure.step(String.format("%s Feature is not enabled for this Env, continuing test for %s",
        TicketType.GAME, alternateTicketType));
      getTestContext().set(SELECTED_TICKET_TYPE, alternateTicketType);
      return getTicket(clubType, ticketType);
    }
    return null;
  }
}