package com.mobiquityinc.packer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.model.Pack;

public class PackHelper {

  private static final Logger LOGGER = LogManager.getLogger(ItemHelper.class);

  public static Pack stringToPack(String input) {

    try {
      String[] capacityItems = input.split(" : ");
      Integer capacity = Integer.parseInt(capacityItems[0]) * 100;
      return new Pack(capacity, ItemHelper.stringToItems(capacityItems[1]));
    } catch (NumberFormatException | APIException e) {
      LOGGER.debug("Caught exception!!!", e);
      return null;
    }
  }

  private PackHelper() {}
}
