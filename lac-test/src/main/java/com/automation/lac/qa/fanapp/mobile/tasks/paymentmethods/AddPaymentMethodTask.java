package com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_CARD_CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CHECK_AUTOFILL;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PREFERRED_CARD;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.ADDRESS;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.CARD_NUMBER;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.CITY;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.COUNTRY;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.EXPIRY_DATE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.NAME_ON_CARD;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.SECURITY_CODE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.STATE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.US;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.ZIP_CODE;
import static com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils.getNotUsedCard;
import static com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils.setCardUsageInTest;
import static com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils.setPreferredCardStatus;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.DeviceActions.setTextField;
import static com.automation.lac.qa.utils.mobile.DeviceActions.specialIosSetText;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeElementToTheBorder;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.TOP_PAGE;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.AddressInfo;
import com.automation.lac.qa.fanapp.api.models.PaymentMethodFile.PaymentMethod;
import com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.AddPaymentMethodScreen;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

public class AddPaymentMethodTask extends AddPaymentMethodScreen {

  /**
   * Adds a valid payment method
   */
  public void addValidCard() {
    addValidCard(getNotUsedCard());
  }

  /**
   * Adds a valid card with autofill for billing address
   *
   * @param card payment method object
   */
  public void addValidCard(PaymentMethod card) {
    addValidCard(card, null);
  }

  /**
   * @param card           payment method object to be added
   * @param billingAddress address object or null
   */
  public void addValidCard(PaymentMethod card, AddressInfo billingAddress) {
    if (isAndroid()) {
      setTextField(getTxtCardNumber(), String.valueOf(card.getCardNumber()),
        CARD_NUMBER.getValue());
      setTextField(getTxtExpiryDate(), card.getExpirationDate(), EXPIRY_DATE.getValue());
      setTextField(getTxtSecurityCode(), String.valueOf(card.getSecurityCode()),
        SECURITY_CODE.getValue());
      click(getTxtNameOnCard(), NAME_ON_CARD.getValue());
      sendKeys(getTxtNameOnCard(), card.getName(), NAME_ON_CARD.getValue());
      hideKeyboard(null);
    } else {
      specialIosSetText(getTxtCardNumber(), String.valueOf(card.getCardNumber()),
        CARD_NUMBER.getValue(), true);
      specialIosSetText(getTxtExpiryDate(), card.getExpirationDate(), EXPIRY_DATE.getValue(), true);
      specialIosSetText(getTxtSecurityCode(), String.valueOf(card.getSecurityCode()),
        SECURITY_CODE.getValue(), true);
      specialIosSetText(getTxtNameOnCard(), card.getName(), NAME_ON_CARD.getValue(), false);
    }
    swipeElementToTheBorder(TOP_PAGE, getChkAutofill());
    if (billingAddress == null) {
      waitForElementToBeClickable(getChkAutofill(), 10);
      click(getChkAutofill(), CHECK_AUTOFILL.getValue());
    } else {
      fillBillingAddressForm(billingAddress);
    }

    //TODO Remove when bug is fixed
    if (isAndroid()) {
      UserInfo userInfo = getTestContext().get(USER_INFO.name());
      String expectedState =  userInfo.getAddressInfo().getState();
      if (!getDpdState().getAttribute("content-desc").contains(expectedState)) {
        Allure.step("State not preloaded. Bug: CA-59227", Status.FAILED);
        click(getDpdState(), STATE.getValue());
        getSelectStateWidget().selectState(expectedState);
      }
    }

    waitForElementToBeClickable(getBtnContinueOrAddCard(), 10);
    click(getBtnContinueOrAddCard(), ADD_CARD_CONTINUE.getValue());
    // update test context to ensures that the first added card encountered is marked as preferred
    if (!getTestContext().containsKey(PREFERRED_CARD.name())) {
      setPreferredCardStatus(card, true);
      getTestContext().set(PREFERRED_CARD.name(), card);
    }
    //update test context to set card as used
    setCardUsageInTest(card, true);
  }

  /**
   * Complete billing address form
   * @param billingAddress address object
   */
  private void fillBillingAddressForm(AddressInfo billingAddress) {
    sendKeys(getTxtStreetAddress(), billingAddress.getPrimaryAddress(), ADDRESS.getValue());
    click(getDpdCountry(), COUNTRY.getValue());
    getSelectCountryWidget().selectCountry(US.getValue());
    click(getDpdState(), STATE.getValue());
    getSelectStateWidget().selectState(billingAddress.getState());
    sendKeys(getTxtCity(), billingAddress.getCity(), CITY.getValue());
    sendKeys(getTxtZipCode(), billingAddress.getZipCode(), ZIP_CODE.getValue());
  }
}