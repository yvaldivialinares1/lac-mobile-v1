package com.automation.lac.qa.staffapp.api.models.cms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClosingIssueResponse {
  private Data data;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class Data {
    private ClosingIssueReasonsByPath closingIssueReasonsList;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class ClosingIssueReasonsByPath {
    private List<Item> items;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class Item {
    private String closingIssueTitle;
    private List<String> issueType;
  }
}
