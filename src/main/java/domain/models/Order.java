package domain.models;

public record Order(
    Long id,
    Grain grain,
    PreparationMethod preparationMethod
) {
  
}
