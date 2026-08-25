package infrastructure.adapters.out.persistence.repositories;

import java.util.List;

import application.ports.out.OrderRepositoryPort;
import domain.models.Order;
import infrastructure.adapters.out.persistence.entities.OrderEntity;
import infrastructure.adapters.out.persistence.mappers.OrderMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepositoryAdapter implements OrderRepositoryPort, PanacheRepository<OrderEntity> {

    @Override
    public void saveOrder(Order order) {
        persist(OrderMapper.toEntity(order));
    }

    @Override
    public void updateOrder(Order order) {
        OrderEntity entity = findById(order.id());
        if (entity != null) {
            OrderMapper.copy(order, entity);
        }
    }

    @Override
    public Order findOrderById(Long id) {
        return OrderMapper.toDomain(findById(id));
    }

    @Override
    public List<Order> findAllOrders() {
        return list("order by placedAt desc, id desc").stream().map(OrderMapper::toDomain).toList();
    }
}
