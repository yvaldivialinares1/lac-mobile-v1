package com.automation.lac.qa.environment;

import java.util.Arrays;
import lombok.Getter;

/**
 * Enum representing different environment configurations.
 */
@Getter
public enum Environments {
  LOCAL("local"),
  QA("qa"),
  STG("stg");

  private final String name;

  Environments(String name) {
    this.name = name;
  }

  /**
   * Get enum item by name
   *
   * @param name of item
   * @return Environment
   */
  public static Environments byName(String name) {
    return Arrays.stream(Environments.values())
      .filter(environments -> environments.getName().equalsIgnoreCase(name))
      .findFirst()
      .orElse(STG);
  }
}