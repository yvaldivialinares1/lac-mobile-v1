package com.automation.lac.qa.fanapp.api.utils;

import com.automation.lac.qa.utils.PropertiesManager;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceConstants {

  public static final String
    BACK_BASE_URI = PropertiesManager.getParameter("lac.back.base.uri", "fanapp");
}
