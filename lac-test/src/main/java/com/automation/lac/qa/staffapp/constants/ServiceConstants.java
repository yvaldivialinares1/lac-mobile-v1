package com.automation.lac.qa.staffapp.constants;

import com.automation.lac.qa.utils.PropertiesManager;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceConstants {

  private static final String REPO_SHORT_NAME = "staffapp";
  public static final String BACK_BASE_URI =
    PropertiesManager.getParameter("lac.back.base.uri", REPO_SHORT_NAME);
  public static final String HEADER_PARKING_API_KEY =
    PropertiesManager.getParameter("header.parking.api.key", REPO_SHORT_NAME);
  public static final String CMS_URI =
    PropertiesManager.getParameter("back.cms.url", REPO_SHORT_NAME);
  public static final String STAFF_ID = "65eb72fa-761b-4d7e-99b7-dccda683f0c7";
}
