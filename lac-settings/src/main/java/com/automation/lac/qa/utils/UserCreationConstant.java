package com.automation.lac.qa.utils;

import static com.automation.lac.qa.driver.AppiumConstants.getOptionalParameter;
import static com.automation.lac.qa.faker.enums.FanType.ADULT;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.automation.lac.qa.faker.enums.FanType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class UserCreationConstant {

  public static final FanType FAN_TYPE =
    FanType.valueOf(
      getOptionalParameterOrDefault("fanapp.usercreation.usertype", ADULT.name()));
  public static final Integer FANS_NUMBER =
    Integer.parseInt(getOptionalParameterOrDefault("fanapp.usercreation.number", "10"));
  public static final boolean GAME_FACE_ID =
    Boolean.parseBoolean(getOptionalParameterOrDefault("fanapp.usercreation.gamefaceid", "false"));
  public static final boolean IDENTITY_PASS =
    Boolean.parseBoolean(
      getOptionalParameterOrDefault("fanApp.usercreation.identitypass", "false"));
  public static final boolean PAYMENT_METHOD =
    Boolean.parseBoolean(
      getOptionalParameterOrDefault("fanapp.usercreation.paymentmethod", "false"));
  public static final List<Integer> TEAMMATE =
    getOptionalParametersOrDefault("fanapp.usercreation.teammate", "3, 50, 12, 18, 21");
  public static final Integer VEHICLE =
    Integer.parseInt(
      getOptionalParameterOrDefault("fanapp.usercreation.vehicle", "5"));
  public static final boolean TICKET_PURCHASE =
    Boolean.parseBoolean(
      getOptionalParameterOrDefault("fanapp.usercreation.ticket", "false"));

  /**
   * Converts a string in a list of elements
   *
   * @param parameterName The name of the parameter to retrieve
   * @param defaultValue  The default value to return if the parameter is not found
   * @return A list of ages in Integer type
   */
  private static List<Integer> getOptionalParametersOrDefault(String parameterName,
                                                              String defaultValue) {
    String reformatValue = defaultValue.replaceAll("[^0-9,]+", EMPTY);

    return reformatValue.equals(EMPTY) ? Collections.emptyList() : Arrays.asList(
        getOptionalParameterOrDefault(parameterName, reformatValue).split(","))
      .stream()
      .filter(value -> value != EMPTY)
      .map(age -> Integer.parseInt(age)).toList();
  }

  /**
   * Retrieves an optional parameter from the properties manager or returns the default value if
   * not found.
   *
   * @param parameterName The name of the parameter to retrieve.
   * @param defaultValue  The default value to return if the parameter is not found.
   * @return The value of the parameter or the default value if not present.
   */
  private static String getOptionalParameterOrDefault(String parameterName, String defaultValue) {
    return Objects.requireNonNullElse(getOptionalParameter(parameterName), defaultValue);
  }
}