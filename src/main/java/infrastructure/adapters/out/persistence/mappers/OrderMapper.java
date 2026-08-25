package infrastructure.adapters.out.persistence.mappers;

import domain.models.Order;
import infrastructure.adapters.out.persistence.entities.OrderEntity;

public final class OrderMapper {

    private OrderMapper() {
    }

    public static Order toDomain(OrderEntity entity) {
        return entity == null
                ? null
                : new Order(entity.id, entity.grainId, entity.preparationMethodId, entity.quantityInGrams,
                        entity.status, entity.placedAt);
    }

    public static OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.id = order.id();
        entity.grainId = order.grainId();
        entity.preparationMethodId = order.preparationMethodId();
        entity.quantityInGrams = order.quantityInGrams();
        copy(order, entity);
        return entity;
    }

    /** Solo lo mutable de un pedido: grano, metodo y cantidad quedan fijos al crearlo. */
    public static void copy(Order order, OrderEntity entity) {
        entity.status = order.status();
        entity.placedAt = order.placedAt();
    }
}
