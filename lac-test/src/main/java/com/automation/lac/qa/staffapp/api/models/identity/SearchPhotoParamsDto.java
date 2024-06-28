package com.automation.lac.qa.staffapp.api.models.identity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchPhotoParamsDto {
  private String fanPhoto;
  private int maxResult;
  private int scoreThreshold;
}
