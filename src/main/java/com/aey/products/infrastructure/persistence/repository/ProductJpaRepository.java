package com.aey.products.infrastructure.persistence.repository;

import com.aey.products.infrastructure.persistence.model.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductJpa, UUID> {
}
