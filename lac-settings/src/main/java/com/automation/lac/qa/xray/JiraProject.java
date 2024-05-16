package com.automation.lac.qa.xray;

import lombok.AllArgsConstructor;
import lombok.Getter;


public class JiraProject {

  @Getter
  @AllArgsConstructor
  public enum Project {
    CLIPPERS("CLIPPERS", "https://laclippers.atlassian.net");

    private final String code;
    private final String description;

    /**
     * @param value String
     * @return String
     */
    public static String getJiraUri(String value) {
      for (Project e : values()) {
        if (e.code.equals(value))
          return e.description;
      }
      return "https://laliga.atlassian.net";
    }
  }

  @Getter
  @AllArgsConstructor
  public enum TestExecutionIssue {
    CLIPPERS("CLIPPERS", "10042");

    private final String code;
    private final String description;

    /**
     * @param value String
     * @return String
     */
    public static String getJiraIssueCode(String value) {
      for (TestExecutionIssue e : values()) {
        if (e.code.equals(value))
          return e.description;
      }
      return "10528";
    }
  }
}
