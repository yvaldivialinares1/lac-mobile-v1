package com.automation.lac.qa.faker.models;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MiniAccountInfo {
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private String chosenName;
  private boolean used;
}
