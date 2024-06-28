package com.automation.lac.qa.staffapp.mobile.questions.myuserprofile;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementAvailable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeAvailable;

import com.automation.lac.qa.staffapp.mobile.screens.myuserprofile.MyIdentityScreen;

public class MyIdentityQuestions extends MyIdentityScreen {

  /**
   * Validate My Identity Pass Panel details
   */
  public void validateMyIdentityPassPanel() {
    waitElementWillBeAvailable(getImgQrCode(), 10);
    getSoftAssert().assertTrue(isTheElementAvailable(getPanelMyIdentityTitle(), 5));
    getSoftAssert().assertTrue(isTheElementAvailable(getLblQrInfoMessage(), 5));
    getSoftAssert().assertTrue(isTheElementAvailable(getImgQrCode(), 5));
    getSoftAssert().assertTrue(isTheElementAvailable(getBtnCloseMyIdentityTitle(), 5));
  }
}
