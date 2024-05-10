package com.aey.products.infrastructure.rest.dto;

import com.aey.products.domain.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    @JsonProperty
    private UUID productId;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private Integer stock;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private String imageUrl;

    @JsonProperty
    private Date createdAt;

    @JsonProperty
    private Date updatedAt;

    @JsonProperty
    private Integer category; // Convert to Entity in db

    @JsonProperty
    private Integer brand; // Convert to Entity in db

    @JsonProperty
    private Integer tag; // Convert to Entity in db


    public static ProductDto fromEntity(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .stock(product.getStock())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .category(product.getCategory())
                .brand(product.getBrand())
                .tag(product.getTag())
                .build();
    }

    public Product toEntity() {
        return Product.builder()
                .productId(productId)
                .name(name)
                .description(description)
                .stock(stock)
                .price(price)
                .imageUrl(imageUrl)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .category(category)
                .brand(brand)
                .tag(tag)
                .build();
    }
}
