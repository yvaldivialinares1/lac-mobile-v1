package com.automation.lac.qa.magnifai.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlexCompareDetails {
  private Integer noiseFilter;
  private Integer minSimilarity;
  private Integer obtainedSimilarity;
  private String baselineImage;
  private String inputImage;
  private String resultImage;
}