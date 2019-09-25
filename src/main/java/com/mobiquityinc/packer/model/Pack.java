package com.mobiquityinc.packer.model;

import java.util.List;
import java.util.Objects;
import com.mobiquityinc.exception.APIException;

public class Pack {

  private Integer capacity;

  private List<Item> items;

  public Pack(Integer capacity, List<Item> items) throws APIException {

    if (capacity == null || items == null || capacity.compareTo(0) <= 0 || items.size() == 0) {
      throw new APIException(
          "Unacceptable constructor argument value", new IllegalArgumentException());
    }
    this.capacity = capacity;
    this.items = items;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Pack other = (Pack) obj;
    return Objects.equals(capacity, other.capacity) && Objects.equals(items, other.items);
  }

  public Integer getCapacity() {
    return capacity;
  }

  public List<Item> getItems() {
    return items;
  }

  @Override
  public int hashCode() {
    return Objects.hash(capacity, items);
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return String.format("Pack [capacity=%s, items=%s]", capacity, items);
  }
}
