package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.MINI_ACCOUNTS_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.models.MiniAccountInfo;
import com.automation.lac.qa.fanapp.mobile.questions.TeammateAccountsQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.teammateaccounts.AddTeammateAccountTask;
import com.automation.lac.qa.fanapp.mobile.tasks.teammateaccounts.TeammateAccountsEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.teammateaccounts.TeammateAccountsTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.util.List;

public class TeammateAccountsStepsDefinition {

  private final AddTeammateAccountTask addTeammateAccountTask = new AddTeammateAccountTask();
  private final TeammateAccountsTask teammateAccountsTask = new TeammateAccountsTask();
  private final TeammateAccountsEducationalTask teammateAccountsEducationalTask =
    new TeammateAccountsEducationalTask();
  private final TeammateAccountsQuestions teammateQuestions = new TeammateAccountsQuestions();

  /**
   * Add a given number of mini accounts
   * When user doesn't have mini accounts the adding process begins in Educational screen
   * From the second mini account it has to be added from MiniAccountsScreen
   *
   * @param numberOfMiniAccounts integer
   */
  @And("the user adds {int} mini account profile")
  public void theUserAddsAGivenNumberOfMiniAccounts(int numberOfMiniAccounts) {
    List<MiniAccountInfo> miniAccounts = getTestContext().get(MINI_ACCOUNTS_INFO.name());
    teammateAccountsEducationalTask.clickAddTeammateAccount();
    var miniAccountToAdd = miniAccounts.stream().findFirst().orElseThrow();
    miniAccounts =
      addTeammateAccountTask.completeTeammateAccountInformation(miniAccounts, miniAccountToAdd);
    miniAccounts = teammateAccountsTask.addTeammateAccounts(numberOfMiniAccounts, miniAccounts);
    getTestContext().set(MINI_ACCOUNTS_INFO.name(), miniAccounts);
  }

  @Then("the user validates the mini account detail added before")
  public void theUserValidatesMiniAccountsAddedBefore() {
    List<MiniAccountInfo> miniAccounts = getTestContext().get(MINI_ACCOUNTS_INFO.name());
    teammateQuestions.validateMiniAccounts(miniAccounts);
  }
}