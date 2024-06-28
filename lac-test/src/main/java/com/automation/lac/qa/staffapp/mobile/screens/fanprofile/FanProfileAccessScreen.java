package com.automation.lac.qa.staffapp.mobile.screens.fanprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.commons.components.BannerMessageComponent;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.FanProfileAccessIdentityComponent;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.FanProfileAccessVehiclesComponent;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.FanProfileClipperBandComponent;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.ManualAgeVerificationComponent;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.RemoveAgeVerificationAlertComponent;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.UnlinkClipperBandAlertComponent;
import lombok.Getter;

@Getter
public class FanProfileAccessScreen extends MobileBaseScreen {

  private BannerMessageComponent bannerMessageComponent;
  private FanProfileAccessIdentityComponent fanProfileAccessIdentityComponent;
  private FanProfileAccessVehiclesComponent fanProfileAccessVehiclesComponent;
  private FanProfileClipperBandComponent fanProfileClipperBandComponent;
  private RemoveAgeVerificationAlertComponent removeAgeVerificationAlertComponent;
  private UnlinkClipperBandAlertComponent unlinkClipperBandAlertComponent;
  private ManualAgeVerificationComponent manualAgeVerificationComponent;
  private ManageVehicleScreen manageVehicleScreen;
}
