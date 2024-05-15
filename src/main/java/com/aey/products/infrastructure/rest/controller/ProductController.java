package com.aey.products.infrastructure.rest.controller;

import com.aey.products.domain.service.ProductService;
import com.aey.products.infrastructure.rest.dto.CreateProductDto;
import common.errors.ErrorMapper;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("products")
public class ProductController {

    @Inject
    ProductService productService;

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts(
            @QueryParam("limit") Integer limit,
            @QueryParam("offset") Integer offset
    ) {
        return Response.ok(productService.getAllProducts(limit, offset)).build();
    }

    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") UUID id) {
        return productService.getProductById(id)
                .map(Response::ok)
                .getOrElseGet(ErrorMapper::toResponse)
                .build();
    }

    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(@Valid CreateProductDto createProductDto) {
        return productService.createProduct(createProductDto)
                .map(res -> Response.ok(res).status(Response.Status.CREATED))
                .getOrElseGet(ErrorMapper::toResponse)
                .build();
    }

}
