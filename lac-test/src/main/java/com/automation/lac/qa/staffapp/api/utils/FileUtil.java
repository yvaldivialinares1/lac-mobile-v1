package com.automation.lac.qa.staffapp.api.utils;

import com.automation.lac.qa.utils.CustomException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUtil {

  /**
   * Read the file and convert it to based64 decoded string value.
   */
  public String getBase64DecodedString(String pathToFile) {
    File file = new File(pathToFile);
    if (!file.exists()) {
      throw new IllegalArgumentException("File not found: " + pathToFile);
    }

    try (FileInputStream imageInFile = new FileInputStream(file);
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = imageInFile.read(buffer)) != -1) {
        byteArrayOutputStream.write(buffer, 0, bytesRead);
      }
      byte[] imageData = byteArrayOutputStream.toByteArray();

      return Base64.getEncoder().encodeToString(imageData);

    } catch (IOException e) {
      throw new CustomException("Error processing file: " + pathToFile, e);
    }
  }
}