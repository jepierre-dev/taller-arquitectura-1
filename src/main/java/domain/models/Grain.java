package domain.models;

import domain.exceptions.GrainErrors;

public record Grain (
    Long id,
    String name,
    String description,
    Long totalOnInventory
  ){

    public Grain{
      if (name == null) {
        throw GrainErrors.nameRequired();
      }
      if (totalOnInventory == null) {
        throw GrainErrors.inventoryRequired(totalOnInventory);
      }
      if (totalOnInventory < 0) {
        throw GrainErrors.inventoryNegative(totalOnInventory);
      }
    }

}
