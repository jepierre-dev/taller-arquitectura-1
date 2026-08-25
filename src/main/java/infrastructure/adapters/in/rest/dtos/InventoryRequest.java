package infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;

public record InventoryRequest(
        @NotNull Integer quantityInGrams) {
}
