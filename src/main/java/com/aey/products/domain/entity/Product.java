package com.aey.products.domain.entity;

import com.aey.products.infrastructure.persistence.model.ProductJpa;
import com.aey.products.infrastructure.rest.dto.ProductDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private UUID productId;
    private String name;
    private String description;
    private Integer stock;
    private BigDecimal price;
    private String imageUrl;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isActive;
    private Integer category; // Convert to Entity in db
    private Integer brand; // Convert to Entity in db
    private Integer tag; // Convert to Entity in db

    public static Product fromEntity(ProductDto product) {
        return Product.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .stock(product.getStock())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .isActive(product.getIsActive())
                .category(product.getCategory())
                .brand(product.getBrand())
                .tag(product.getTag())
                .build();
    }
}
