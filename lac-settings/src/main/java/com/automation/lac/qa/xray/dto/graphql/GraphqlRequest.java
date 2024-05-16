package com.automation.lac.qa.xray.dto.graphql;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GraphqlRequest {

  private String query;
  private Variables variables;
}
