package domain.exceptions;

import domain.exceptions.codes.GrainErrorCodes;
import shared.domain.exceptions.DomainException;

public class GrainErrors {
  public static DomainException inventoryNegative(Long totalOnInventory) {
    return new DomainException.RuleViolation(
        GrainErrorCodes.INVENTORY_NEGATIVE,
        java.util.Map.of("totalOnInventory", totalOnInventory));
  }

  public static DomainException inventoryRequired(Long totalOnInventory) {
    return new DomainException.RuleViolation(
        GrainErrorCodes.INVENTORY_REQUIRED,
        java.util.Map.of("totalOnInventory", totalOnInventory));
  }

  public static DomainException nameRequired() {
    return new DomainException.RuleViolation(GrainErrorCodes.NAME_REQUIRED);
  }
}
