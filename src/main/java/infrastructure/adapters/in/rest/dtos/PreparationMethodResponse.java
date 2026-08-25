package infrastructure.adapters.in.rest.dtos;

import domain.models.PreparationMethod;

public record PreparationMethodResponse(
        Long id,
        String name,
        String description,
        Integer timeInMinutes) {

    public static PreparationMethodResponse from(PreparationMethod preparationMethod) {
        return new PreparationMethodResponse(preparationMethod.id(), preparationMethod.name(),
                preparationMethod.description(), preparationMethod.timeInMinutes());
    }
}
