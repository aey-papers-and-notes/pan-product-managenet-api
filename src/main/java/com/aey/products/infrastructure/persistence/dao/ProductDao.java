package com.aey.products.infrastructure.persistence.dao;

import com.aey.products.domain.entity.Product;
import com.aey.products.domain.repository.ProductRepository;
import com.aey.products.infrastructure.persistence.model.ProductJpa;
import com.aey.products.infrastructure.persistence.query.ProductQuery;
import com.aey.products.infrastructure.persistence.repository.ProductJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.*;

@ApplicationScoped
public class ProductDao implements ProductRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    ProductJpaRepository productJpaRepository;

    @Override
    public List<Product> findAllProducts(Integer limit, Integer offset) {
        //noinspection unchecked
        List<Object[]> result = entityManager
                .createNativeQuery(ProductQuery.PAGINATION_PRODUCT)
                .setParameter(ProductQuery.PARAM_PRODUCT_LIMIT, limit)
                .setParameter(ProductQuery.PARAM_PRODUCT_OFFSET, offset)
                .getResultList();

        if (result.isEmpty()) {
            return new ArrayList<>();
        }

        return result.stream().map(r -> (
                Product.builder()
                        .productId((UUID) r[0])
                        .name((String) r[1])
                        .description((String) r[2])
                        .stock((Integer) r[3])
                        .price((BigDecimal) r[4])
                        .imageUrl((String) r[5])
                        .createdAt((Date) r[6])
                        .updatedAt((Date) r[7])
                        .isActive((Boolean) r[8])
                        .category((Integer) r[9])
                        .brand((Integer) r[10])
                        .tag((Integer) r[11])
                        .build()
                )).toList();
    }

    @Override
    public Integer countAllAvailableProducts() {
        Long count = (Long) entityManager
                .createNativeQuery(ProductQuery.COUNT_AVAILABLE_PRODUCTS)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    public Optional<Product> findProductById(UUID productId) {
        return productJpaRepository
                .findById(productId)
                .map(ProductJpa::toEntity);
    }

    @Override
    public Optional<Product> createProduct(Product product) {
        return Optional.of(productJpaRepository.saveAndFlush(ProductJpa.fromEntity(product)).toEntity());
    }

}

