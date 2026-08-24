package domain.models;

import domain.exceptions.GrainErrors;
import shared.domain.exceptions.FieldErrors;

public record Grain (
    Long id,
    String name,
    String description,
    Long totalOnInventory
  ){

    public Grain{
      FieldErrors.requirePresent(name, "name");
      FieldErrors.requirePresent(totalOnInventory, "totalOnInventory");
      if (totalOnInventory < 0) {
        throw GrainErrors.inventoryNegative(totalOnInventory);
      }
    }

    public Grain(Long id, String name, String description, Integer totalOnInventory) {
      this(id, name, description, totalOnInventory != null ? totalOnInventory.longValue() : null);
    }

    public Grain addInventory(Long quantityInGrams) {
      return new Grain(id, name, description, totalOnInventory + quantityInGrams);
    }

    // El constructor rechaza el resultado negativo: no se puede sacar mas stock del que hay.
    public Grain removeInventory(Long quantityInGrams) {
      return new Grain(id, name, description, totalOnInventory - quantityInGrams);
    }

}
