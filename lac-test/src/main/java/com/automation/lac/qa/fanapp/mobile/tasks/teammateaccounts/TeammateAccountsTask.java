package com.automation.lac.qa.fanapp.mobile.tasks.teammateaccounts;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_TEAMMATE_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.teammateaccounts.TeammateAccountsScreen;

public class TeammateAccountsTask extends TeammateAccountsScreen {

  public AddTeammateAccountTask clickAddTeammateAccount() {
    click(getBtnAddTeammateAccount(), ADD_TEAMMATE_ACCOUNT.getValue());
    return new AddTeammateAccountTask();
  }

  public void goToMyAccountSettingsFromTeammateAccounts() {
    click(getBtnBack(), BACK.getValue());
  }
}