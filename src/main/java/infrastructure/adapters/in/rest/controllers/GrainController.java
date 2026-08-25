package infrastructure.adapters.in.rest.controllers;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse;

import application.ports.in.GrainUseCase;
import infrastructure.adapters.in.rest.dtos.GrainRequest;
import infrastructure.adapters.in.rest.dtos.GrainResponse;
import infrastructure.adapters.in.rest.dtos.InventoryRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import shared.infrastructure.rest.ApiWrapped;

@Path("/grains")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GrainController {

    private final GrainUseCase grainUseCase;

    public GrainController(GrainUseCase grainUseCase) {
        this.grainUseCase = grainUseCase;
    }

    @GET
    @ApiWrapped(message = "grain.listed")
    public List<GrainResponse> list() {
        return grainUseCase.listGrains().stream().map(GrainResponse::from).toList();
    }

    @POST
    @Transactional
    @ApiWrapped(message = "grain.created")
    public RestResponse<Void> create(@Valid GrainRequest request) {
        grainUseCase.createGrain(request.name(), request.description(), request.totalOnInventory());
        return RestResponse.status(RestResponse.Status.CREATED);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @ApiWrapped(message = "grain.updated")
    public RestResponse<Void> update(@PathParam("id") Long id, @Valid GrainRequest request) {
        grainUseCase.updateGrain(id, request.name(), request.description(), request.totalOnInventory());
        return RestResponse.ok();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @ApiWrapped(message = "grain.deleted")
    public RestResponse<Void> delete(@PathParam("id") Long id) {
        grainUseCase.deleteGrain(id);
        return RestResponse.ok();
    }

    @POST
    @Path("/{id}/inventory/additions")
    @Transactional
    @ApiWrapped(message = "grain.inventory.added")
    public RestResponse<Void> addInventory(@PathParam("id") Long id, @Valid InventoryRequest request) {
        grainUseCase.addInventory(id, request.quantityInGrams());
        return RestResponse.ok();
    }

    @POST
    @Path("/{id}/inventory/removals")
    @Transactional
    @ApiWrapped(message = "grain.inventory.removed")
    public RestResponse<Void> removeInventory(@PathParam("id") Long id, @Valid InventoryRequest request) {
        grainUseCase.removeInventory(id, request.quantityInGrams());
        return RestResponse.ok();
    }
}
