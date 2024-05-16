package com.automation.lac.qa.magnifai.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration defining the endpoints for the MagnifAi API.
 * Each enum constant represents a specific endpoint path used
 * to interact with the MagnifAi web services.
 */

@Getter
@RequiredArgsConstructor
public enum MagnifAiEndPoints {
  GET_TOKEN("/realms/aut/protocol/openid-connect/token"),
  CREATE_EXECUTION("/aut-dashboard-api/executions/"),
  IMAGE_COMPARISON("/aut-magnifai-api/image-comparison"),
  FLEXIBLE_IMAGE_COMPARISON("/aut-magnifai-api/flex-compare"),
  SEARCH_IMAGE("/aut-magnifai-api/search"),
  FLEXIBLE_SEARCH_IMAGE("/aut-magnifai-api/flex-search"),
  LOCATE_IMAGE("/aut-magnifai-api/locate"),
  FLEXIBLE_LOCATE_IMAGE("/aut-magnifai-api/flex-locate"),
  ASSETS("/aut-assets-api/assets/");

  private final String text;

  @Override
  public String toString() {
    return text;
  }
}
