package infrastructure.adapters.in.rest.controllers;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse;

import application.ports.in.CoffeeOrderUseCase;
import infrastructure.adapters.in.rest.dtos.OrderRequest;
import infrastructure.adapters.in.rest.dtos.OrderResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import shared.infrastructure.rest.ApiWrapped;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CoffeeOrderController {

    private final CoffeeOrderUseCase coffeeOrderUseCase;

    public CoffeeOrderController(CoffeeOrderUseCase coffeeOrderUseCase) {
        this.coffeeOrderUseCase = coffeeOrderUseCase;
    }

    // Una sola transaccion: si el pedido falla al guardarse, el descuento de stock se revierte.
    @POST
    @Transactional
    @ApiWrapped(message = "order.created")
    public RestResponse<Void> create(@Valid OrderRequest request) {
        coffeeOrderUseCase.processOrder(request.grainId(), request.preparationMethodId(), request.quantityInGrams());
        return RestResponse.status(RestResponse.Status.CREATED);
    }

    @GET
    @ApiWrapped(message = "order.listed")
    public List<OrderResponse> list() {
        return coffeeOrderUseCase.listOrders().stream().map(OrderResponse::from).toList();
    }
}
