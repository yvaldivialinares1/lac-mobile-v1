package com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CLEAR_NICKNAME;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DELETE_IN_CONFIRMATION_MODAL;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DELETE_PAYMENT_METHOD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DELETE_UPDATE_IN_CONFIRMATION_MODAL;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.GIVE_NICKNAME;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SAVE_NICKNAME;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.NICKNAME;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.DeviceActions.setTextField;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.DOWN_TO_UP;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.api.models.PaymentMethodFile;
import com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.CardDetailsScreen;
import com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils;
import com.automation.lac.qa.utils.mobile.WaitActions;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardDetailsTask extends CardDetailsScreen {

  private final String txtAttribute = isAndroid() ? "text" : "label";

  /**
   * Sets a nickname to a payment method
   *
   * @param nickname a non-empty card nickname string
   */
  public void giveItANickname(String nickname) {
    if (!nickname.isEmpty()) {
      waitForElementVisibility(getBtnGiveItANickname(), 5);
      click(getBtnGiveItANickname(), GIVE_NICKNAME.getValue());
      waitForElementVisibility(getTxtEditNicknameAtModal(), 5);
      setTextField(getTxtEditNicknameAtModal(), nickname, NICKNAME.getValue());
      click(getBtnSaveNicknameAtModal(), SAVE_NICKNAME.getValue());
    } else {
      log.info("Nickname cannot be empty when giving a nickname to a given card");
    }
  }

  /**
   * Updates the nickname of a card has already a given nickname
   *
   * @param nickname a nickname string different from current
   */
  public void editNickname(String nickname) {
    if (getBtnEditNickname().isDisplayed()) {
      if (!getTxtNickname().getAttribute(txtAttribute).equals(nickname)) {
        click(getBtnEditNickname(), GIVE_NICKNAME.getValue());
        click(getBtnClearEditNicknameAtModal(), CLEAR_NICKNAME.getValue());
        sendKeys(getTxtNickname(), nickname, NICKNAME.getValue());
        click(getBtnSaveNicknameAtModal(), SAVE_NICKNAME.getValue());
      } else {
        log.info("Nickname replacement cannot be the same from current.");
      }
    } else {
      log.info("Edit nickname operation failed, edit nickname is not present.");
    }
  }

  /**
   * Deletes a payment method
   */
  public void deletePaymentMethod(PaymentMethodFile.PaymentMethod card) {
    deletePaymentMethod(card, null);
  }

  /**
   * Deletes a payment method
   *
   * @param cardToReplacePreferred last 4 digits of card to replace preferred when prior to deletion
   *                               there are up to 3 cards, if not provided top card is selected
   *                               by default.
   */
  public void deletePaymentMethod(PaymentMethodFile.PaymentMethod card,
                                  String cardToReplacePreferred) {
    boolean isPreferred =
      !WaitActions.isTheElementVisible(getChkPreferredPaymentMethod(), 5);
    List<PaymentMethodFile.PaymentMethod> cards = PaymentUtils.getAllUsedCards();
    click(getBtnDeletePaymentMethod(), DELETE_PAYMENT_METHOD.getValue());
    if (!cards.isEmpty() && cards.size() <= 2) {
      click(getBtnDeleteInConfirmationModal(), DELETE_IN_CONFIRMATION_MODAL.getValue());
    } else if (cards.size() >= 3) {
      if (isPreferred) {
        if (cardToReplacePreferred != null) {
          swipeUntilFindElement(getCardNumber(cardToReplacePreferred), DOWN_TO_UP,
            getDeleteModalView());
          click(getCardNumber(cardToReplacePreferred), cardToReplacePreferred);
        }
        click(getBtnDeleteAndUpdateInConfirmationModal(),
          DELETE_UPDATE_IN_CONFIRMATION_MODAL.getValue());
      } else {
        click(getBtnDeleteInConfirmationModal(), DELETE_IN_CONFIRMATION_MODAL.getValue());
      }
    }
    //update test context to set card as not used
    PaymentUtils.setCardUsageInTest(card, false);
  }

  /**
   * Clicks back button
   */
  public void clickBack() {
    click(getBtnBackButton(), BACK.getValue());
  }
}
