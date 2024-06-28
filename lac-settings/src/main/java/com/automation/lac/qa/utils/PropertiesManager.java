package com.automation.lac.qa.utils;

import com.automation.lac.qa.environment.Environments;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testng.xml.XmlSuite;

@Slf4j
public class PropertiesManager {

  @Getter
  private final Properties properties = new Properties();
  private final String propertiesFile;
  private static final String PROPERTIES_EXTENSION = ".properties";

  /**
   * Constructs a new PropertiesManager and loads properties from the specified file.
   *
   * @param propertiesFileName the name of the properties file to load
   */
  public PropertiesManager(String propertiesFileName) {
    propertiesFile = propertiesFileName;
    loadProperties();
  }

  /**
   * Retrieves a PropertiesManager instance for a specific repository with the current environment.
   *
   * @param repoShortName the short name of the repository
   * @return a PropertiesManager instance for the repository with the current environment
   */
  public static PropertiesManager getInstance(String repoShortName) {
    return newInstance(repoShortName + "."
      + Environments.byName(System.getProperty("environment")).getName()
      + PROPERTIES_EXTENSION);
  }

  /**
   * Retrieves the default PropertiesManager instance.
   *
   * @return the default PropertiesManager instance
   */
  public static PropertiesManager getInstance() {
    return newInstance("lac.properties");
  }

  /**
   * Retrieves a list of all PropertiesManager instances for the available property files.
   *
   * @return a list of PropertiesManager instances
   */
  public static List<PropertiesManager> getAllInstances() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL resourceUrl = classLoader.getResource("EnvironmentProperties");
    assert resourceUrl != null;
    File dir = new File(resourceUrl.getFile());
    return Arrays.stream(Objects.requireNonNull(dir.listFiles()))
      .map(file -> getInstance(file.getName())).toList();
  }

  /**
   * Creates a new PropertiesManager instance for the specified properties file.
   *
   * @param propertiesFileName the name of the properties file
   * @return a new PropertiesManager instance
   */
  private static PropertiesManager newInstance(String propertiesFileName) {
    return new PropertiesManager(propertiesFileName);
  }

  /**
   * Loads properties from the file associated with this instance.
   */
  private void loadProperties() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    try {
      InputStream input = classLoader.getResourceAsStream(
        "EnvironmentProperties/" + propertiesFile);
      if (input == null) {
        throw new CustomException("Properties file not found: " + propertiesFile);
      }
      properties.load(input);
    } catch (IOException exception) {
      throw new CustomException(exception);
    }
  }

  /**
   * Retrieves the value of the specified property key.
   *
   * @param key the property key to retrieve
   * @return the property value
   */
  public String getProperty(String key) {
    return properties.getProperty(key.trim());
  }

  /**
   * Formats a given string to match the naming conventions of environment variables.
   * It converts all characters to uppercase and replaces periods with underscores.
   *
   * @param variable the string to be formatted
   * @return the formatted string as an environment variable name
   */
  private static String formatAsEnvVar(String variable) {
    return variable.toUpperCase().replace(".", "_");
  }

  /**
   * Retrieves the value of the specified parameter from system environment variables,
   * system properties, or configuration properties in that order.
   *
   * @param key the parameter key
   * @return the parameter value
   * @throws CustomException if the parameter cannot be found
   */
  public static String getParameter(String key) {
    String value = getSystemEnvVariable(key);
    if (value == null) {
      value = getSystemProperty(key);
      if (value == null) {
        value = getConfigProperty(key);
      }
    }
    if (value == null) {
      String errorDetails = buildErrorDetails(key);
      throw new CustomException(errorDetails);
    }
    return value;
  }


  /**
   * Retrieves the value of the specified parameter from system environment variables
   * or configuration properties of a specific repository.
   *
   * @param key           the parameter key
   * @param repoShortName the short name of the repository
   * @return the parameter value
   * @throws CustomException if the parameter cannot be found
   */
  public static String getParameter(String key, String repoShortName) {
    String value = getSystemEnvVariable(key);
    if (value == null) {
      value = getConfigPropertyByInstance(key, repoShortName);
    }
    if (value == null) {
      String errorDetails = buildErrorDetails(key);
      throw new CustomException(errorDetails);
    }
    return value;
  }

  /**
   * Retrieves a property value directly from the system environment variables.
   *
   * @param key the environment variable key
   * @return the environment variable value, or null if not found
   */
  public static String getPropertyFromEnv(String key) {
    String variableValue = System.getenv(key);
    if (variableValue == null) {
      log.warn("Environment variable not found or has null value: {}", key);
      return null;
    }
    return variableValue;
  }

  /**
   * Retrieves a property value from system environment variables, formatted as an environment
   * variable name.
   *
   * @param variable the property name to format and retrieve
   * @return the environment variable value, or null if not found
   */
  public static String getSystemEnvVariable(String variable) {
    return System.getenv(formatAsEnvVar(variable).toUpperCase());
  }

  /**
   * Retrieves a property value from system properties.
   *
   * @param property the property name to retrieve
   * @return the system property value, or null if not found
   */
  public static String getSystemProperty(String property) {
    return System.getProperty(property);
  }

  /**
   * Retrieves a property value from configuration properties.
   *
   * @param property the property name to retrieve
   * @return the configuration property value, or null if not found
   */
  public static String getConfigProperty(String property) {
    return PropertiesManager.getInstance().getProperty(property);
  }

  /**
   * Retrieves a property value from configuration properties of a specific repository.
   *
   * @param property      the property name to retrieve
   * @param repoShortName the short name of the repository
   * @return the configuration property value, or null if not found
   */
  public static String getConfigPropertyByInstance(String property,
                                                   String repoShortName) {
    return PropertiesManager.getInstance(repoShortName).getProperty(property);
  }

  /**
   * Retrieves parameters from XML suite files or falls back to system/env/config properties.
   *
   * @param xmlSuite the XML suite to retrieve parameters from
   * @param property the property name to retrieve
   * @return the property value
   * @throws CustomException if the property value is null or empty
   */
  public static String getXmlParameters(XmlSuite xmlSuite, String property) {
    String value = xmlSuite.getParameter(property);
    if (value == null || value.isBlank()) {
      value = getParameter(property);
    }
    if (value.isBlank()) {
      throw new CustomException("Capability '" + property + "' is NULL or Empty");
    }
    return value;
  }

  /**
   * Builds a detailed error message for a missing parameter.
   *
   * @param key the parameter key that is missing
   * @return the detailed error message
   */
  private static String buildErrorDetails(String key) {
    final String comment = "=<value>\n";
    return String.format("Unable to get '%s' parameter. You can set the property as follows:%n"
        + "option 1: as an environment variable %s%n"
        + "option 2: in the command line parameter as -D%s%n"
        + "option 3: as an input in configuration file: %s%n"
        + "--if the parameter is env specific you should create a key per env",
      key, formatAsEnvVar(key), key, key + comment);
  }
}