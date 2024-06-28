package com.automation.lac.qa.fanapp.api.models;

import com.automation.lac.qa.utils.CustomException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Abstract class for handling common functionality for reading and deserializing JSON files.
 *
 * @param <T> The type of the specific credentials file class.
 */
public abstract class AbstractJsonFileReader<T> {

  protected abstract String getFilePath();

  protected abstract TypeReference<T> getTypeReference();

  private String readFileContent() throws IOException {
    return new String(Files.readAllBytes(Paths.get(getFilePath())));
  }

  protected T parseFile(String jsonContent) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(jsonContent, getTypeReference());
  }

  protected T fromJsonFile() {
    try {
      String jsonContent = readFileContent();
      return parseFile(jsonContent);
    } catch (IOException e) {
      throw new CustomException("Error reading or deserializing the JSON file.", e);
    }
  }
}