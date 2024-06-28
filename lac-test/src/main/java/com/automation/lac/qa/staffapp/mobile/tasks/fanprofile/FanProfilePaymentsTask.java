package com.automation.lac.qa.staffapp.mobile.tasks.fanprofile;

import static com.automation.lac.qa.staffapp.api.models.PaymentMethodFile.PaymentMethod;
import static com.automation.lac.qa.staffapp.mobile.enums.StaffAppKeys.PAYMENT_METHODS;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;

import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.FanProfilePaymentsScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;
import java.util.List;
import java.util.Random;

public class FanProfilePaymentsTask extends FanProfilePaymentsScreen {

  public void clickOnViewAllButton() {
    click(getBtnViewAll(), "View all");
  }

  /**
   * Tap on preferred card
   */
  @Step("Tap on the preferred card")
  public void clickOnPreferredCard() {
    getLstPaymentMethods().stream()
      .filter(paymentMethod -> isTheElementVisible(paymentMethod.getLblPreferredMethod(), 1))
      .findFirst().orElseThrow(() -> new CustomException("Preferred method not found"))
      .getImgCardLogo().click();
  }

  public void clickOnCloseCardDetails() {
    click(getCardDetailsModalComponent().getBtnClose(), "Close card details");
  }

  /**
   * Tap on valid card different to preferred or invalid
   */
  @Step("Tap on a valid card")
  public void clickOnValidCard() {
    getLstPaymentMethods().stream()
      .filter(paymentMethod -> !isTheElementVisible(paymentMethod.getLblPreferredMethod(), 1))
      .filter(paymentMethod -> !isTheElementVisible(paymentMethod.getLblCardStatus(), 1))
      .findFirst().orElseThrow(() -> new CustomException("Valid card not found"))
      .getImgCardLogo().click();
  }

  public void clickOnPreferredCheckBox() {
    click(getCardDetailsModalComponent().getCheckboxPreferredCard(), "Preferred checkbox");
  }

  public void clickOnAddCard() {
    click(getBtnAddCard(), "Add card");
  }

  /**
   * Set new payment method information
   */
  @Step("Fill the data of the new payment method")
  public void fillCardInformation() {
    PaymentMethod paymentMethod = getRandomPaymentMethod();
    sendKeys(getAddPaymentMethodModalComponent().getFrmInputCardNumber(),
      String.valueOf(paymentMethod.getCardNumber()), "Card number");
    sendKeys(getAddPaymentMethodModalComponent().getFrmInputExpiryDate(),
      paymentMethod.getExpirationDate(), "Expiry date");
    sendKeys(getAddPaymentMethodModalComponent().getFrmInputSecurityCode(),
      String.valueOf(paymentMethod.getSecurityCode()), "Security code");
    sendKeys(getAddPaymentMethodModalComponent().getFrmInputNameOnCard(),
      paymentMethod.getName(), "Name on card");
  }

  private PaymentMethod getRandomPaymentMethod() {
    List<PaymentMethod> paymentMethods = getTestContext().get(PAYMENT_METHODS.name());
    return paymentMethods.stream().skip(new Random().nextInt(paymentMethods.size())).findFirst()
      .orElseThrow(() -> new CustomException("Payment method not found"));
  }

  public void clickOnAutofillData() {
    click(getAddPaymentMethodModalComponent().getChkAutoFillData(), "Autofill data");
  }

  public void clickOnAddPaymentMethod() {
    click(getAddPaymentMethodModalComponent().getBtnAddCard(), "Add new payment method");
  }
}
