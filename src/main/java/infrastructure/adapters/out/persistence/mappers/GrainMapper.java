package infrastructure.adapters.out.persistence.mappers;

import domain.models.Grain;
import infrastructure.adapters.out.persistence.entities.GrainEntity;

public final class GrainMapper {

    private GrainMapper() {
    }

    public static Grain toDomain(GrainEntity entity) {
        return entity == null
                ? null
                : new Grain(entity.id, entity.name, entity.description, entity.totalOnInventory);
    }

    public static GrainEntity toEntity(Grain grain) {
        GrainEntity entity = new GrainEntity();
        entity.id = grain.id();
        copy(grain, entity);
        return entity;
    }

    /** Sobre una entidad gestionada: Hibernate detecta el cambio, no hace falta un update explicito. */
    public static void copy(Grain grain, GrainEntity entity) {
        entity.name = grain.name();
        entity.description = grain.description();
        entity.totalOnInventory = grain.totalOnInventory();
    }
}
