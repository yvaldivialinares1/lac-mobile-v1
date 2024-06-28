package com.automation.lac.qa.fanapp.mobile.tasks.myprofile;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_IDENTITY;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PASSWORD_SECURITY;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PERSONAL_INFORMATION;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.QR_CODE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.TEAMMATE_ACCOUNTS;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.myprofile.MyAccountSettingsScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods.MyPaymentMethodsTask;

public class MyAccountSettingsTasks extends MyAccountSettingsScreen {
  private final MyLoggedProfileTask myProfileLoggedTasks = new MyLoggedProfileTask();
  private final MyPaymentsTask myPaymentsTask = new MyPaymentsTask();
  private final MyPaymentMethodsTask myPaymentMethodsTask = new MyPaymentMethodsTask();

  public void clickOnPersonalInformation() {
    click(getBtnPersonalInformation(), PERSONAL_INFORMATION.getValue());
  }

  public void clickOnPasswordAndSecurity() {
    click(getBtnPasswordAndSecurity(), PASSWORD_SECURITY.getValue());
  }

  public void clickOnMyIdentity() {
    click(getBtnMyIdentity(), MY_IDENTITY.getValue());
  }

  public void clickOnMiniAccounts() {
    click(getBtnTeammateAccounts(), TEAMMATE_ACCOUNTS.getValue());
  }

  public void clickOnQrCode() {
    click(getBtnQrCode(), QR_CODE.getValue());
  }

  public void clickOnBackButton() {
    clickOnBackButton(1);
  }

  /**
   * Go back several times.
   */
  public void clickOnBackButton(int numberOfBack) {
    for (int i = 0; i < numberOfBack; i++) {
      click(getBtnBack(), BACK.getValue());
    }
  }

  /**
   * Navigates back to the user's profile from the account settings screen.
   */
  public void goBackToProfileFromMyAccountSettings() {
    clickOnBackButton();
  }

  /**
   * Transitions to the account settings from the payment methods management interface.
   * It navigates through payments to the user's profile and then to account settings.
   */
  public void goToMyAccountSettingsFromMyPaymentMethodsManagement() {
    myPaymentMethodsTask.goToMyPaymentsFromMyPaymentMethods();
    myPaymentsTask.goToMyProfileFromMyPayments();
    myProfileLoggedTasks.goToMyAccountSettings();
  }

  /**
   * Transitions to the My Profile from the payment methods management interface.
   * It navigates through payments to the user's profile and then to My Profile.
   */
  public void goToMyProfileFromMyPaymentMethodsManagement() {
    myPaymentMethodsTask.goToMyPaymentsFromMyPaymentMethods();
    myPaymentsTask.goToMyProfileFromMyPayments();
  }
}