package domain.exceptions;

import domain.exceptions.codes.OrderErrorCodes;
import shared.domain.exceptions.DomainException;

public class OrderErrors {
  
  public static DomainException nameRequired() {
    return new DomainException.RuleViolation(OrderErrorCodes.NAME_REQUIRED);
  }

  public static DomainException inventoryRequired(Long totalOnInventory) {
    return new DomainException.RuleViolation(
        OrderErrorCodes.INVENTORY_REQUIRED,
        java.util.Map.of("totalOnInventory", totalOnInventory));
  }

  public static DomainException inventoryNegative(Long totalOnInventory) {
    return new DomainException.RuleViolation(
        OrderErrorCodes.INVENTORY_NEGATIVE,
        java.util.Map.of("totalOnInventory", totalOnInventory));
  }

}
