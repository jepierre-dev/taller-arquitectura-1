package domain.models;

import java.time.Instant;

import domain.exceptions.OrderErrors;
import shared.domain.exceptions.FieldErrors;

public record Order(
    Long id,
    Long grainId,
    Long preparationMethodId,
    Integer quantityInGrams,
    OrderStatus status,
    Instant placedAt
) {

  public Order {
    FieldErrors.requirePresent(grainId, "grainId");
    FieldErrors.requirePresent(preparationMethodId, "preparationMethodId");
    FieldErrors.requirePresent(quantityInGrams, "quantityInGrams");
    FieldErrors.requirePresent(status, "status");
    FieldErrors.requirePresent(placedAt, "placedAt");
    if (quantityInGrams <= 0) {
      throw OrderErrors.quantityNotPositive(quantityInGrams);
    }
  }

  public static Order createOrder(Long grainId, Long preparationMethodId, Integer quantityInGrams, Instant placedAt) {
    return new Order(null, grainId, preparationMethodId, quantityInGrams, OrderStatus.PENDING, placedAt);
  }

  public Order confirm() {
    return withStatus(OrderStatus.CONFIRMED);
  }

  public Order reject() {
    return withStatus(OrderStatus.REJECTED);
  }

  private Order withStatus(OrderStatus target) {
    if (status != OrderStatus.PENDING) {
      throw OrderErrors.notPending(status);
    }
    return new Order(id, grainId, preparationMethodId, quantityInGrams, target, placedAt);
  }
}
