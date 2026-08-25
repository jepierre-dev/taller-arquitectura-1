package application.ports.in;

import java.util.List;

import domain.models.Order;

public interface CoffeeOrderUseCase {
    void processOrder(Long grainId, Long preparationMethodId, Integer quantityInGrams);

    List<Order> listOrders();
}
