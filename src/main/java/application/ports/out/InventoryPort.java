package application.ports.out;

/** Vista de inventario para el flujo de pedidos: consulta stock y lo descuenta al confirmar. */
public interface InventoryPort {
    public Long availableGrams(Long grainId);
    public void discount(Long grainId, Long quantityInGrams);
}
