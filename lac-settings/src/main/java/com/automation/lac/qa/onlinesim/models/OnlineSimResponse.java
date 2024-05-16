package com.automation.lac.qa.onlinesim.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * Represents the response structure from the OnlineSim API.
 * It holds information about countries, numbers, and messages received from the API.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OnlineSimResponse {
  private List<Country> countries;
  private Map<String, NumberInfo> numbers;
  private MessageContainer messages;

  /**
   * Represents country information as part of the OnlineSim API response.
   */
  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Country {
    @JsonProperty("country")
    private int countryCode;
    @JsonProperty("country_original")
    private String countryName;
  }

  /**
   * Represents number information, including country and full number details.
   */
  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class NumberInfo {
    private int country;
    @JsonProperty("country_original")
    private String countryName;
    @JsonProperty("full_number")
    private String fullNumber;
    @JsonProperty("is_archive")
    private boolean isArchive;

  }

  /**
   * Container for holding a list of message data.
   */

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class MessageContainer {
    private List<Message> data;
  }

  /**
   * Represents an individual message, including metadata such as ID, text, and creation time.
   */
  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Message {
    private long id;
    private String text;
    @JsonProperty("in_number")
    private String inNumber;
    @JsonProperty("my_number")
    private long myNumber;
    @JsonProperty("created_at")
    private String createdAt;
    private String code;
  }
}
