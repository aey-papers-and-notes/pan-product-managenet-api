package com.aey.products.domain.service;

import com.aey.products.infrastructure.rest.dto.PaginationProductDto;
import com.aey.products.infrastructure.rest.dto.ProductDto;
import common.errors.ErrorCode;
import io.vavr.control.Either;

import java.util.UUID;

public interface ProductService {
    PaginationProductDto getAllProducts(Integer limit, Integer offset);
    Either<ErrorCode, ProductDto> getProductById(UUID productId);
}
