package domain.exceptions;

import java.util.Map;

import domain.exceptions.codes.OrderErrorCodes;
import domain.models.OrderStatus;
import shared.domain.exceptions.DomainException;

public final class OrderErrors {

  private OrderErrors() {
  }

  public static DomainException quantityNotPositive(Integer quantityInGrams) {
    return new DomainException.RuleViolation(
        OrderErrorCodes.QUANTITY_NOT_POSITIVE,
        Map.of("quantityInGrams", quantityInGrams));
  }

  /** Conflict y no RuleViolation: el mismo pedido seria valido cuando entre stock. */
  public static DomainException insufficientInventory(Long grainId, Integer requested, Integer available) {
    return new DomainException.Conflict(
        OrderErrorCodes.INSUFFICIENT_INVENTORY,
        Map.of("grainId", grainId, "requested", requested, "available", available));
  }

  public static DomainException notPending(OrderStatus status) {
    return new DomainException.Conflict(
        OrderErrorCodes.NOT_PENDING,
        Map.of("status", status));
  }
}
