package com.automation.lac.qa.xray.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fields {

  private Project project;
  private String summary;
  private String description;
  private IssueType issuetype;
}
