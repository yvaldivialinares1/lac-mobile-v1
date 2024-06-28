package com.automation.lac.qa.staffapp.mobile.questions.payment;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.mobile.enums.SuccessMessage.SUCCESS_PAYMENT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeAvailable;
import static org.apache.logging.log4j.util.Strings.EMPTY;

import com.automation.lac.qa.staffapp.mobile.screens.payment.PaymentConfirmationScreen;
import io.qameta.allure.Step;

public class PaymentConfirmationQuestions extends PaymentConfirmationScreen {

  /**
   * Validate successful payment screen
   */
  @Step("Validate successful payment screen")
  public void validateSuccessfulPayment() {
    waitElementWillBeAvailable(getImgPurchaseSuccess(), 30);
    getSoftAssert().assertTrue(getImgPurchaseSuccess().isDisplayed(), "Successful image");
    getSoftAssert().assertEquals(getLblTitle().getText(), SUCCESS_PAYMENT.getMessage(),
      "Successful title");
    getSoftAssert().assertNotEquals(getLblSubtitle().getText(), EMPTY, "Successful subtitle");
    getSoftAssert().assertNotEquals(getLblOwner().getText(), EMPTY, "Successful owner");
    getSoftAssert().assertTrue(getLblEventName().isDisplayed(),
      "Ticket name event");
    getTestContext().set("Ticket", getLblEventName().getText());
    getSoftAssert().assertTrue(getLblSeatsInfo().isDisplayed(),
      "Seats name event");
    getTestContext().set("Seat", getLblSeatsInfo().getText());
  }
}
