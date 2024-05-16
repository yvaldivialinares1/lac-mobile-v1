package com.automation.lac.qa.xray.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssueRequest {

  private Fields fields;
}
