package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.educationalexperience;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.I_HAVE_READ;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.CREATE_INTUIT_DOME_ACCOUNT;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.TOP_PAGE;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementAttributeToBeValue;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementAttributeContainsValue;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementAttributeValue;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.educationalexperience.CreateAccountEducationalScreen;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateAccountEducationalTask extends CreateAccountEducationalScreen {

  /**
   * Complete the Full Educational Experience
   */
  public void completeFullEducationalExperience() {
    if (!isAndroid()) {
      waitForElementVisibility(getLblCreateYourAccountTittle(), 10);
      waitForElementAttributeContainsValue(getBtnCreateIntuitDomeAccount(),
        "accessible", "true", 10);
    }
    click(getBtnCreateIntuitDomeAccount(), CREATE_INTUIT_DOME_ACCOUNT.getValue());

    swipeUntilFindElement(getImportantInfoWidget().chkIHaveReadXpath(), TOP_PAGE,
      getImportantInfoWidget().getTxtInfoScroll());

    if (isAndroid()) {
      if (!isTheElementAttributeToBeValue(getImportantInfoWidget().getChkIHaveRead(),
        3, "checked", "true")) {
        click(getImportantInfoWidget().getChkIHaveRead(), I_HAVE_READ.getValue());
      }
    } else {
      click(getImportantInfoWidget().getChkIHaveRead(), I_HAVE_READ.getValue());
    }

    waitForElementAttributeValue(getImportantInfoWidget().getBtnContinue(), "enabled", "true", 3);
    click(getImportantInfoWidget().getBtnContinue(), CONTINUE.getValue());
  }
}