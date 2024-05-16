package com.automation.lac.qa.xray.dto.graphql;

import java.util.List;
import lombok.Data;

@Data
public class Preconditions {

  private int total;
  private int start;
  private int limit;
  private List<Object> results;
}
