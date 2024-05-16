package com.automation.lac.qa.xray.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Step {

  private String action;
  private String data;
  private String result;
}
