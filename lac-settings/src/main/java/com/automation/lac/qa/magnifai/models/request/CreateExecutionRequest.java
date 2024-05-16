package com.automation.lac.qa.magnifai.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a request to create a new magnifAI execution.
 * Postman Collection: <a href="https://console.magnifai.es/help#/helpjuice_production/uploads/upload/image/17755/3783926/1706048532163-MagnifAI%2Bconsole%2Bv.5.1.postman_collection.json">...</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExecutionRequest {
  private String name;
  private String projectId;
}
