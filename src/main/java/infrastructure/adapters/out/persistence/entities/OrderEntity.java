package infrastructure.adapters.out.persistence.entities;

import java.time.Instant;

import domain.models.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// "orders" es palabra reservada en varios motores: el prefijo evita tener que citar el identificador.
@Entity
@Table(name = "coffee_orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "grain_id", nullable = false)
    public Long grainId;

    @Column(name = "preparation_method_id", nullable = false)
    public Long preparationMethodId;

    @Column(name = "quantity_in_grams", nullable = false)
    public Integer quantityInGrams;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public OrderStatus status;

    @Column(name = "placed_at", nullable = false)
    public Instant placedAt;
}
