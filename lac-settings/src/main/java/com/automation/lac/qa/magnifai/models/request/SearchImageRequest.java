package com.automation.lac.qa.magnifai.models.request;

import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class represents a request to search an image within an image.
 * Postman Collection: <a href="https://console.magnifai.es/help#/helpjuice_production/uploads/upload/image/17755/3783926/1706048532163-MagnifAI%2Bconsole%2Bv.5.1.postman_collection.json">...</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchImageRequest {
  private String testName;
  private String executionId;
  private File parentImage;
  private File childImage;
  private String minSimilarity;
  private String testMode;
}
