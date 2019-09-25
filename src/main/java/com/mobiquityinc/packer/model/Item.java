package com.mobiquityinc.packer.model;

import java.util.Objects;
import com.mobiquityinc.exception.APIException;

public class Item {

  private Integer cost;

  private Integer index;
  private Integer weight;

  public Item(Integer index, Integer weight, Integer cost) throws APIException {
    if (index == null
        || weight == null
        || cost == null
        || index.compareTo(0) <= 0
        || weight.compareTo(0) <= 0
        || cost.compareTo(0) <= 0) {
      throw new APIException(
          "Unacceptable constructor argument value", new IllegalArgumentException());
    }
    this.index = index;
    this.weight = weight;
    this.cost = cost;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Item other = (Item) obj;
    return Objects.equals(cost, other.cost)
        && Objects.equals(index, other.index)
        && Objects.equals(weight, other.weight);
  }

  public Integer getCost() {
    return cost;
  }

  public Integer getIndex() {
    return index;
  }

  public Integer getWeight() {
    return weight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(cost, index, weight);
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  @Override
  public String toString() {
    return String.format("Item [index=%s, weight=%s, cost=%s]", index, weight, cost);
  }
}
