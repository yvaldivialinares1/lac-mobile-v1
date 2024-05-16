package com.automation.lac.qa.fanapp.mobile.mocks.ios.tasks;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.mocks.ios.screens.MobileConfigurationsScreen;

public class MobileConfigurationsTasks extends MobileConfigurationsScreen {

  /**
   * Activates or deactivates the geolocation mock feature.
   *
   * @param activate if true, the geolocation mock will be activated, otherwise deactivated.
   */
  public void activateGeolocationMock(boolean activate) {
    if (activate) {
      click(getSwtchMockGeolocation(), "Geolocation mock");
    }
  }

  /**
   * Activates or deactivates the 20 years old age mock for the Game Face ID feature.
   *
   * @param activate if true, the 20 years old age mock will be activated, otherwise deactivated.
   */
  public void activateGameFaceIdWith20YearsOldMock(boolean activate) {
    if (activate) {
      click(getSwtch20YearsOld(), "20 years old mock");
    }
  }

  /**
   * Activates or deactivates the 45 years old age mock for the Game Face ID feature.
   *
   * @param activate if true, the 45 years old age mock will be activated, otherwise deactivated.
   */
  public void activateGameFaceIdWith45YearsOldMock(boolean activate) {
    if (activate) {
      click(getSwtch45YearsOld(), "45 years old mock");
    }
  }

  /**
   * Activates or deactivates a mock feature during the account creation flow.
   *
   * @param activate if true, the mock for create account flow will be activated,
   *                 otherwise deactivated.
   */
  public void activateAtCreateAccountFlowMock(boolean activate) {
    if (activate) {
      click(getSwtchAtCreateAccountFlow(), "at create account flow mock");
    }
  }

  /**
   * Clicks on the 'Done' button.
   */
  public void clickOnDone() {
    click(getBtnDone(), "Done");
  }

}