package com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_CARD_CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CHECK_AUTOFILL;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PREFERRED_CARD;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.ADDRESS;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.CARD_NUMBER;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.CITY;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.COUNTRY;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.EXPIRY_DATE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.NAME_ON_CARD;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.SEARCH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.SECURITY_CODE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.STATE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.US;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.ZIP_CODE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.DeviceActions.setTextField;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeElementToTheBorder;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.TOP_PAGE;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import com.automation.lac.qa.faker.models.userinfo.AddressInfo;
import com.automation.lac.qa.fanapp.api.models.PaymentMethodFile.PaymentMethod;
import com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.AddPaymentMethodScreen;
import com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils;

public class AddPaymentMethodTask extends AddPaymentMethodScreen {

  /**
   * Adds a valid payment method
   */
  public void addValidCard() {
    var card = PaymentUtils.getNotUsedCard();
    addValidCard(card);
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
    setTextField(getTxtCardNumber(), String.valueOf(card.getCardNumber()), CARD_NUMBER.getValue());
    setTextField(getTxtExpiryDate(), card.getExpirationDate(), EXPIRY_DATE.getValue());
    setTextField(getTxtSecurityCode(), String.valueOf(card.getSecurityCode()),
      SECURITY_CODE.getValue());
    click(getTxtNameOnCard(), NAME_ON_CARD.getValue());
    sendKeys(getTxtNameOnCard(), card.getName(), NAME_ON_CARD.getValue());
    hideKeyboard(null);
    swipeElementToTheBorder(TOP_PAGE, getChkAutofill());
    if (billingAddress == null) {
      click(waitForElementToBeClickable(10, getChkAutofill()), CHECK_AUTOFILL.getValue());
    } else {
      fillBillingAddressForm(billingAddress);
    }
    swipeElementToTheBorder(TOP_PAGE, getBtnContinueOrAddCard());
    click(waitForElementToBeClickable(10, getBtnContinueOrAddCard()), ADD_CARD_CONTINUE.getValue());
    // update test context to ensures that the first added card encountered is marked as preferred
    if (!getTestContext().containsKey(PREFERRED_CARD.name())) {
      PaymentUtils.setPreferredCardStatus(card, true);
      getTestContext().set(PREFERRED_CARD.name(), card);
    }
    //update test context to set card as used
    PaymentUtils.setCardUsageInTest(card, true);
  }

  /**
   * @param billingAddress address object
   */
  private void fillBillingAddressForm(AddressInfo billingAddress) {
    sendKeys(getTxtStreetAddress(), billingAddress.getPrimaryAddress(), ADDRESS.getValue());
    click(getDpdCountry(), COUNTRY.getValue());
    sendKeys(getTxtSearch(), US.getValue(), SEARCH.getValue());
    if (isAndroid()) {
      click(selectOption(US.getValue()), US.getValue());
    } else {
      click(getLblCountryList().stream().findFirst().orElseThrow(), US.getValue());
    }
    click(getDpdState(), STATE.getValue());
    sendKeys(getTxtSearch(), billingAddress.getState(), SEARCH.getValue());
    if (isAndroid()) {
      click(selectOption(billingAddress.getState()), billingAddress.getState());
    } else {
      click(getLblStatesList().stream().findFirst().orElseThrow(), billingAddress.getState());
    }
    sendKeys(getTxtCity(), billingAddress.getCity(), CITY.getValue());
    sendKeys(getTxtZipCode(), billingAddress.getZipCode(), ZIP_CODE.getValue());
  }
}