package application.ports.in;

public interface CoffeeOrderUseCase {
    void processOrder(Long grainId, Long preparationMethodId, Integer quantityInGrams);
}
