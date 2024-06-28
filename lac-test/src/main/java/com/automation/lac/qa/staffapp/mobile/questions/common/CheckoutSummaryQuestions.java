package com.automation.lac.qa.staffapp.mobile.questions.common;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementAvailable;
import static org.apache.logging.log4j.util.Strings.EMPTY;

import com.automation.lac.qa.staffapp.mobile.screens.commons.CheckoutSummaryScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;

public class CheckoutSummaryQuestions extends CheckoutSummaryScreen {

  /**
   * Validate checkout summary screen visibility
   */
  @Step("Validate checkout summary screen is displayed")
  public void validateCheckoutScreen() {
    getSoftAssert().assertTrue(getLblTitleEvent().isDisplayed(), "Title event");
    getSoftAssert().assertTrue(getLblEventDate().isDisplayed(), "Date event");
    getSoftAssert().assertFalse(getLstGarageButtons().isEmpty(), "Garage options");
    getSoftAssert().assertFalse(getBtnPlaceOrder().isEnabled(), "Place order");
  }

  /**
   * Validate purchase information
   **/
  @Step("Validate purchase information")
  public void validatePurchasePrice() {
    getSoftAssert().assertNotEquals(getLblTotalParking().getText(), EMPTY, "Total parking");
    getSoftAssert().assertNotEquals(getLblTotalPrice().getText(), EMPTY, "Total price");
    getSoftAssert().assertTrue(getBtnPlaceOrder().isEnabled(), "Place order");
  }

  /**
   * Validate valid payment method
   */
  @Step("Validate valid payment method")
  public void validateValidPaymentMethod() {
    if (isTheElementAvailable(getLblWarningPayment(), 5)) {
      throw new CustomException("Valid payment method not found");
    } else {
      getSoftAssert().assertTrue(getPaymentCardComponent().getImgCardLogo().isDisplayed(),
        "Card logo");
      getSoftAssert().assertTrue(getPaymentCardComponent().getLblCardName().isDisplayed(),
        "Card name");
    }
  }
}
