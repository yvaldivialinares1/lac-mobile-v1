package com.automation.lac.qa.staffapp.constants;

import com.automation.lac.qa.utils.PropertiesManager;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceConstants {

  public static final String BACK_BASE_URI =
    PropertiesManager.getParameter("lac.back.base.uri", "staffapp");

  public static final String HEADER_PARKING_API_KEY =
    PropertiesManager.getParameter("header.parking.api.key", "staffapp");

  public static final String CMS_URI =
    PropertiesManager.getParameter("back.cms.url", "staffapp");
}
