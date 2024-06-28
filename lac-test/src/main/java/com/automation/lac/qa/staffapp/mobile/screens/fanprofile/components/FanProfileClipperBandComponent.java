package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class FanProfileClipperBandComponent extends Widget {

  @iOSXCUITFindBy(id = "clipperband_id")
  private WebElement clipperBandId;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'unlink'")
  private WebElement btnUnlink;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='link_manually' and @visible='true' "
    + "or @name='LINK MANUALLY' and @visible='true']")
  private WebElement btnLinkManually;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='link_nfc' or @name='LINK NFC']")
  private WebElement btnLinkNfc;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='no_clipperband_linked_yet' "
    + "or @name='no_linked_clipperband_title' or @name='There is no Clipperband linked yet']")
  private WebElement msgNoClipperBandLinked;

  protected FanProfileClipperBandComponent(WebElement element) {
    super(element);
  }
}
