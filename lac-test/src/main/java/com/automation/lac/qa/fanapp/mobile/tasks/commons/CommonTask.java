package com.automation.lac.qa.fanapp.mobile.tasks.commons;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.quickIsDisplayed;

import com.automation.lac.qa.fanapp.mobile.screens.commons.CommonsScreen;
import com.automation.lac.qa.utils.mobile.WaitActions;


public class CommonTask extends CommonsScreen {
  private static final int MAX_ATTEMPTS = 10;
  private static final int WAIT_TIME_SHORT = 5;
  private static final int WAIT_TIME_LONG = 20;

  public void clickOnBackButton() {
    click(getBtnBack(), BACK.getValue());
  }

  /**
   * @param screen screen title name to reach
   */
  public void navigateBack(String screen) {
    int attempts = 0;
    while (attempts <= MAX_ATTEMPTS) {
      if ("home".equalsIgnoreCase(screen)) {
        if (isHomeScreenReached()) {
          break;
        }
      } else {
        if (isScreenReached(screen)) {
          break;
        }
      }
      clickOnBackButton();
      attempts++;
    }
  }

  private boolean isHomeScreenReached() {
    return WaitActions.elementIsDisplayed(getBtnMenu())
      || !WaitActions.isTheElementVisible(getBtnBack(), WAIT_TIME_LONG);
  }

  /**
   * @param screen screen title name to reach
   * @return true or false if screen is reached
   */
  private boolean isScreenReached(String screen) {
    if (WaitActions.isTheElementVisible(getTitle(), WAIT_TIME_SHORT)) {
      if (!isAndroid()) {
        return getTitle().getAttribute("label").equalsIgnoreCase(screen);
      } else {
        return quickIsDisplayed(getAndroidTitle(screen), 1);
      }
    }
    return false;
  }
}
