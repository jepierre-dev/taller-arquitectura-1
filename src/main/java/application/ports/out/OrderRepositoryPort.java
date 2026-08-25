package application.ports.out;

import java.util.List;

import domain.models.Order;

public interface OrderRepositoryPort {
    public void saveOrder(Order order);
    public void updateOrder(Order order);

    public Order findOrderById(Long id);
    public List<Order> findAllOrders();

}
