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

}
