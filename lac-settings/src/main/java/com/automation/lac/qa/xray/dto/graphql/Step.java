package com.automation.lac.qa.xray.dto.graphql;

import java.util.List;
import lombok.Data;

@Data
public class Step {

  private String id;
  private String data;
  private String action;
  private String result;
  private List<Object> attachments;
}
