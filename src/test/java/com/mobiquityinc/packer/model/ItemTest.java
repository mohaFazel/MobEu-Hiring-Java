package com.mobiquityinc.packer.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import com.mobiquityinc.exception.APIException;

public class ItemTest {

  @Test
  public void illegalArgumentTest() {

    assertThrows(
        APIException.class,
        () -> {
          new Item(null, 1, 1);
        });
  }
  
  @Test
  public void legalArgumentTest() throws APIException {
    
    new Item(1, 1, 1);
  }
}
