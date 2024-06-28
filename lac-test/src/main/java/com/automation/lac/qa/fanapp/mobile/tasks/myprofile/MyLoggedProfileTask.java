package com.automation.lac.qa.fanapp.mobile.tasks.myprofile;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_IDENTITY_PASS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_YOUR_CARDS_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.GAME_FACE_ID_REMINDER;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.LOG_OUT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_ACCOUNT_SETTINGS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_PAYMENTS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_VEHICLES;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.VERIFY_AGE_REMINDER;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.REMINDER_CARD_VISIBILITY;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.performSwipeOverAnElement;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeActions.isTheSameDom;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeElementToTheBorder;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.DOWN_TO_UP;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.HORIZONTAL;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.LEFT_TO_RIGHT;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.RIGHT_TO_LEFT;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForProcessToFinish;

import com.automation.lac.qa.fanapp.mobile.enums.ReminderNames;
import com.automation.lac.qa.fanapp.mobile.screens.myprofile.MyLoggedProfileScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.modals.ModalLogOutTasks;
import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.utils.mobile.WaitActions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLoggedProfileTask extends MyLoggedProfileScreen {

  private final ModalLogOutTasks modalLogOutTasks = new ModalLogOutTasks();

  private static final Logger log = LoggerFactory.getLogger(MyLoggedProfileTask.class);

  public void clickOnGameFaceIdReminderLink() {
    click(getLnkGameFaceIdReminder(), GAME_FACE_ID_REMINDER.getValue());
  }

  public void clickOnVerifyAgeReminderLink() {
    click(getLnkVerifyAgeReminder(), VERIFY_AGE_REMINDER.getValue());
  }

  public void clickOnAddYourCardsReminderLink() {
    waitForElementVisibility(getLnkAddYourCards(), 5);
    click(getLnkAddYourCards(), ADD_YOUR_CARDS_ACCOUNT.getValue());
  }

  public void clickOnAddIdentityPassReminderLink() {
    click(getLnkAddIdentityPass(), ADD_IDENTITY_PASS.getValue());
  }

  public void clickBackOnMyProfile() {
    click(getBtnBack(), BACK.getValue());
  }

  private void goToReminderCardIos(ReminderNames reminderName) {
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
      selectTheReminderCardInAndroid(reminderName);
    } else {
      goToReminderCardIos(ReminderNames.getReminderCardEnum(reminderName));
    }
  }

  /**
   * @return list
   */
  public List<String> lookTheReminderCard() {
    List<String> remindersVisible = new ArrayList<>();
    if (isAndroid()) {
      remindersVisible = searchReminderCardsOnAndroid();
    } else {
      //get reminder titles
      List<String> reminderTitleNames = ReminderNames.getTitlesReminderNames();
      //get reminders iOS
      List<WebElement> lstReminderCards = getLstReminderCards();
      for (WebElement card : lstReminderCards) {
        for (String reminderTitle : reminderTitleNames) {
          if (card.getAttribute("name").contains(reminderTitle)) {
            remindersVisible.add(reminderTitle);
          }
        }
      }
    }
    return remindersVisible;
  }

  private List<String> searchReminderCardsOnAndroid() {
    int loops = 0;
    List<String> remindersVisible = new ArrayList<>();
    String previousPageSource;
    do {
      loops++;
      log.info("reminder card name {}", getReminderCardsText().getText());
      remindersVisible.add(getReminderCardsText().getText());
      previousPageSource = getDriver().getPageSource();
      swipeElementToTheBorder(RIGHT_TO_LEFT, getReminderCardsView());
      waitForProcessToFinish(1);
    } while (!isTheSameDom(previousPageSource));
    for (int i = 0; i < loops; i++) {
      swipeElementToTheBorder(LEFT_TO_RIGHT, getReminderCardsView());
    }
    return remindersVisible.stream().distinct().collect(Collectors.toList());
  }

  private void selectTheReminderCardInAndroid(String reminderCard) {
    waitForElementVisibility(getReminderCardPosition(), 5);
    String[] position = getReminderCardPosition().getAttribute("content-desc").split(" ");
    ReminderNames reminderEnum = ReminderNames.getReminderCardEnum(reminderCard);
    String finalXpath = String.format(xpathCard, reminderEnum.getTitle());
    if (position[2].equals(position[4])) {
      swipeUntilFindElement(finalXpath, LEFT_TO_RIGHT, getReminderCardsView());
    } else {
      swipeUntilFindElement(finalXpath, RIGHT_TO_LEFT, getReminderCardsView());
    }
    WaitActions.waitForProcessToFinish(1);
  }

  /**
   * This method clicks on a reminder card
   *
   * @param reminderName string to find a ReminderNames enum
   */
  public void clickOnReminder(String reminderName) {
    ReminderNames reminderEnum = ReminderNames.getReminderCardEnum(reminderName);
    if (isAndroid()) {
      switch (Objects.requireNonNull(reminderEnum)) {
        case GAME_FACE_ID -> clickOnGameFaceIdReminderLink();
        case AGE_VERIFICATION -> clickOnVerifyAgeReminderLink();
        case ADD_CARDS -> clickOnAddYourCardsReminderLink();
        case IDENTITY_PASS -> clickOnAddIdentityPassReminderLink();
        default -> throw new CustomException("The option " + reminderEnum + " is not valid.");
      }
    } else {
      click(getLstReminderCards().stream().filter(card ->
          card.getAttribute("label").toLowerCase()
            .contains(reminderEnum.getTitle().toLowerCase())).findFirst().orElseThrow(),
        reminderName + " reminder card");
    }
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
        waitForProcessToFinish(1);
      }
    } else {
      if (isFound[0]) {
        for (int i = 0; i < Math.abs(cardToBeFoundPosition - focusedCardPosition); i++) {
          performSwipeOverAnElement(
            focusedCard, HORIZONTAL, 80, 20, "reminder card to right");
          waitForProcessToFinish(1);
        }
      }
    }
    return isFound[0];
  }

  /**
   * This method go to my account settings from MyProfile
   */
  public void goToMyAccountSettings() {
    if (isTheElementVisible(getPnlYourNextEvent(), 3)) {
      swipeUntilFindElement(getBtnMyAccountSettings(), DOWN_TO_UP);
      waitForProcessToFinish(1);
    }
    click(getBtnMyAccountSettings(), MY_ACCOUNT_SETTINGS.getValue());
  }

  /**
   * go to my vehicles
   */
  public void goToMyVehicles() {
    if (isTheElementVisible(getPnlYourNextEvent(), 3)) {
      swipeUntilFindElement(getBtnMyVehicles(), DOWN_TO_UP);
      waitForProcessToFinish(1);
    }
    click(getBtnMyVehicles(), MY_VEHICLES.getValue());
  }

  /**
   * This method go to payment screen
   */
  public void goToMyPayments() {
    if (isTheElementVisible(getPnlYourNextEvent(), 3)) {
      swipeUntilFindElement(getBtnMyPayments(), DOWN_TO_UP);
      waitForProcessToFinish(1);
    }
    click(getBtnMyPayments(), MY_PAYMENTS.getValue());
  }

  /**
   * Initiates the user logout sequence.
   * Swipes to log out button on My Profile, initiates log out, and confirms via modal dialog.
   */
  public void logoutFromTheApp() {
    if (isTheElementVisible(getPnlYourNextEvent(), 3)) {
      swipeUntilFindElement(getBtnLogOut(), DOWN_TO_UP);
      waitForProcessToFinish(1);
    }
    click(getBtnLogOut(), LOG_OUT.getValue());
    modalLogOutTasks.clickOnLogOutButton();
  }
}