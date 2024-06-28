package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.MINI_ACCOUNTS_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.models.userinfo.TeammateInfo;
import com.automation.lac.qa.fanapp.mobile.questions.TeammateAccountsQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.teammateaccounts.AddTeammateAccountTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.util.List;

public class TeammateAccountsStepsDefinition {

  private final AddTeammateAccountTask addTeammateAccountTask = new AddTeammateAccountTask();
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
    List<TeammateInfo> miniAccountsList = getTestContext().get(MINI_ACCOUNTS_INFO.name());
    List<TeammateInfo> miniAccountsToAdd =
      miniAccountsList.stream().limit(numberOfMiniAccounts).toList();

    addTeammateAccountTask.addTeammateAccountInformation(miniAccountsToAdd);
    getTestContext().set(MINI_ACCOUNTS_INFO.name(), miniAccountsToAdd);
  }

  @Then("the user validates the mini account detail added before")
  public void theUserValidatesMiniAccountsAddedBefore() {
    List<TeammateInfo> miniAccounts = getTestContext().get(MINI_ACCOUNTS_INFO.name());
    teammateQuestions.validateMiniAccounts(miniAccounts);
  }
}