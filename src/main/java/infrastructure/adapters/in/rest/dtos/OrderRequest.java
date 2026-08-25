package infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;

public record OrderRequest(
        @NotNull Long grainId,
        @NotNull Long preparationMethodId,
        @NotNull Integer quantityInGrams) {
}
