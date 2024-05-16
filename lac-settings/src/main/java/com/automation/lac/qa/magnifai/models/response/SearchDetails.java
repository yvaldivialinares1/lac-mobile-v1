package com.automation.lac.qa.magnifai.models.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDetails {
  private String childImageRef;
  private String parentImageRef;
  private String resultImageRef;
  private Integer minSimilarity;
  private Integer totalMatches;
  private List<MatchDetails> matches;
}