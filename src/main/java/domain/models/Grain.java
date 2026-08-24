package domain.models;

import domain.exceptions.GrainErrors;
import shared.domain.exceptions.FieldErrors;

public record Grain (
    Long id,
    String name,
    String description,
    Integer totalOnInventory
  ){

    public Grain{
      FieldErrors.requirePresent(name, "name");
      FieldErrors.requirePresent(totalOnInventory, "totalOnInventory");
      if (totalOnInventory < 0) {
        throw GrainErrors.inventoryNegative(totalOnInventory);
      }
    }

    public Grain addInventory(Integer quantityInGrams) {
      return new Grain(id, name, description, totalOnInventory + quantityInGrams);
    }

    // El constructor rechaza el resultado negativo: no se puede sacar mas stock del que hay.
    public Grain removeInventory(Integer quantityInGrams) {
      return new Grain(id, name, description, totalOnInventory - quantityInGrams);
    }

}
