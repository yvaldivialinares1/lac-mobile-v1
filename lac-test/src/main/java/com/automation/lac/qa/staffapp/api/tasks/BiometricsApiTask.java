package com.automation.lac.qa.staffapp.api.tasks;

import static java.util.Arrays.asList;

import com.automation.lac.qa.staffapp.api.models.identity.AccountPhotoResponseDto;
import com.automation.lac.qa.staffapp.api.models.identity.RegisterBiometricRequestDto;
import com.automation.lac.qa.staffapp.api.models.identity.RegisterBiometricResponseDto;
import com.automation.lac.qa.staffapp.api.models.identity.SearchPhotoParamsDto;
import com.automation.lac.qa.staffapp.api.services.identity.BiometricsService;
import com.automation.lac.qa.staffapp.api.utils.FileUtil;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BiometricsApiTask {
  private final Deque<String> queue = new ConcurrentLinkedDeque<>(
    asList("src/test/resources/test_data/staffapp/file/fan_photo_2.png",
      "src/test/resources/test_data/staffapp/file/fan_photo_1.png"));

  /**
   * Delete fan account biometric if exists.
   */
  private static void deleteFanBiometricIfExists(
    RegisterBiometricRequestDto registerBiometricRequestDto) {
    SearchPhotoParamsDto searchPhotoDto =
      SearchPhotoParamsDto.builder()
        .fanPhoto(registerBiometricRequestDto.getFanPhoto())
        .scoreThreshold(500)
        .maxResult(10)
        .build();

    List<AccountPhotoResponseDto> intuitDomeAccountsByPhotoList =
      BiometricsService.findIntuitDomeAccountsByPhoto(searchPhotoDto);

    if (intuitDomeAccountsByPhotoList != null && !intuitDomeAccountsByPhotoList.isEmpty()) {
      intuitDomeAccountsByPhotoList.forEach(
        account -> BiometricsService.deleteBiometric(account.getId()));
      //TODO: currently it take the time to get game face deleted
      // -> delete this when api behaviour is fixed
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Register new intuit Dome Account biometric of fan.
   *
   * @return RegisterBiometricResponseDto data
   */
  public static RegisterBiometricResponseDto registerIntuitDomeAccountBiometrics(
    String intuitDomeAccountId) {
    RegisterBiometricRequestDto registerBiometricRequestDto =
      RegisterBiometricRequestDto.builder()
        .accountId(intuitDomeAccountId)
        .fanPhoto(FileUtil.getBase64DecodedString(queue.pollFirst()))
        .memberType("FAN")
        .build();

    deleteFanBiometricIfExists(registerBiometricRequestDto);
    return BiometricsService.registerBiometrics(registerBiometricRequestDto);
  }
}
