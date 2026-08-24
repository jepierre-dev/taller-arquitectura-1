package domain.exceptions;

import java.util.Map;

import domain.exceptions.codes.GrainErrorCodes;
import shared.domain.exceptions.DomainException;

public final class GrainErrors {

  private GrainErrors() {
  }

  public static DomainException inventoryNegative(Integer totalOnInventory) {
    return new DomainException.RuleViolation(
        GrainErrorCodes.INVENTORY_NEGATIVE,
        Map.of("totalOnInventory", totalOnInventory));
  }

  public static DomainException nameAlreadyExists(String name) {
    return new DomainException.Conflict(
        GrainErrorCodes.NAME_ALREADY_EXISTS,
        Map.of("name", name));
  }

  public static DomainException grainNotFound(Long grainId) {
    return new DomainException.NotFound(
        GrainErrorCodes.GRAIN_NOT_FOUND,
        Map.of("grainId", grainId));
  }
}
