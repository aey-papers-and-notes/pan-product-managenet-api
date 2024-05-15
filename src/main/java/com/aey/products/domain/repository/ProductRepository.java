package com.aey.products.domain.repository;

import com.aey.products.domain.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    List<Product> findAllProducts(Integer limit, Integer offset);
    Integer countAllAvailableProducts();
    Optional<Product> findProductById(UUID productId);
    Optional<Product> createProduct(Product product);
    void disableProduct(Product product);
}
