package com.automation.lac.qa.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;
import org.testng.util.Strings;

@UtilityClass
public class StringOperations {

  /**
   * Validate match regex expression
   *
   * @param regex Expression
   * @param input string to test
   * @return Returns "true" if a given String("input") matches a given regular expression
   */
  public static boolean validateRegex(final String regex, final String input) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);
    return matcher.find();
  }

  /**
   * Validate match regex expression
   *
   * @param regex Expression
   * @param input string to test
   * @return String
   */
  public static List<String> regexReader(String regex, String input) {
    List<String> stringList = new LinkedList<>();
    // Compile and use regular expression
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);

    //Get the matched strings
    while (matcher.find()) {
      stringList.add(matcher.group(0));
    }
    return stringList;
  }

  /**
   * Clear secrets
   */
  public static void clearSecrets() {
    PropertiesManager.getAllInstances().forEach(propertiesManager ->
      propertiesManager.getProperties().entrySet().stream()
        .filter(properties -> (properties.getKey().toString().contains("secret")
          || properties.getKey().toString().contains("password"))
          && !properties.getValue().toString().contains("#{")
          && !Strings.isNullOrEmpty(properties.getValue().toString()))
        .forEach(property ->
          clearSecret(property.getValue().toString())
        )
    );

    Allure.step("Secrets was deleted successful.");
  }

  /**
   * Clear secrets
   *
   * @param secretValue to clear
   */
  public static void clearSecret(String secretValue) {
    File dir = new File("allure-results/");
    Arrays.stream(Objects.requireNonNull(dir.listFiles()))
      .filter(File::isFile)
      .filter(dirOrFile -> !dirOrFile.getName().contains("attachment"))
      .forEach(file -> {
        String content;
        try {
          content = IOUtils.toString(new FileInputStream(file), UTF_8);
          content = content.replaceAll(secretValue, "********");
          IOUtils.write(content, new FileOutputStream(file), UTF_8);
        } catch (IOException e) {
          Allure.step("Error clearing secrets", Status.FAILED);
        }
      });
  }
}