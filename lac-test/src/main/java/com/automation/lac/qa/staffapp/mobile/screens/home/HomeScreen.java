package com.automation.lac.qa.staffapp.mobile.screens.home;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.commons.components.NavigationBarComponent;
import com.automation.lac.qa.staffapp.mobile.screens.commons.components.NavigationHeaderComponent;
import com.automation.lac.qa.staffapp.mobile.screens.home.components.FanBarComponent;
import com.automation.lac.qa.staffapp.mobile.screens.home.components.MyUserProfileComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class HomeScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "navigation_header_logo")
  private WebElement headerLogo;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name = 'banner_message_text']")
  private WebElement lblBannerMessage;

  private NavigationBarComponent navigationBarComponent;
  private NavigationHeaderComponent navigationHeaderComponent;
  private FanBarComponent fanBarComponent;
  private MyUserProfileComponent myUserProfileComponent;
}