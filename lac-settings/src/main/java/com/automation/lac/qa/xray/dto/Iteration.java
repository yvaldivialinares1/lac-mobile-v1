package com.automation.lac.qa.xray.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Iteration {

  private String status;
  private List<Parameter> parameters;
}
