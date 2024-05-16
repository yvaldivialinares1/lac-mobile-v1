package com.automation.lac.qa.xray.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestInfo {

  private List<Step> steps;
  private String type;
}
