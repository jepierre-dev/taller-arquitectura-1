package application.ports.in;

import java.util.List;

import domain.models.Grain;

public interface GrainUseCase {
    public void createGrain(String name, String description, Integer totalOnInventory);
    public void updateGrain(Long id, String name, String description, Integer totalOnInventory);
    public void deleteGrain(Long id);

    public List<Grain> listGrains();

    public void addInventory(Long grainId, Integer quantityInGrams);
    public void removeInventory(Long grainId, Integer quantityInGrams);
}
