package com.aey.products.domain.service;

import com.aey.products.infrastructure.rest.dto.PaginationProductDto;

public interface ProductService {
    PaginationProductDto getAllProducts(Integer limit, Integer offset);
}
