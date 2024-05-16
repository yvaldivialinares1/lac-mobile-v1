package com.automation.lac.qa.magnifai.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a request to generate a valid token.
 * Postman Collection: <a href="https://console.magnifai.es/help#/helpjuice_production/uploads/upload/image/17755/3783926/1706048532163-MagnifAI%2Bconsole%2Bv.5.1.postman_collection.json">...</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTokenRequest {
  @JsonProperty("client_id")
  private String clientId;
  private String username;
  private String password;
  @JsonProperty("grant_type")
  private String grantType;
}
