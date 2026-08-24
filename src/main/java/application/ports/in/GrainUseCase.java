package application.ports.in;

public interface GrainUseCase {
    public void createGrain(String name, String description, Long totalOnInventory);
    public void updateGrain(Long id, String name, String description, Long totalOnInventory);
    public void deleteGrain(Long id);

    public void addInventory(Long grainId, Long quantityInGrams);
    public void removeInventory(Long grainId, Long quantityInGrams);
}
