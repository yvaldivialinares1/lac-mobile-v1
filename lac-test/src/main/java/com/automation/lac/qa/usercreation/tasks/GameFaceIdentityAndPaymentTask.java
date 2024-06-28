package com.automation.lac.qa.usercreation.tasks;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.GAME_FACE_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IDENTITY_PASS_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IS_CARD_ADDED;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.MOCK_ACTIVATION_STATUS;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PAYMENT_METHODS_CREATION_RESULT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.UserCreationConstant.GAME_FACE_ID;
import static com.automation.lac.qa.utils.UserCreationConstant.IDENTITY_PASS;
import static com.automation.lac.qa.utils.UserCreationConstant.PAYMENT_METHOD;
import static io.qameta.allure.model.Status.PASSED;
import static io.qameta.allure.model.Status.SKIPPED;

import com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid.GameFaceIdEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.CreateAccountEndingTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.identitypass.MyIdentityPassEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods.AddPaymentMethodTask;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentsmethod.AddPaymentMethodEducationalTask;

public class GameFaceIdentityAndPaymentTask {

  private final GameFaceIdEducationalTask gameFaceIdEducationalTask =
    new GameFaceIdEducationalTask();
  private final MyIdentityPassEducationalTask myIdentityPassEducationalTask =
    new MyIdentityPassEducationalTask();
  private final AddPaymentMethodEducationalTask addPaymentMethodEducationalTask =
    new AddPaymentMethodEducationalTask();
  private final AddPaymentMethodTask addPaymentMethodTask = new AddPaymentMethodTask();

  /**
   * Completes the GameFaceId flow
   */
  public void manageGameFaceId() {
    if (GAME_FACE_ID && (boolean) getTestContext().get(MOCK_ACTIVATION_STATUS.name())) {
      gameFaceIdEducationalTask.setMyGameFaceId();
      getTestContext().set(GAME_FACE_CREATION_RESULT.name(), PASSED);
    } else {
      gameFaceIdEducationalTask.skipGameFaceEducationalScreen();
      getTestContext().set(GAME_FACE_CREATION_RESULT.name(), SKIPPED);
    }
  }

  /**
   * Completes the IdentityPass flow
   */
  public void manageIdentityPass() {
    if (IDENTITY_PASS && (boolean) getTestContext().get(MOCK_ACTIVATION_STATUS.name())) {
      myIdentityPassEducationalTask.clickOnAddIdentityPass();
      getTestContext().set(IDENTITY_PASS_CREATION_RESULT.name(), PASSED);
    } else {
      myIdentityPassEducationalTask.skipMyIdentityPassEducationalScreen();
      getTestContext().set(IDENTITY_PASS_CREATION_RESULT.name(), SKIPPED);
    }
  }

  /**
   * Completes the PaymentMethod flow
   */
  public CreateAccountEndingTasks managePaymentMethod() {
    if (PAYMENT_METHOD) {
      addPaymentMethodEducationalTask.clickAddPaymentMethod();
      addPaymentMethodTask.addValidCard();
      getTestContext().set(IS_CARD_ADDED.name(), true);
      getTestContext().set(PAYMENT_METHODS_CREATION_RESULT.name(), PASSED);
    } else {
      addPaymentMethodEducationalTask.skipAddPaymentMethodEducationalScreen();
      getTestContext().set(PAYMENT_METHODS_CREATION_RESULT.name(), SKIPPED);
    }

    return new CreateAccountEndingTasks();
  }
}