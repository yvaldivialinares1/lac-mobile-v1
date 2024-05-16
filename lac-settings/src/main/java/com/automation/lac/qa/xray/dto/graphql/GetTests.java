package com.automation.lac.qa.xray.dto.graphql;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTests {

  private int total;
  private int start;
  private int limit;
  private List<Result> results;
}
