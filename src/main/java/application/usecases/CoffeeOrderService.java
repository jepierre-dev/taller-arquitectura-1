package application.usecases;

import java.time.Instant;
import java.util.List;

import application.ports.in.CoffeeOrderUseCase;
import application.ports.out.GrainRepositoryPort;
import application.ports.out.InventoryPort;
import application.ports.out.OrderRepositoryPort;
import application.ports.out.PreparationMethodRepositoryPort;
import domain.exceptions.PreparationMethodErrors;
import domain.exceptions.GrainErrors;
import domain.exceptions.OrderErrors;
import domain.models.Grain;
import domain.models.Order;
import domain.models.PreparationMethod;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CoffeeOrderService implements CoffeeOrderUseCase {

    private final GrainRepositoryPort grainRepositoryPort;
    private final PreparationMethodRepositoryPort preparationMethodRepositoryPort;

    private final OrderRepositoryPort orderRepositoryPort;
    private final InventoryPort inventoryPort;

    public CoffeeOrderService(GrainRepositoryPort grainRepositoryPort,
            PreparationMethodRepositoryPort preparationMethodRepositoryPort, OrderRepositoryPort orderRepositoryPort,
            InventoryPort inventoryPort) {
        this.grainRepositoryPort = grainRepositoryPort;
        this.preparationMethodRepositoryPort = preparationMethodRepositoryPort;
        this.orderRepositoryPort = orderRepositoryPort;
        this.inventoryPort = inventoryPort;
    }

    @Override
    public void processOrder(Long grainId, Long preparationMethodId, Integer quantityInGrams) {
        validateGrain(grainId);
        validatePreparationMethod(preparationMethodId);

        Integer availableGrams = inventoryPort.availableGrams(grainId);
        if (availableGrams < quantityInGrams) {
            throw OrderErrors.insufficientInventory(grainId, quantityInGrams, availableGrams);
        }
        inventoryPort.discount(grainId, quantityInGrams);

        Order order = Order.createOrder(
                grainId,
                preparationMethodId,
                quantityInGrams,
                Instant.now());
        orderRepositoryPort.saveOrder(order.confirm());
    }

    @Override
    public List<Order> listOrders() {
        return orderRepositoryPort.findAllOrders();
    }

    private void validateGrain(Long grainId) {
        Grain grain = grainRepositoryPort.findGrainById(grainId);
        if (grain == null) {
            throw GrainErrors.grainNotFound(grainId);
        }
    }

    private void validatePreparationMethod(Long preparationMethodId) {
        PreparationMethod preparationMethod = preparationMethodRepositoryPort
                .findPreparationMethodById(preparationMethodId);
        if (preparationMethod == null) {
            throw PreparationMethodErrors.preparationMethodNotFound(preparationMethodId);
        }
    }
}
