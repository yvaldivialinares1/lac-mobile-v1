package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.Constants.APP_MOCKS;

import com.automation.lac.qa.fanapp.mobile.tasks.commons.CommonTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitypass.MyIdentityPassEducationalTask;
import io.cucumber.java.en.And;

public class MyIdentityPassStepsDefinition {

  private final MyIdentityPassEducationalTask myIdentityPassEducationalTask =
    new MyIdentityPassEducationalTask();
  private final CommonTask commonTask = new CommonTask();

  @And("the user skip the identity pass registration")
  public void theUserSkipTheGameFaceRegistration() {
    myIdentityPassEducationalTask.skipMyIdentityPassEducationalScreen();
  }

  /**
   * This method simulates the completion of the identity pass registration by a user.
   * It clicks on the "Add Identity Pass" button and then checks that the necessary
   * elements are displayed on the screen.
   */
  @And("the user completes the identity pass registration( from reminder)?$")
  public void theUserCompletesTheIdentityPassRegistration(String from) {
    //TODO: remove conditional and else condition upon the mock support is in place for iOS.
    if (isAndroid() && APP_MOCKS) {
      myIdentityPassEducationalTask
        .clickOnAddIdentityPass();
    } else {
      if (from != null && from.contains("from reminder")) {
        commonTask.navigateBack("My Profile");
      } else {
        myIdentityPassEducationalTask.skipMyIdentityPassEducationalScreen();
      }
    }
  }
}