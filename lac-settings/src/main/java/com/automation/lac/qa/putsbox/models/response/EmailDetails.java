package com.automation.lac.qa.putsbox.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the details of an email message.
 * This class encapsulates various attributes of an email such as ID, headers, sender, recipient(s),
 * and content.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailDetails {
  private String id;
  private String headers;
  @JsonProperty("from_email")
  private String fromEmail;
  @JsonProperty("from_name")
  private String fromName;
  private List<String> to;
  private String email;
  private String subject;
  private String text;
  private String html;
  private List<Object> attachments;
}