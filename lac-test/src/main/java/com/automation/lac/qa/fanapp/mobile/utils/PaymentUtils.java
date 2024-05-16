package com.automation.lac.qa.fanapp.mobile.utils;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PAYMENT_METHODS;

import com.automation.lac.qa.fanapp.api.models.PaymentMethodFile;
import com.automation.lac.qa.utils.TestContextManager;
import java.util.List;
import java.util.Random;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentUtils {


  /**
   * @return a card from test context that has not been used.
   */
  public static PaymentMethodFile.PaymentMethod getNotUsedCard() {
    return getCard(false);
  }

  /**
   * @return a card from test context that has been used.
   */
  public static PaymentMethodFile.PaymentMethod getUsedCard() {
    return getCard(true);
  }

  /**
   * @return a card from test context that has been used.
   */
  public static List<PaymentMethodFile.PaymentMethod> getAllUsedCards() {
    var cards = getTestCards();
    return cards.stream().filter(PaymentMethodFile.PaymentMethod::isUsed)
      .toList();
  }

  /**
   * @param isUsedStatus state of the card usage (true= used/false=not used)
   * @return a random card object when matches the isUsed condition or null
   */
  public static PaymentMethodFile.PaymentMethod getCard(boolean isUsedStatus) {
    Random random = new Random();
    var filteredList =
      getTestCards().stream().filter(paymentMethod -> paymentMethod.isUsed() == isUsedStatus)
        .toList();
    if (filteredList.isEmpty()) {
      return null;
    } else {
      return filteredList.get(random.nextInt(filteredList.size()));
    }
  }

  /**
   * Updates the card status in the test context
   *
   * @param targetCard card objet to update its usage status (true= used/false=not used).
   */
  public static void setCardUsageInTest(PaymentMethodFile.PaymentMethod targetCard,
                                        boolean usageStatus) {
    getTestCards().remove(targetCard);
    targetCard.setUsed(usageStatus);
    getTestCards().add(targetCard);
  }

  /**
   * Updates the card used nickname in the test context
   *
   * @param targetCard card objet to update its usage status (true= used/false=not used).
   */
  public static void setCardNicknameInTest(PaymentMethodFile.PaymentMethod targetCard,
                                           String nickname) {
    getTestCards().remove(targetCard);
    targetCard.setNickname(nickname);
    getTestCards().add(targetCard);
  }

  /**
   * @param cardNumber card number
   * @return last four digits
   */
  public static String getCardLastFourDigits(long cardNumber) {
    return String.valueOf(cardNumber).substring(String.valueOf(cardNumber).length() - 4);
  }

  /**
   * @return a list of cards in the context manager
   */
  private static List<PaymentMethodFile.PaymentMethod> getTestCards() {
    return (List<PaymentMethodFile.PaymentMethod>) TestContextManager.getTestContext()
      .get(PAYMENT_METHODS.name());
  }

  /**
   * Updates the card Preferred status in the test context
   *
   * @param targetCard card objet to update its preferred status (true= used/false=not used).
   */
  public static void setPreferredCardStatus(PaymentMethodFile.PaymentMethod targetCard,
                                            boolean preferredStatus) {
    getTestCards().remove(targetCard);
    targetCard.setPreferred(preferredStatus);
    getTestCards().add(targetCard);
  }

}
