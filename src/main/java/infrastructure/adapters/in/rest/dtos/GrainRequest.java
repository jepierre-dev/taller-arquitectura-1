package infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GrainRequest(
        @NotBlank String name,
        String description,
        @NotNull Integer totalOnInventory) {
}
