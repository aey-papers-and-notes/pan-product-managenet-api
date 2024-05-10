package com.aey.products.domain.repository;

import com.aey.products.domain.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllProducts(Integer limit, Integer offset);
    Integer countAllAvailableProducts();
}
