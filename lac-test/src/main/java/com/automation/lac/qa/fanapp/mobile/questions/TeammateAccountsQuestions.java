package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.faker.models.userinfo.TeammateInfo;
import com.automation.lac.qa.fanapp.mobile.screens.teammateaccounts.TeammateAccountsScreen;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

@Slf4j
public class TeammateAccountsQuestions extends TeammateAccountsScreen {

  /**
   * Check that the mini accounts added before are displayed
   */
  public void validateMiniAccounts(List<TeammateInfo> miniAccounts) {
    String labelValue = isAndroid() ? "contentDescription" : "label";
    boolean miniAddedCorrectly = false;

    for (TeammateInfo miniAccountInfo : miniAccounts) {
      String name = miniAccountInfo.getFirstName();
      String lastName = miniAccountInfo.getLastName();
      String expectedName = String.format("%s %s", name, lastName);

      DateTimeFormatter miniAccountScreenFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
      String birthDate = miniAccountInfo.getBirthDate().format(miniAccountScreenFormat);

      for (WebElement miniAccountName : getLblTeammateAccountName()) {
        if (miniAccountName.getAttribute(labelValue).contains(expectedName)) {
          int indexOfName = getLblTeammateAccountName().indexOf(miniAccountName);
          WebElement miniAccountDob = getLblTeammateAccountDateOfBirth().get(indexOfName);
          String actualBirthday = miniAccountDob.getAttribute(labelValue);
          if (actualBirthday.contains(birthDate)) {
            miniAddedCorrectly = true;
            break;
          } else {
            log.error(String.format("%s expected birthday [%s] but found [%s]",
              expectedName, birthDate, actualBirthday));
          }
        }
      }
      getSoftAssert().assertTrue(miniAddedCorrectly, "Checking mini accounts were added correctly");
    }
  }

  /**
   * Gets the list of TeamMate to validate if they were added correctly
   *
   * @return an Integer
   */
  public Integer getTheAddedTeammatesNumber() {
    try {
      return getLblTeammateAccountName().size();
    } catch (Exception e) {
      return 0;
    }
  }
}
