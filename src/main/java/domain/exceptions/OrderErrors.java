package domain.exceptions;

import java.util.Map;

import domain.exceptions.codes.OrderErrorCodes;
import shared.domain.exceptions.DomainException;

public final class OrderErrors {

  private OrderErrors() {
  }

  public static DomainException inventoryNegative(Long totalOnInventory) {
    return new DomainException.RuleViolation(
        OrderErrorCodes.INVENTORY_NEGATIVE,
        Map.of("totalOnInventory", totalOnInventory));
  }
}
