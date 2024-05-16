package com.automation.lac.qa.magnifai.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a response generated after a creation execution request
 * Postman Collection: <a href="https://console.magnifai.es/help#/helpjuice_production/uploads/upload/image/17755/3783926/1706048532163-MagnifAI%2Bconsole%2Bv.5.1.postman_collection.json">...</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExecutionResponse {
  private String id;
  private String projectId;
  private String name;
  private Long creationTimestamp;
  private Integer passedCount;
  private Integer failedCount;
  private Integer undefinedCount;
}