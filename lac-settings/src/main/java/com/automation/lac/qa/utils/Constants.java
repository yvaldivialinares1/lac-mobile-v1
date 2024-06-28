package com.automation.lac.qa.utils;

import com.automation.lac.qa.driver.AppiumConstants;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

  public static final String PROJECT_ID = PropertiesManager.getParameter("project.id");
  public static final boolean JENKINS_ENABLED =
    Boolean.parseBoolean(PropertiesManager.getParameter("arqqa.automation"));
  public static final boolean XRAY_ENABLED =
    Boolean.parseBoolean(PropertiesManager.getParameter("framework.xray.enabled"));
  public static final String XRAY_CLIENT_ID =
    PropertiesManager.getParameter("framework.xray.clientId");
  public static final String XRAY_CLIENT_SECRET =
    PropertiesManager.getParameter("framework.xray.clientSecret");
  public static final String JIRA_USER_ID = PropertiesManager.getParameter("framework.jira.email");
  public static final String JIRA_API_TOKEN =
    PropertiesManager.getParameter("framework.jira.apiToken");
  public static final String MAGNIFAI_USERNAME =
    PropertiesManager.getParameter("framework.magnifai.username");
  public static final String MAGNIFAI_PASSWORD =
    PropertiesManager.getParameter("framework.magnifai.password");
  public static final String IPAD_DEVICE_NAME = "iPad";
  public static final boolean APP_MOCKS =
    Boolean.parseBoolean(
      AppiumConstants.getOptionalParameterOrDefault("fanApp.mocks", "false"));

  public static final boolean IS_GAME_ENABLED = Boolean.parseBoolean(PropertiesManager
    .getParameter("lac.games.enabled","fanapp"));
  public static final boolean IS_EVENT_ENABLED = Boolean.parseBoolean(PropertiesManager
    .getParameter("lac.events.enabled","fanapp"));

}