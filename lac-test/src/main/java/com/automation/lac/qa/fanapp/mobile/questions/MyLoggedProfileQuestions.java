package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_NAME;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TIMESTAMP;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.REMINDER_CARD_VISIBILITY;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.waitForElementVisibility;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.enums.ReminderNames;
import com.automation.lac.qa.fanapp.mobile.screens.myprofile.MyLoggedProfileScreen;
import com.automation.lac.qa.fanapp.mobile.utils.TicketScreenUtils;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class MyLoggedProfileQuestions extends MyLoggedProfileScreen {

  /**
   * This method validates if the reminder card is visible
   *
   * @param reminderName string to get reminder card enum
   */
  public void validateReminderIsVisible(String reminderName) {
    ReminderNames reminderEnum = ReminderNames.getReminderCardEnum(reminderName);

    HashMap<ReminderNames, Boolean> reminderNamesVisibility =
      getTestContext().get(REMINDER_CARD_VISIBILITY.name());

    getSoftAssert()
      .assertTrue(reminderNamesVisibility.get(reminderEnum),
        String.format("Checking %s reminder card is displayed", reminderEnum.getName()));
  }

  /**
   * This method validates if the reminder card is not visible
   *
   * @param reminderName string to get reminder card enum
   */
  public void validateReminderVisibility(String reminderName, String condition) {
    ReminderNames reminderEnum = ReminderNames.getReminderCardEnum(reminderName);

    HashMap<ReminderNames, Boolean> reminderNamesVisibility =
      getTestContext().get(REMINDER_CARD_VISIBILITY.name());

    if (condition != null && condition.trim().equals("not")) {
      getSoftAssert().assertFalse(reminderNamesVisibility.get(reminderEnum),
        String.format("Checking %s reminder card is not displayed", reminderEnum.getName()));
    } else {
      getSoftAssert().assertTrue(reminderNamesVisibility.get(reminderEnum),
        String.format("Checking %s reminder card is displayed", reminderEnum.getName()));
    }
  }

  /**
   * Method to validate purchased ticket on screen
   */
  public void checkPurchasedTicket() {
    boolean isPanelVisible = waitForElementVisibility(getPnlYourNextEvent(), 10);

    getSoftAssert()
      .assertTrue(isPanelVisible, "Checking next event is displayed");

    if (isPanelVisible) {

      int eventDate = getTestContext().get(SELECTED_TICKET_TIMESTAMP);
      int numberOfTickets = getTestContext().get(SELECTED_TICKET_COUNT);
      String pluralForTickets = numberOfTickets == 1 ? "" : "s";

      String strEventDateConverted = TicketScreenUtils
        .convertTimestampFromApiToTicketScreenDate(eventDate);

      String strEventDate = "";
      String[] dateSplit = strEventDateConverted.split(",");
      if (strEventDateConverted.contains("TODAY")) {
        strEventDate = String.format("Today, %s", dateSplit[dateSplit.length - 1]);
      } else {
        String eventDay = StringUtils.capitalize(dateSplit[0].toLowerCase());
        String eventMonth = StringUtils.capitalize(dateSplit[1].trim().split(" ")[0].toLowerCase());
        String strEventDateProvisional = strEventDateConverted.replace(dateSplit[0], eventDay);
        strEventDate =
          strEventDateProvisional.replace(dateSplit[1].trim().split(" ")[0], eventMonth);
      }

      String eventInfo = getPnlYourNextEvent().getAttribute(!isAndroid() ? "label" : "text");
      String eventName = getTestContext().get(SELECTED_TICKET_NAME);
      String strNumberOfTickets = String.format("%s ticket%s", numberOfTickets, pluralForTickets);

      getSoftAssert().assertTrue(eventInfo.contains(eventName),
        "Checking event name is the same as the purchased, Expected: " + eventName);

      getSoftAssert().assertTrue(eventInfo.contains(strEventDate),
        "Checking event day is the same as the purchased, Expected: " + strEventDate);

      getSoftAssert().assertTrue(eventInfo.contains(strNumberOfTickets),
        "Checking number of tickets is the same as the purchased, Expected: " + strNumberOfTickets);
    }
  }
}
