package com.aey.products.infrastructure.rest.dto;

import com.aey.products.domain.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductDto {

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
    private Integer category; // Convert to Entity in db

    @JsonProperty
    private Integer brand; // Convert to Entity in db

    @JsonProperty
    private Integer tag; // Convert to Entity in db


    public static CreateProductDto fromEntity(Product product) {
        return CreateProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .stock(product.getStock())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .category(product.getCategory())
                .brand(product.getBrand())
                .tag(product.getTag())
                .build();
    }

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .description(description)
                .stock(stock)
                .price(price)
                .imageUrl(imageUrl)
                .build();
    }
}
