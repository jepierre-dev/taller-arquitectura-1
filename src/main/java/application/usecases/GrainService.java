package application.usecases;

import application.ports.in.GrainUseCase;
import application.ports.out.GrainRepositoryPort;
import domain.exceptions.GrainErrors;
import domain.models.Grain;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GrainService implements GrainUseCase {

    private final GrainRepositoryPort grainRepositoryPort;

    public GrainService(GrainRepositoryPort grainRepositoryPort) {
        this.grainRepositoryPort = grainRepositoryPort;
    }

    @Override
    public void createGrain(String name, String description, Long totalOnInventory) {
        if (grainRepositoryPort.existsByName(name)) {
            throw GrainErrors.nameAlreadyExists(name);
        }
        grainRepositoryPort.saveGrain(new Grain(null, name, description, totalOnInventory));
    }

    @Override
    public void updateGrain(Long id, String name, String description, Long totalOnInventory) {
        grainRepositoryPort.updateGrain(new Grain(id, name, description, totalOnInventory));
    }

    @Override
    public void deleteGrain(Long id) {
        grainRepositoryPort.deleteGrain(id);
    }

    @Override
    public void addInventory(Long grainId, Long quantityInGrams) {
        Grain grain = grainRepositoryPort.findGrainById(grainId);
        grainRepositoryPort.updateGrain(grain.addInventory(quantityInGrams));
    }

    @Override
    public void removeInventory(Long grainId, Long quantityInGrams) {
        Grain grain = grainRepositoryPort.findGrainById(grainId);
        grainRepositoryPort.updateGrain(grain.removeInventory(quantityInGrams));
    }
    
}
