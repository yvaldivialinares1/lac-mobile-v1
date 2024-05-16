package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.educationalexperience;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.I_HAVE_READ;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.CREATE_INTUIT_DOME_ACCOUNT;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.educationalexperience.CreateAccountEducationalScreen;
import com.automation.lac.qa.utils.mobile.SwipeDirections;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateAccountEducationalTask extends CreateAccountEducationalScreen {

  /**
   * Complete the Full Educational Experience
   */
  public void completeFullEducationalExperience() {
    if (!isAndroid())
      waitForElementToBeClickable(5, getBtnCreateIntuitDomeAccount());
    click(getBtnCreateIntuitDomeAccount(), CREATE_INTUIT_DOME_ACCOUNT.getValue());
    swipeUntilFindElement(getImportantInfoEducationalComponent().getChkIHaveRead(),
      SwipeDirections.DOWN_TO_UP, getImportantInfoEducationalComponent().getTxtInfoScroll());
    click(getImportantInfoEducationalComponent().getChkIHaveRead(), I_HAVE_READ.getValue());
    waitForElementToBeClickable(2,
      getImportantInfoEducationalComponent().getBtnCreateAnIntuitDomeAccount());
    click(getImportantInfoEducationalComponent().getBtnCreateAnIntuitDomeAccount(),
      CREATE_INTUIT_DOME_ACCOUNT.getValue());
  }
}