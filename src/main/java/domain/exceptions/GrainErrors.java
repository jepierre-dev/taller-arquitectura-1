package domain.exceptions;

import java.util.Map;

import domain.exceptions.codes.GrainErrorCodes;
import shared.domain.exceptions.DomainException;

public final class GrainErrors {

  private GrainErrors() {
  }

  public static DomainException inventoryNegative(Long totalOnInventory) {
    return new DomainException.RuleViolation(
        GrainErrorCodes.INVENTORY_NEGATIVE,
        Map.of("totalOnInventory", totalOnInventory));
  }
}
