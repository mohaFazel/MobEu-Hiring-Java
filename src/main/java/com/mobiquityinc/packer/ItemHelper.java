package com.mobiquityinc.packer;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.model.Item;

public class ItemHelper {

  private static final Logger LOGGER = LogManager.getLogger(ItemHelper.class);

  public static Item stringToItemConverter(String item) {
    String[] indexWieghtCost = item.split(",");
    try {
      return new Item(
          parseInt(indexWieghtCost[0]),
          (int)(parseFloat(indexWieghtCost[1]) * 100),
          parseInt(indexWieghtCost[2]));
    } catch (NumberFormatException | APIException e) {
      LOGGER.debug("Caught exception!!!", e);
      return null;
    }
  }

  public static List<Item> stringToItems(String input) {

    List<String> items = Arrays.asList(input.split("\\s"));
    return items
        .stream()
        .map(item -> item.replaceAll("[\\(|\\)|€]", ""))
        .map(ItemHelper::stringToItemConverter)
        .collect(toList());
  }

  private ItemHelper() {}
}
