package com.automation.lac.qa.xray.dto.graphql;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Variables {

  private String jql;
  private int limit;
}
