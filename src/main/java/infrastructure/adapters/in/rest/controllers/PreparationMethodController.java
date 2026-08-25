package infrastructure.adapters.in.rest.controllers;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse;

import application.ports.in.PreparationMethodUseCase;
import infrastructure.adapters.in.rest.dtos.PreparationMethodRequest;
import infrastructure.adapters.in.rest.dtos.PreparationMethodResponse;
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

@Path("/preparation-methods")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PreparationMethodController {

    private final PreparationMethodUseCase preparationMethodUseCase;

    public PreparationMethodController(PreparationMethodUseCase preparationMethodUseCase) {
        this.preparationMethodUseCase = preparationMethodUseCase;
    }

    @GET
    @ApiWrapped(message = "preparation.listed")
    public List<PreparationMethodResponse> list() {
        return preparationMethodUseCase.listPreparationMethods().stream()
                .map(PreparationMethodResponse::from)
                .toList();
    }

    @POST
    @Transactional
    @ApiWrapped(message = "preparation.created")
    public RestResponse<Void> create(@Valid PreparationMethodRequest request) {
        preparationMethodUseCase.createPreparationMethod(request.name(), request.description(),
                request.timeInMinutes());
        return RestResponse.status(RestResponse.Status.CREATED);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @ApiWrapped(message = "preparation.updated")
    public RestResponse<Void> update(@PathParam("id") Long id, @Valid PreparationMethodRequest request) {
        preparationMethodUseCase.updatePreparationMethod(id, request.name(), request.description(),
                request.timeInMinutes());
        return RestResponse.ok();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @ApiWrapped(message = "preparation.deleted")
    public RestResponse<Void> delete(@PathParam("id") Long id) {
        preparationMethodUseCase.deletePreparationMethod(id);
        return RestResponse.ok();
    }
}
