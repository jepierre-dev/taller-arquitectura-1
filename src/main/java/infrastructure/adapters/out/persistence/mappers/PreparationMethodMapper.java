package infrastructure.adapters.out.persistence.mappers;

import domain.models.PreparationMethod;
import infrastructure.adapters.out.persistence.entities.PreparationMethodEntity;

public final class PreparationMethodMapper {

    private PreparationMethodMapper() {
    }

    public static PreparationMethod toDomain(PreparationMethodEntity entity) {
        return entity == null
                ? null
                : new PreparationMethod(entity.id, entity.name, entity.description, entity.timeInMinutes);
    }

    public static PreparationMethodEntity toEntity(PreparationMethod preparationMethod) {
        PreparationMethodEntity entity = new PreparationMethodEntity();
        entity.id = preparationMethod.id();
        copy(preparationMethod, entity);
        return entity;
    }

    /** Sobre una entidad gestionada: Hibernate detecta el cambio, no hace falta un update explicito. */
    public static void copy(PreparationMethod preparationMethod, PreparationMethodEntity entity) {
        entity.name = preparationMethod.name();
        entity.description = preparationMethod.description();
        entity.timeInMinutes = preparationMethod.timeInMinutes();
    }
}
