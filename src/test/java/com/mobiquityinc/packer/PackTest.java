package com.mobiquityinc.packer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Pack;

public class PackTest {

  @Test
  public void illegalArgumentCapacityNegativeTest() {

    assertThrows(
        APIException.class,
        () -> {
          new Pack(-1, new ArrayList<>());
        });
  }

  @Test
  public void illegalArgumentCapacityNullTest() {

    assertThrows(
        APIException.class,
        () -> {
          new Pack(null, new ArrayList<>());
        });
  }

  @Test
  public void illegalArgumentCapacityZeroTest() {

    assertThrows(
        APIException.class,
        () -> {
          new Pack(0, new ArrayList<>());
        });
  }

  @Test
  public void illegalArgumentItemsEmptyTest() {

    assertThrows(
        APIException.class,
        () -> {
          new Pack(1, new ArrayList<>());
        });
  }

  @Test
  public void illegalArgumentItemsNullTest() {

    assertThrows(
        APIException.class,
        () -> {
          new Pack(1, null);
        });
  }

  @Test
  public void legalArgumentTest() throws APIException {

    new Pack(1, Arrays.asList(new Item(1, 1, 1)));
  }
}
