package application.ports.in;

public interface GrainUseCase {
    public void createGrain(String name, String description, Integer totalOnInventory);
    public void updateGrain(Long id, String name, String description, Integer totalOnInventory);
    public void deleteGrain(Long id);

    public void addInventory(Long grainId, Integer quantityInGrams);
    public void removeInventory(Long grainId, Integer quantityInGrams);
}
