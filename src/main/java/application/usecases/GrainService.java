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
        if(grainRepositoryPort.existsByNameAndIdNot(name, id)) {
            throw GrainErrors.nameAlreadyExists(name);
        }
        validateNotNull(grainRepositoryPort.findGrainById(id), id);
        grainRepositoryPort.updateGrain(new Grain(id, name, description, totalOnInventory));
    }

    @Override
    public void deleteGrain(Long id) {
        validateNotNull(grainRepositoryPort.findGrainById(id), id);
        grainRepositoryPort.deleteGrain(id);
    }

    @Override
    public void addInventory(Long grainId, Long quantityInGrams) {
        Grain grain = grainRepositoryPort.findGrainById(grainId);
        validateNotNull(grain, grainId);
        grainRepositoryPort.updateGrain(grain.addInventory(quantityInGrams));
    }

    @Override
    public void removeInventory(Long grainId, Long quantityInGrams) {
        Grain grain = grainRepositoryPort.findGrainById(grainId);
        validateNotNull(grain, grainId);
        grainRepositoryPort.updateGrain(grain.removeInventory(quantityInGrams));
    }

    public void validateNotNull(Grain grain, Long grainId) {
        if(grain == null) {
            throw GrainErrors.grainNotFound(grainId);
        }
    }
    
}
