package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_NAME;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TIMESTAMP;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.enums.ReminderNames;
import com.automation.lac.qa.fanapp.mobile.screens.myprofile.MyLoggedProfileScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyLoggedProfileTask;
import com.automation.lac.qa.fanapp.mobile.utils.TicketScreenUtils;
import com.automation.lac.qa.utils.mobile.WaitActions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class MyLoggedProfileQuestions extends MyLoggedProfileScreen {

  private final MyLoggedProfileTask myProfileLoggedTasks = new MyLoggedProfileTask();

  private static final int BANNER_WAIT_TIME = 5;

  /**
   * This method validates if the reminder card is not visible
   *
   * @param reminderName string to get reminder card enum
   */
  public void validateReminderVisibility(String reminderName, String condition) {
    String[] reminders = reminderName.split(", ");
    List<String> remindersList = myProfileLoggedTasks.lookTheReminderCard();
    List<String> remindersListToSearch = new ArrayList<>(Arrays.asList(reminders));

    log.info("reminders to find {}, reminders found {}", remindersListToSearch.size(),
      remindersList.size());
    for (String reminder : remindersListToSearch) {
      ReminderNames reminderEnum = ReminderNames.getReminderCardEnum(reminder);
      assert reminderEnum != null;
      if (condition == null) {
        getSoftAssert().assertTrue(remindersList.contains(reminderEnum.getTitle()),
          String.format("reminder %s is displayed", reminderEnum.getName()));
      } else {
        getSoftAssert().assertFalse(remindersList.contains(reminderEnum.getTitle()),
          String.format("reminder %s is not displayed", reminderEnum.getName()));
      }
    }
  }

  /**
   * Method to validate purchased ticket on screen
   */
  public void checkPurchasedTicket() {
    boolean isPanelVisible = WaitActions.isTheElementVisible(getPnlYourNextEvent(), 10);

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

  /**
   * Check if the profile is logged
   */
  public boolean isProfileLogged() {
    return WaitActions.elementIsDisplayed(getBtnMyAccountSettings());
  }

  /**
   * @param value expected text value to be contained in the banner message
   */
  public void validateBannerMessage(String value) {
    boolean isBannerShown =
      WaitActions.isTheElementVisible(getLblBannerMessage(), BANNER_WAIT_TIME);
    getSoftAssert().assertTrue(isBannerShown, "Banner is displayed.");
    if (isBannerShown) {
      if (isAndroid()) {
        getSoftAssert().assertEquals(getLblBannerMessage().getAttribute("content-desc"),
          value,
          String.format("The banner has the text '%s'",
            getLblBannerMessage().getAttribute("content-desc")));
      } else {
        getSoftAssert().assertEquals(getLblBannerMessage().getText(),
          value,
          String.format("The banner has the text '%s'", getLblBannerMessage().getText()));
      }
    }
  }
}
