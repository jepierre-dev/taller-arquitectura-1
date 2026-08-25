package infrastructure.adapters.in.rest.dtos;

import domain.models.Grain;

public record GrainResponse(
        Long id,
        String name,
        String description,
        Integer totalOnInventory) {

    public static GrainResponse from(Grain grain) {
        return new GrainResponse(grain.id(), grain.name(), grain.description(), grain.totalOnInventory());
    }
}
