package infrastructure.adapters.out.persistence.repositories;

import application.ports.out.InventoryPort;
import domain.exceptions.GrainErrors;
import infrastructure.adapters.out.persistence.entities.GrainEntity;
import infrastructure.adapters.out.persistence.mappers.GrainMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/** El stock vive en el propio grano: esta vista solo expone lo que el flujo de pedidos necesita. */
@ApplicationScoped
public class InventoryAdapter implements InventoryPort, PanacheRepository<GrainEntity> {

    @Override
    public Integer availableGrams(Long grainId) {
        return requireGrain(grainId).totalOnInventory;
    }

    @Override
    public void discount(Long grainId, Integer quantityInGrams) {
        GrainEntity entity = requireGrain(grainId);
        // Pasa por el modelo para que la invariante de stock no negativo se aplique tambien aqui.
        GrainMapper.copy(GrainMapper.toDomain(entity).removeInventory(quantityInGrams), entity);
    }

    private GrainEntity requireGrain(Long grainId) {
        GrainEntity entity = findById(grainId);
        if (entity == null) {
            throw GrainErrors.grainNotFound(grainId);
        }
        return entity;
    }
}
