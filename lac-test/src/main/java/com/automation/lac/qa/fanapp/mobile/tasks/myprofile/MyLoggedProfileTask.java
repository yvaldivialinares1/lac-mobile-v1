package com.automation.lac.qa.fanapp.mobile.tasks.myprofile;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_IDENTITY_PASS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_YOUR_CARDS_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.GAME_FACE_ID_REMINDER;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_ACCOUNT_SETTINGS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_PAYMENTS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_VEHICLES;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.VERIFY_AGE_REMINDER;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.REMINDER_CARD_VISIBILITY;
import static com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections.HORIZONTAL;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.performSwipeOverAnElement;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForReminderCardToAppear;

import com.automation.lac.qa.fanapp.mobile.enums.ReminderNames;
import com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections;
import com.automation.lac.qa.fanapp.mobile.screens.myprofile.MyLoggedProfileScreen;
import com.automation.lac.qa.fanapp.mobile.utils.DeviceActions;
import com.automation.lac.qa.utils.mobile.WaitActions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyLoggedProfileTask extends MyLoggedProfileScreen {

  public void clickOnGameFaceIdReminderLink() {
    click(getLnkGameFaceIdReminder(), GAME_FACE_ID_REMINDER.getValue());
  }

  public void clickOnVerifyAgeReminderLink() {
    click(getLnkVerifyAgeReminder(), VERIFY_AGE_REMINDER.getValue());
  }

  public void clickOnAddYourCardsReminderLink() {
    click(getLnkAddYourCards(), ADD_YOUR_CARDS_ACCOUNT.getValue());
  }

  public void clickOnAddIdentityPassReminderLink() {
    click(getLnkAddIdentityPass(), ADD_IDENTITY_PASS.getValue());
  }

  public void clickBackOnMyProfile() {
    click(getBtnBack(), BACK.getValue());
  }

  private void goToReminderCardAndroid(ReminderNames reminderName) {
    boolean[] isFound = {false};
    // iterate to the right
    isFound[0] = findReminderCardAndroid(reminderName, 80, 10);
    // iterate to the left
    if (!isFound[0]) {
      isFound[0] = findReminderCardAndroid(reminderName, 20, 90);
    }

    // save the visibility status
    Map<ReminderNames, Boolean> reminderCardsVisibility = new HashMap<>();
    reminderCardsVisibility.put(reminderName, isFound[0]);
    getTestContext().set(REMINDER_CARD_VISIBILITY.name(), reminderCardsVisibility);
  }

  private void geToReminderCardIos(ReminderNames reminderName) {
    boolean[] isFound = {false};

    isFound[0] = findReminderCardIOs(reminderName);

    Map<ReminderNames, Boolean> reminderCardsVisibility = new HashMap<>();
    reminderCardsVisibility.put(reminderName, isFound[0]);
    getTestContext().set(REMINDER_CARD_VISIBILITY.name(), reminderCardsVisibility);
  }

  /**
   * This method swipes over reminder cards until reminderNames is found
   *
   * @param reminderName String to search in ReminderCard enum, based on name attribute
   */
  public void goToReminderCard(String reminderName) {
    if (isAndroid()) {
      goToReminderCardAndroid(ReminderNames.getReminderCardEnum(reminderName));
    } else {
      geToReminderCardIos(ReminderNames.getReminderCardEnum(reminderName));
    }
  }

  /**
   * This method clicks on a reminder card
   *
   * @param reminderName string to find a ReminderNames enum
   */
  public void clickOnReminder(String reminderName) {
    ReminderNames reminderEnum = ReminderNames.getReminderCardEnum(reminderName);
    if (isAndroid()) {
      switch (reminderEnum) {
        case GAME_FACE_ID -> clickOnGameFaceIdReminderLink();
        case AGE_VERIFICATION -> clickOnVerifyAgeReminderLink();
        case ADD_CARDS -> clickOnAddYourCardsReminderLink();
        case IDENTITY_PASS -> clickOnAddIdentityPassReminderLink();
        default -> {
          // do nothing
        }
      }
    } else {
      click(getLstReminderCards().stream().filter(card ->
          card.getAttribute("label").toLowerCase()
            .contains(reminderEnum.getTitle().toLowerCase())).findFirst().orElseThrow(),
        reminderName + " reminder card");
    }
  }

  /**
   * This method go to my account settings from MyProfile
   */
  public void goToMyAccountSettingsFromMyProfile() {
    DeviceActions.verticallyScrollToElement(getBtnMyAccountSettings(),
      SwipeDirections.DOWN_TO_UP, 5, 30);
    click(getBtnMyAccountSettings(), MY_ACCOUNT_SETTINGS.getValue());
  }

  private WebElement getTheVisibleReminderCardIOs() {
    List<WebElement> lstReminderCards = getLstReminderCards();
    WebElement focusedCard = lstReminderCards.get(0);
    // get the card on screen
    for (WebElement lstReminderCard : lstReminderCards) {
      if (lstReminderCard.getSize().getWidth() < focusedCard.getSize().getWidth()) {
        focusedCard = lstReminderCard;
      }
    }
    return focusedCard;
  }

  private boolean findReminderCardAndroid(ReminderNames reminderName, int startX, int endX) {
    boolean[] isFound = {false};
    boolean[] shouldStop = {false};
    // define the amount of cards on screen
    WebElement firstReminderCard = getLblCardNumber();
    String cardNumberDescription = firstReminderCard.getAttribute("content-desc");
    int numberOfCards = Integer.parseInt(cardNumberDescription.substring(
      cardNumberDescription.length() - 1));
    // define device width size
    int deviceWidthSize = getDriver().manage().window().getSize().getWidth();
    // define the card in the middle
    int count = 0;
    if (getLstReminderCards().size() >= 2
      && getLstReminderCards().get(0).getSize().getWidth() < deviceWidthSize / 2) {
      count = 1;
    }
    for (int i = 1; i <= numberOfCards; i++) {
      WebElement reminderCard = getLstReminderCards().get(count);
      if (WaitActions.waitForElementVisibility(
        By.xpath(String.format(getXpathCard(), reminderName.getTitle())), 1)) {
        isFound[0] = true;
        break;
      } else {
        if (!shouldStop[0]) {
          performSwipeOverAnElement(
            reminderCard, HORIZONTAL, startX, endX, "reminder card");
          waitForReminderCardToAppear(1);
        }
        if (reminderCard.getSize().getWidth() > deviceWidthSize / 2) {
          shouldStop[0] = true;
        }
      }
      if (numberOfCards > 1) {
        count = 1;
      }
    }
    return isFound[0];
  }

  private boolean findReminderCardIOs(ReminderNames reminderName) {
    List<WebElement> lstReminderCards = getLstReminderCards();
    boolean[] isFound = {false};

    WebElement focusedCard = getTheVisibleReminderCardIOs();
    int focusedCardPosition = 0;
    int cardToBeFoundPosition = 0;

    for (int i = 0; i < lstReminderCards.size(); i++) {
      if (lstReminderCards.get(i).equals(focusedCard)) {
        focusedCardPosition = i;
      }
      if (lstReminderCards.get(i).getAttribute("label").toLowerCase().contains(
        reminderName.getTitle().toLowerCase())) {
        cardToBeFoundPosition = i;
        isFound[0] = true;
      }
    }

    if (focusedCardPosition >= cardToBeFoundPosition && isFound[0]) {
      for (int i = 0; i < Math.abs(cardToBeFoundPosition - focusedCardPosition); i++) {
        performSwipeOverAnElement(
          focusedCard, HORIZONTAL, 20, 80, "reminder card to left");
        waitForReminderCardToAppear(1);
      }
    } else {
      if (isFound[0]) {
        for (int i = 0; i < Math.abs(cardToBeFoundPosition - focusedCardPosition); i++) {
          performSwipeOverAnElement(
            focusedCard, HORIZONTAL, 80, 20, "reminder card to right");
          waitForReminderCardToAppear(1);
        }
      }
    }
    return isFound[0];
  }

  public void goToMyVehiclesFromMyProfile() {
    DeviceActions.verticallyScrollToElement(getBtnMyVehicles(), SwipeDirections.DOWN_TO_UP, 1, 50);
    click(getBtnMyVehicles(), MY_VEHICLES.getValue());
  }

  public void goToMyPaymentsFromMyProfile() {
    DeviceActions.verticallyScrollToElement(getBtnMyPayments(), SwipeDirections.DOWN_TO_UP,1,  30);
    click(getBtnMyPayments(), MY_PAYMENTS.getValue());
  }
}