package com.automation.lac.qa.xray.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Info {

  private String project;
  private String summary;
  private String description;
}
