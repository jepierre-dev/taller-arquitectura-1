package infrastructure.adapters.in.rest.dtos;

import java.time.Instant;

import domain.models.Order;
import domain.models.OrderStatus;

public record OrderResponse(
        Long id,
        Long grainId,
        Long preparationMethodId,
        Integer quantityInGrams,
        OrderStatus status,
        Instant placedAt) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(order.id(), order.grainId(), order.preparationMethodId(),
                order.quantityInGrams(), order.status(), order.placedAt());
    }
}
