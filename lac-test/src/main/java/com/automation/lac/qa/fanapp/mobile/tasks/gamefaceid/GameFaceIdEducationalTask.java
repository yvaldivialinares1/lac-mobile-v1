package com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SAVE_GAME_FACE_ID;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SET_MY_GAME_FACE_ID;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.WHILE_USING_APP;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeElementToTheBorder;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.TOP_PAGE;
import static com.automation.lac.qa.utils.mobile.WaitActions.quickIsDisplayed;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeAvailable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForProcessToFinish;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.mobile.screens.educationals.GameFaceIdEducationalScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;

public class GameFaceIdEducationalTask extends GameFaceIdEducationalScreen {

  private static final int WAIT_MAX_TIME = 20;

  /**
   * Skip Game Face Educational Screen
   */
  @Step("The user does skips game face id")
  public void skipGameFaceEducationalScreen() {
    try {
      click(getBtnSkip(), SKIP.getValue());
    } catch (NoSuchElementException exception) {
      if (quickIsDisplayed(getUnavailableGameFaceImgXpath(), 5)) {
        click(getBtnContinue(), CONTINUE.getValue());
      } else if (quickIsDisplayed(getErrorXpath(), 5)) {
        UserInfo userInfo = getTestContext().get(USER_INFO.name());
        String phoneCountry = userInfo.getPhoneInfo().getPhoneOtpCountry();
        String phoneCode = userInfo.getPhoneInfo().getPhoneOtpCountryCode();
        String phoneNum = userInfo.getPersonalInfo().getPhoneNumber();
        throw new CustomException(String.format("Not able to validate phone number "
          + "(+%s)%s from %s on TicketMaster\nBug: CA-57090", phoneCode, phoneNum, phoneCountry));
      } else {
        throw new CustomException("Unexpected Error occurred");
      }
    }
  }

  public void clickBackOnGameFaceEducationalScreen() {
    click(getBtnBack(), BACK.getValue());
  }

  /**
   * Set up a user game face id.
   */
  @Step("The user sets game face id")
  public void setMyGameFaceId() {
    click(getBtnSetGameFaceId(), SET_MY_GAME_FACE_ID.getValue());
    click(getEnableCameraWidget().getBtnWhileUsingTheApp(), WHILE_USING_APP.getValue());
    waitForElementVisibility(getGameFaceIdDataWidget().getTxtInfoScroll(), WAIT_MAX_TIME);
    swipeElementToTheBorder(TOP_PAGE, getGameFaceIdDataWidget().getTxtInfoScroll());
    waitForProcessToFinish(1);
    waitForElementToBeClickable(getGameFaceIdDataWidget().getBtnContinue(), 5);
    click(getGameFaceIdDataWidget().getBtnContinue(), CONTINUE.getValue());
    waitElementWillBeAvailable(getSaveGameFaceId(), WAIT_MAX_TIME);
    click(getSaveGameFaceId(), SAVE_GAME_FACE_ID.getValue());
  }
}