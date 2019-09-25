package com.mobiquityinc.packer;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Pack;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.mobiquityinc.exception.APIException;

public class PackerTest {

  private static final String SAMPLE_INVALID_CONTENT =
      "81 : (1,53.38,45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
  private static final String SAMPLE_VALID_CONTENT =
      "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

  @Test
  public void emptyFilePathTest() {

    assertThrows(
        APIException.class,
        () -> {
          Packer.pack("    ");
        });
  }

  @Test
  public void fileInvalidEncodingTest() throws IOException {

    Path path = Files.createTempFile(UUID.randomUUID().toString(), ".txt");
    assertThrows(
        APIException.class,
        () -> {
          Packer.validateInputFile(path.toFile());
        });
    path.toFile().deleteOnExit();
  }

  @ParameterizedTest
  @ValueSource(strings = {"0", "0.0", "33.34", "288.620", "259.000"})
  public void floatRegexTest(String floatValue) {

    final boolean matches = Pattern.compile(Packer.FLOAT_REGEX).matcher("123").matches();
    Assertions.assertTrue(matches);
  }

  @Test
  public void invalidFileContentTest() throws IOException {

    Path path = Files.createTempFile(UUID.randomUUID().toString(), ".txt");
    FileUtils.writeStringToFile(
        path.toFile(), SAMPLE_INVALID_CONTENT, StandardCharsets.UTF_8.toString());
    assertThrows(
        APIException.class,
        () -> {
          Packer.readFileContent(path);
        });
    path.toFile().deleteOnExit();
  }

  @Test
  public void invalidFilePathTest() {

    assertThrows(
        APIException.class,
        () -> {
          Packer.validateInputFile(new File(randomUUID().toString()));
        });
  }

  @Test
  public void nullFilePathTest() {

    assertThrows(
        APIException.class,
        () -> {
          Packer.pack(null);
        });
  }

  @Test
  public void validFileContentTest() throws IOException, APIException {

    Path path = Files.createTempFile(UUID.randomUUID().toString(), ".txt");
    FileUtils.writeStringToFile(
        path.toFile(), SAMPLE_VALID_CONTENT, StandardCharsets.UTF_8.toString());
    Packer.pack(path.toFile().getAbsolutePath());
    path.toFile().deleteOnExit();
  }

  @Test
  public void solveTest() {
      try {
          Packer.solve(8100, getItems());
          Packer.solve(800, getSecondItems());
          Packer.solve(7500, getThirdItems());
          Packer.solve(5600, getFourthItems());
      } catch (APIException e) {
          e.printStackTrace();
      }
  }

    private List<Item> getFourthItems() throws APIException {
        List<Item> result = new ArrayList<>();
        result.add(new Item(1, 9072, 13));
        result.add(new Item(2, 3380, 40));
        result.add(new Item(3, 4315, 10));
        result.add(new Item(4, 3797, 16));
        result.add(new Item(5, 4681, 36));
        result.add(new Item(6, 4877, 79));
        result.add(new Item(7, 8180, 45));
        result.add(new Item(8, 1936, 79));
        result.add(new Item(9, 676, 64));
        return result;
    }

    private List<Item> getThirdItems() throws APIException {
        List<Item> result = new ArrayList<>();
        result.add(new Item(1, 8531, 29));
        result.add(new Item(2, 1455, 74));
        result.add(new Item(3, 3980, 16));
        result.add(new Item(4, 2624, 55));
        result.add(new Item(5, 6369, 52));
        result.add(new Item(6, 7625, 75));
        result.add(new Item(7, 6002, 74));
        result.add(new Item(8, 9318, 35));
        result.add(new Item(9, 8995, 78));
        return result;
    }

    private List<Item> getSecondItems() throws APIException {
        List<Item> result = new ArrayList<>();
        result.add(new Item(1, 1530, 34));
        return result;
    }

    private List<Item> getItems() throws APIException {
      List<Item> result = new ArrayList<>();
      result.add(new Item(1, 5338, 45));
      result.add(new Item(2, 8862, 98));
      result.add(new Item(3, 7848, 3));
      result.add(new Item(4, 7230, 76));
      result.add(new Item(5, 3018, 9));
      result.add(new Item(6, 4634, 48));
      return result;
    }
}
