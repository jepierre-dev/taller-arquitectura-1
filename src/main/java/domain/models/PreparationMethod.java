package domain.models;

import domain.exceptions.PreparationMethodErrors;
import shared.domain.exceptions.FieldErrors;

public record PreparationMethod(
    Long id,
    String name,
    String description,
    Integer timeInMinutes
) {
    public PreparationMethod {
        FieldErrors.requirePresent(name, "name");
        FieldErrors.requirePresent(timeInMinutes, "timeInMinutes");
        if (timeInMinutes < 0) {
            throw PreparationMethodErrors.timeNegative(timeInMinutes);
        }
    }
}
