package com.automation.lac.qa.xray.dto.graphql;

import java.util.List;
import lombok.Data;

@Data
public class Result {

  private String issueId;
  private Jira jira;
  private String projectId;
  private TestType testType;
  private List<Step> steps;
  private Preconditions preconditions;
}
