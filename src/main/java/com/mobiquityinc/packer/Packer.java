package com.mobiquityinc.packer;

import static com.glaforge.i18n.io.CharsetToolkit.guessEncoding;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mobiquityinc.packer.model.Item;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.model.Pack;

public class Packer {

  private Packer() {}

  private static final String EXCEPTION = "Exception has been thrown";
  static final String FLOAT_REGEX = "([0-9]+([.][0-9]*)?|[.][0-9]+)";
  private static final Logger LOGGER = LogManager.getLogger(Packer.class);
  private static final Pattern PATTERN =
      Pattern.compile("[0-9]+\\s:\\s(\\(([0-9]+," + FLOAT_REGEX + ",€[0-9]+\\)\\s?))+");

  public static String pack(String filePath) throws APIException {

    if (filePath == null || filePath.trim().length() == 0) {
      throw new APIException(
          "Input file path is either null or empty.", new IllegalArgumentException());
    }

    final Path path = Paths.get(filePath);
    validateInputFile(path.toFile());
    List<String> packs = readFileContent(path);
    final List<Pack> collect = packs.stream().map(PackHelper::stringToPack).collect(toList());
    return StringUtils.join(collect.stream().map(pack -> solve(pack.getCapacity(), pack.getItems())).collect(toList()), "\n");
  }

  static String solve(Integer capacity, List<Item> items) {
    int[][] matrix = new int[items.size() + 1][capacity + 1];

    // At the beginning we should initialize the first column
    for (int i = 0; i <= capacity; i++)
      matrix[0][i] = 0;

    // Then we will iterate over items
    for (int item = 1; item <= items.size(); item++) {
      for (int weight = 0; weight <= capacity; weight++) {
        if (items.get(item - 1).getWeight() > weight)
          matrix[item][weight] = matrix[item-1][weight];
        else
          matrix[item][weight] = Math.max(matrix[item-1][weight], matrix[item-1][weight - items.get(item - 1).getWeight()]
                  + items.get(item - 1).getCost());
      }
    }

    int res = matrix[items.size()][capacity];
    int w = capacity;
    List<Item> solution = new ArrayList<>();

    for (int i = items.size(); i > 0  &&  res > 0; i--) {
      if (res != matrix[i-1][w]) {
        solution.add(items.get(i-1));
        // we remove items value and weight
        res -= items.get(i-1).getCost();
        w -= items.get(i-1).getWeight();
      }
    }
    String result;
    if (solution.size() > 0) {
      result = StringUtils.join(solution.stream().map(Item::getIndex).map(String::valueOf).collect(toList()), ",");
    } else {
      result = "-";
    }
    return result;
  }

  public static List<String> readFileContent(Path path) throws APIException {

    try {
      final List<String> content = Files.readAllLines(path);
      if (content.isEmpty()) {
        throw new APIException("File contains no line!!!");
      }
      final Optional<Matcher> invalidLine =
          content.stream().map(PATTERN::matcher).filter(match -> !match.matches()).findAny();
      if (invalidLine.isPresent()) {
        throw new APIException("Invalid file content!!!");
      }
      return content;
    } catch (IOException e) {
      LOGGER.debug(EXCEPTION, e);
      throw new APIException(EXCEPTION, e);
    }
  }

  public static void validateInputFile(File file) throws APIException {
    try {
      LOGGER.debug("Recieved file path {} to process", file);
      if (!file.exists()) {
        throw new APIException("The specified file does not exists.");
      }
      if (!guessEncoding(file, 4096, UTF_8).equals(UTF_8)) {
        throw new APIException("File encoding is not UTF-8");
      }
    } catch (Exception e) {
      LOGGER.debug(EXCEPTION, e);
      throw new APIException(EXCEPTION, e);
    }
  }
}
