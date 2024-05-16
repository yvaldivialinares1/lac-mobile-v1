package com.automation.lac.qa.fanapp.mobile.tasks.teammateaccounts;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_TEAMMATE_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.faker.models.MiniAccountInfo;
import com.automation.lac.qa.fanapp.mobile.screens.teammateaccounts.TeammateAccountsScreen;
import java.util.List;

public class TeammateAccountsTask extends TeammateAccountsScreen {

  public AddTeammateAccountTask clickAddTeammateAccount() {
    click(getBtnAddTeammateToYourProfile(), ADD_TEAMMATE_ACCOUNT.getValue());
    return new AddTeammateAccountTask();
  }

  public void goToMyAccountSettingsFromTeammateAccounts() {
    click(getBtnBack(), BACK.getValue());
  }

  /**
   * From the second mini account that needs to be added it has to be from the MiniAccountsScreen
   * We set the limit to expected mini accounts to add
   * Skipping the first as it should have been added on different screen
   *
   * @param numberOfMiniAccounts  Limit number of mini accounts to add
   * @param miniAccounts      List with mini accounts info
   */
  public List<MiniAccountInfo> addTeammateAccounts(int numberOfMiniAccounts,
                                                   List<MiniAccountInfo> miniAccounts) {

    List<MiniAccountInfo> minisToAdd =
      miniAccounts.stream().filter(mini -> !mini.isUsed()).limit(numberOfMiniAccounts - 1).toList();

    for (MiniAccountInfo mini : minisToAdd) {
      clickAddTeammateAccount().completeTeammateAccountInformation(miniAccounts, mini);
    }
    return miniAccounts;
  }
}