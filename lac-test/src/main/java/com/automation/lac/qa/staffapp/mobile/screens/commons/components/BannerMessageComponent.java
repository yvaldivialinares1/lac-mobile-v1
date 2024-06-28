package com.automation.lac.qa.staffapp.mobile.screens.commons.components;

import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementInvisible;

import com.automation.lac.qa.utils.mobile.WaitActions;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
@Getter
public class BannerMessageComponent extends Widget {

  @iOSXCUITFindBy(id = "banner_message_text")
  private WebElement lblBannerMessage;

  protected BannerMessageComponent(WebElement element) {
    super(element);
  }

  public boolean isBannerMessageDisplayed() {
    return WaitActions.isTheElementVisible(lblBannerMessage, 7);
  }

  public boolean isBannerMessageDisappearedInFewSeconds() {
    return isTheElementInvisible(lblBannerMessage, 7);
  }

  /**
   * Extract banner message text
   */
  public String getBannerMessageText() {
    WaitActions.isTheElementVisible(lblBannerMessage, 7);
    return lblBannerMessage.getAttribute("label");
  }
}