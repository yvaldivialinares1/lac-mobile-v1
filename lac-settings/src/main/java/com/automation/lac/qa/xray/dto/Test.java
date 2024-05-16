package com.automation.lac.qa.xray.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonFilter("customTest")
public class Test {

  private String testKey;
  private String status;
  private String comment;
  private TestInfo testInfo;
  private List<Iteration> iterations;

}
