package domain.exceptions;

import java.util.Map;

import domain.exceptions.codes.PreparationMethodErrorCodes;
import shared.domain.exceptions.DomainException;

public final class PreparationMethodErrors {

  private PreparationMethodErrors() {
  }

  public static DomainException timeNegative(Integer timeInMinutes) {
    return new DomainException.RuleViolation(
        PreparationMethodErrorCodes.TIME_NEGATIVE,
        Map.of("timeInMinutes", timeInMinutes));
  }

  public static DomainException nameAlreadyExists(String name) {
    return new DomainException.Conflict(
        PreparationMethodErrorCodes.NAME_ALREADY_EXISTS,
        Map.of("name", name));
  }

  public static DomainException preparationMethodNotFound(Long preparationMethodId) {
    return new DomainException.NotFound(
        PreparationMethodErrorCodes.PREPARATION_METHOD_NOT_FOUND,
        Map.of("preparationMethodId", preparationMethodId));
  }
}
