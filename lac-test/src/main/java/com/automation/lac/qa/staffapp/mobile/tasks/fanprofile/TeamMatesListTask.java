package com.automation.lac.qa.staffapp.mobile.tasks.fanprofile;

import static com.automation.lac.qa.staffapp.constants.ContextConstants.TEAMMATE_NOTE;
import static com.automation.lac.qa.staffapp.mobile.stepsdefinitions.CommonsStep.getFanAccountContextDataByAccountType;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.api.models.identity.LinkedAccount;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.TeammatesListScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.TeamMateInfoComponent;
import java.security.SecureRandom;
import java.util.List;

public class TeamMatesListTask extends TeammatesListScreen {

  private static final SecureRandom random = new SecureRandom();

  public void tapAddTeammateButton() {
    click(getBtnAddTeammate(), "add teammate");
  }

  public void tapCloseTeammatesListButton() {
    click(getBtnClose(), "close");
  }

  /**
   * select the random teammate note from a teammates list.
   *
   * @param fanAccountType String indicating the id of fan account.
   */
  public void selectRandomTeamMateListNote(String fanAccountType) {
    List<LinkedAccount> teammatesList =
      getFanAccountContextDataByAccountType(fanAccountType).getLinkedAccounts();
    LinkedAccount teammate = teammatesList.get(random.nextInt(teammatesList.size()));
    getTestContext().set(TEAMMATE_NOTE, teammate);

    TeamMateInfoComponent teammateScreenNote = getTeammatesList().stream()
      .filter(
        mate -> mate.getLblChosenName().getAttribute("label").equals(teammate.getChosenName()))
      .findFirst()
      .orElseThrow();
    click(teammateScreenNote.getLblSurname(), "Random teammates list note");
  }
}
