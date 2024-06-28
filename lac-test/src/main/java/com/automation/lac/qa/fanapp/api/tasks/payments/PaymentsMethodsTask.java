package com.automation.lac.qa.fanapp.api.tasks.payments;

import static com.automation.lac.qa.fanapp.api.services.payments.PaymentsMethodsService.deleteAllPaymentMethods;

import com.automation.lac.qa.utils.CustomException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class PaymentsMethodsTask {

  /**
   * Deletes all payment methods for a given user.
   * This method calls the PaymentsMethodsService to delete all payment methods
   * associated with the specified user ID.
   *
   * @param userId The ID of the user whose payment methods are to be deleted.
   */
  public static void deleteAllPaymentsMethods(String userId) {
    if (userId == null || userId.trim().isEmpty()) {
      throw new CustomException("User ID cannot be null or empty");
    }
    deleteAllPaymentMethods(userId);
  }
}