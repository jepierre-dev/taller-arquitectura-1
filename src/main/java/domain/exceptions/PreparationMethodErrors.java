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
}
