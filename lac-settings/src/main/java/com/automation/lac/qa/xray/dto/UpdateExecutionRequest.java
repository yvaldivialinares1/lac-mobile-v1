package com.automation.lac.qa.xray.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateExecutionRequest {

  private String testExecutionKey;
  private Info info;
  private List<Test> tests;
}
