package com.automation.lac.qa.fanapp.mobile.tasks.developersettings;

import static com.automation.lac.qa.fanapp.mobile.utils.ElementUtils.isElementChecked;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.developersettings.DeveloperSettingsScreen;
import com.automation.lac.qa.utils.mobile.WaitActions;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.qameta.allure.Step;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DeveloperSettingsTask extends DeveloperSettingsScreen {

  /**
   * Simulates pressing the volume down button three times to open developer settings.
   * This method assumes ADB is available and configured to communicate with the device.
   *
   * @return The current instance of {@code DeveloperSettingsTask} for method chaining.
   */
  public DeveloperSettingsTask setMocksWith() {
    int maxAttempts = 15;
    List<AndroidKey> keys =
      Arrays.asList(AndroidKey.VOLUME_DOWN, AndroidKey.VOLUME_DOWN, AndroidKey.VOLUME_DOWN,
        AndroidKey.VOLUME_DOWN);

    while (maxAttempts > 0 && !WaitActions.elementIsDisplayed(getDevSettingsTitle())) {
      for (AndroidKey key : keys) {
        ((PressesKey) getDriver()).pressKey(new KeyEvent().withKey(key));
      }
      maxAttempts--;
    }
    return this;
  }

  /**
   * Closes the current screen by simulating a press of the Android back button using Appium's
   * executeScript command.
   */
  public void closeScreen() {
    getDriver().navigate().back();
  }

  /**
   * Sets the location to either Illinois or Texas by toggling the geolocation settings.
   * If the specified location matches either of these states, it activates the restricted state.
   *
   * @param location The name of the location to be set.
   * @return The current instance of {@code DeveloperSettingsTask} for method chaining.
   */
  public DeveloperSettingsTask restrictedStateFor(String location) {
    if (location != null && (location.equalsIgnoreCase("Illinois")
      || location.equalsIgnoreCase("texas"))) {
      click(getToggleGeolocation(), "Geolocation");
      click(getToggleRestrictedState(), "restricted state");
    }
    return this;
  }

  /**
   * Configures the Game Face ID according to the specified user type.
   * It activates the Game Face ID toggle, selects the appropriate age based on the user type,
   * and then skips the camera step.
   *
   * @param userType The user type string, which should be "adult" to select the 45 years
   *                 old option.
   *                 If the user type is not "adult" and the 21 years old toggle is not already
   *                 active,
   *                 it will select the 21 years old option.
   * @return The current instance of {@code DeveloperSettingsTask} for method chaining.
   */
  public DeveloperSettingsTask gameFaceIdForUserType(String userType) {
    WaitActions.waitForElementVisibility(getToggleGameFaceId(), 10);
    WaitActions.waitForProcessToFinish(2); //to ensure volume down control is gone.
    click(getToggleGameFaceId(), "Game Face Id");
    switch (userType.toLowerCase()) {
      case "adult" -> {
        WaitActions.isTheElementVisible(getToggle45YearsOld(), 5);
        click(getToggle45YearsOld(), "45 years old");
      }
      case "young_adult" -> {
        if (!isElementChecked(getToggle21YearsOld())) {
          click(getToggle21YearsOld(), "21 years old");
        }
      }
      default -> log.warn("Unknown user type: {}", userType);
    }

    click(getToggleGameFaceSkipCamera(), "skip camera");
    return this;
  }

  /**
   * Sets the phone OTP (One Time Password) bypass based on the activate parameter.
   * If activate is true, it clicks on the toggle to bypass the phone OTP requirement.
   *
   * @param activate A boolean flag indicating whether to activate the bypass for phone OTP.
   */
  public void setPhoneOtpBypass(boolean activate) {
    if (activate) {
      click(getToggleBypassPhoneOtp(), "Bypass Phone OTP");
    }
  }

  /**
   * Activates or deactivates the bypass for Identity Pass verification based on the provided
   * parameter.
   * If the activate parameter is true, it clicks on the toggle to bypass the Identity Pass check.
   *
   * @param activate A boolean flag indicating whether to activate the bypass for Identity Pass
   *                 verification.
   * @return The current instance of {@code DeveloperSettingsTask} for method chaining.
   */
  public DeveloperSettingsTask setIdentityPassBypass(boolean activate) {
    if (activate) {
      click(getToggleBypassIdentityPass(), "Bypass Identity pass");
    }
    return this;
  }

  public DeveloperSettingsTask setFocus() {
    click(getDevSettingsTitle(), "Setting focus to dev settings.");
    return this;
  }


  /**
   * @param stages list of stages to enable during test execution
   */
  @Step("The user activates and configures the mock")
  public void activateMocks(List<String> stages) {
    if (isAndroid() && (stages.contains("Game Face ID") || stages.contains("Identity Pass"))) {
      setMocksWith()
        .setFocus()
        .gameFaceIdForUserType(getTestContext().get("userType"))
        .setIdentityPassBypass(true)
        .closeScreen();
    }
  }
}