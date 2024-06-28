package com.automation.lac.qa.fanapp.mobile.screens.educationals;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components.EnableCameraWidget;
import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components.GameFaceIdDataWidget;
import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class GameFaceIdEducationalScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "descriptionContains(\"GameFaceId,\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_image' AND label CONTAINS 'game face'")
  private WebElement imgGameFaceIdLogo;

  @AndroidFindBy(uiAutomator = "resourceId(\"GameFaceEducationalScreentvTitle\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_label_title' AND label CONTAINS 'game face'")
  private WebElement lblSetGameFaceIdTitle;

  @AndroidFindBy(uiAutomator = "resourceId(\"GameFaceEducationalScreenbtnPrimary\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'SET MY GAME FACE ID' AND type CONTAINS 'Button'")
  private WebElement btnSetGameFaceId;

  @AndroidFindBy(uiAutomator = "description(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND  type == 'XCUIElementTypeButton'")
  private WebElement btnBack;

  @AndroidFindBy(uiAutomator = "text(\"Skip\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_navigation_option_skip_with_text'")
  private WebElement btnSkip;

  @AndroidFindBy(id = "tvPrimaryCta")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'CONTINUE' AND type == 'XCUIElementTypeButton'")
  private WebElement btnContinue;

  @AndroidFindBy(id = "btnSAVE MY GAME FACE ID")
  @iOSXCUITFindBy(iOSNsPredicate = "TBD")
  private WebElement saveGameFaceId;

  @AndroidFindBy(id = "com.android.permissioncontroller:id/grant_dialog")
  @iOSXCUITFindBy(xpath = "TBD")
  private EnableCameraWidget enableCameraWidget;

  @AndroidFindBy(id = "modalContentView")
  @iOSXCUITFindBy(xpath = "TBD")
  private GameFaceIdDataWidget gameFaceIdDataWidget;

  public String getUnavailableGameFaceImgXpath() {
    String iosXpath = "//XCUIElementTypeOther[contains(@name,'faceId_unavailable_perk')]";
    return isAndroid() ? "//android.view.View[contains(@content-desc,'UnavailableStep']" : iosXpath;
  }

  public String getErrorXpath() {
    String iosXpath = "//XCUIElementTypeStaticText[contains(@name,'OOPS!')]";
    return isAndroid() ? "//android.widget.TextView[contains(@text,'OOPS!')]" : iosXpath;
  }
}