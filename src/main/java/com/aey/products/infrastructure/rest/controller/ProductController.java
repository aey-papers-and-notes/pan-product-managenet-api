package com.aey.products.infrastructure.rest.controller;

import com.aey.products.domain.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
}
