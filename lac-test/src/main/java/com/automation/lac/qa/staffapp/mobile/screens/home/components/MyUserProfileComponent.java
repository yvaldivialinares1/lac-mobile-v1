package com.automation.lac.qa.staffapp.mobile.screens.home.components;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeAvailable;

import com.automation.lac.qa.staffapp.mobile.enums.MyProfileMenuOptions;
import com.automation.lac.qa.utils.CustomException;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class MyUserProfileComponent extends Widget {

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'menu_title' AND label == 'GAME FACE ID'")
  private WebElement btnGameFaceId;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'menu_title' AND label == 'MY IDENTITY PASS'")
  private WebElement btnMyIdentityPass;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'menu_title' AND label == 'MY VEHICLES'")
  private WebElement btnMyVehicle;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'menu_title' AND label == 'LOGOUT'")
  private WebElement btnLogout;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'first_last_name_label'")
  private WebElement lblFirstAndLastName;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'role_label'")
  private WebElement lblRole;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'member_area_label'")
  private WebElement lblMemberArea;

  protected MyUserProfileComponent(WebElement element) {
    super(element);
  }

  /**
   * select selectMenuOption Option
   */
  public void selectMenuOption(MyProfileMenuOptions option) {
    switch (option) {
      case GAME_FACE_ID:
        waitElementWillBeAvailable(getBtnGameFaceId(), 5);
        click(getBtnGameFaceId(), "Menu Game Face ID");
        return;
      case MY_IDENTITY_PASS:
        waitElementWillBeAvailable(getBtnMyIdentityPass(), 5);
        click(getBtnMyIdentityPass(), "Menu My Identity pass");
        return;
      case MY_VEHICLE:
        waitElementWillBeAvailable(getBtnMyVehicle(), 5);
        click(getBtnMyVehicle(), "Menu My Vehicle");
        return;
      case LOGOUT:
        waitElementWillBeAvailable(getBtnLogout(), 5);
        click(getBtnLogout(), "Menu Logout");
        return;
      default:
        throw new CustomException("There is no option to select defined as " + option);
    }
  }
}
