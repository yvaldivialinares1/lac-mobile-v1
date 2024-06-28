package com.automation.lac.qa.staffapp.mobile.tasks.fanprofile;


import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.automation.lac.qa.staffapp.mobile.enums.FanProfileInformationOption;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.FanProfileInformationScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;

public class FanProfileInformationTask extends FanProfileInformationScreen {

  /**
   * Click on fan option button
   *
   * @param fanProfileInformationOption for click on button
   */
  @Step("Tap on fan option tap {fanProfileInformationOption}")
  public void clickOnFanOptionButton(FanProfileInformationOption fanProfileInformationOption) {
    getLstFanOptionsButtons().stream().filter(
        option -> containsIgnoreCase(option.getAttribute("label"),
          fanProfileInformationOption.getValue()))
      .findFirst().orElseThrow(() -> new CustomException("Fan button option not found")).click();
  }

  public void tapSeePersonalInformation() {
    click(getBtnExpandFanInformation(), getBtnExpandFanInformation().getAttribute("label"));
  }

  public void tapSeeTeammatesAccountsList() {
    click(getLblTeamMatesTitle(), "teammate accounts");
  }
}
