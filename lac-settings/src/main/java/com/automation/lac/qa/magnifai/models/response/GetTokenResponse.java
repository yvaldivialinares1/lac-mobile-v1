package com.automation.lac.qa.magnifai.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a response generated after a token request
 * Postman Collection: <a href="https://console.magnifai.es/help#/helpjuice_production/uploads/upload/image/17755/3783926/1706048532163-MagnifAI%2Bconsole%2Bv.5.1.postman_collection.json">...</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTokenResponse {
  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("expires_in")
  private Integer expiresIn;
  @JsonProperty("refresh_expires_in")
  private Integer refreshExpiresIn;
  @JsonProperty("refresh_token")
  private String refreshToken;
  @JsonProperty("token_type")
  private String tokenType;
  @JsonProperty("not-before-policy")
  private Integer notBeforePolicy;
  @JsonProperty("session_state")
  private String sessionState;
  private String scope;
}