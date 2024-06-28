package com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_CARD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_PAYMENT_METHOD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.DOWN_TO_UP;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementAttributeValue;

import com.automation.lac.qa.fanapp.api.models.PaymentMethodFile;
import com.automation.lac.qa.fanapp.mobile.screens.commons.CommonsScreen;
import com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.MyPaymentMethodsScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyPaymentsTask;
import com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils;
import com.automation.lac.qa.utils.mobile.WaitActions;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

@Slf4j
public class MyPaymentMethodsTask extends MyPaymentMethodsScreen {

  private final CommonsScreen commonsScreen = new CommonsScreen();
  private final AddPaymentMethodTask addPaymentMethod = new AddPaymentMethodTask();
  private final CardDetailsTask cardDetails = new CardDetailsTask();
  private final MyPaymentsTask myPaymentsTask = new MyPaymentsTask();

  /**
   * Navigates to the detail of a given card
   *
   * @param card payment method object
   */
  public void navigateToCardDetail(PaymentMethodFile.PaymentMethod card) {
    String lastFour = PaymentUtils.getCardLastFourDigits(card.getCardNumber());
    swipeUntilFindElement(getCardNumber(lastFour), DOWN_TO_UP, getScrollableView());
    click(getCardNumber(lastFour), lastFour);
  }

  /**
   * Add payment method, if payment methods have been added before it will interact the add card
   * button, else it will interact with the add payment method button.
   */
  public void addPaymentMethod() {
    if (WaitActions.isTheElementVisible(getLblNoPaymentMethodsYet(), 5)) {
      click(getBtnAddPaymentMethod(), ADD_PAYMENT_METHOD.getValue());
    } else if (WaitActions.isTheElementVisible(getLblPaymentMethods(), 5)) {
      swipeUntilFindElement(getBtnAddCard(), DOWN_TO_UP, getScrollableView());
      click(getBtnAddCard(), ADD_CARD.getValue());
    } else {
      log.warn("Add card or add payment method buttons are not displayed.");
    }
  }


  public void goToMyPaymentsFromMyPaymentMethods() {
    waitForElementAttributeValue(commonsScreen.getBtnBack(), "clickable", "true", 10);
    click(commonsScreen.getBtnBack(), BACK.getValue());
  }

  /**
   * Adds N payment methods from the payment method management screen
   *
   * @param numberOfPaymentMethods number of cards to add
   */
  public void addValidPaymentMethods(int numberOfPaymentMethods) {
    for (int i = 0; i < numberOfPaymentMethods; i++) {
      var card = PaymentUtils.getNotUsedCard();
      log.info("Selected card is: " + card.getCardNumber() + " " + card.getName());
      if (card != null) {
        addPaymentMethod();
        addPaymentMethod.addValidCard(card);
      } else {
        log.warn("There are not more available cards in the data set.");
      }
    }
  }

  /**
   * Deletes N payment methods from the payment method management screen
   *
   * @param numberOfPaymentMethods number of cards to delete
   */
  public void deletePaymentMethods(int numberOfPaymentMethods) {
    for (int i = 0; i < numberOfPaymentMethods; i++) {
      var card = PaymentUtils.getUsedCard();
      if (card != null) {
        navigateToCardDetail(card);
        cardDetails.deletePaymentMethod(card);
      }
    }
  }

  /**
   * Set a nickname for a given card
   *
   * @param card card object
   */
  public void setNicknameForPaymentMethod(PaymentMethodFile.PaymentMethod card) {
    Faker faker = new Faker();
    String nickname = faker.funnyName().name();
    navigateToCardDetail(card);
    cardDetails.giveItANickname(nickname);
    //update test context with new nickname
    PaymentUtils.setCardNicknameInTest(card, nickname);
    cardDetails.clickBack();
  }

  /**
   * Navigates to the user's profile page from the "My Payments" section.
   */
  public void goToMyProfileFromMyPayments() {
    goToMyPaymentsFromMyPaymentMethods();
    myPaymentsTask.goToMyProfileFromMyPayments();
  }
}