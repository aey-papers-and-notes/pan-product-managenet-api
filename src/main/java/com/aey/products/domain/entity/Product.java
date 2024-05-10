package com.aey.products.domain.entity;

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
}
