package com.aey.products.infrastructure.persistence.model;

import com.aey.products.domain.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "prod01_products")
public class ProductJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "p_name")
    private String name;

    @Column(name = "p_description")
    private String description;

    @Column(name = "p_stock")
    private Integer stock;

    @Column(name = "p_price")
    private BigDecimal price;

    @Column(name = "p_image_url")
    private String imageUrl;

    @Column(name = "p_created_at")
    private Date createdAt;

    @Column(name = "p_updated_at")
    private Date updatedAt;

    @Column(name = "p_is_active")
    private Boolean isActive;

    @Column(name = "p_category")
    private Integer category; // Convert to Entity in db

    @Column(name = "p_brand")
    private Integer brand; // Convert to Entity in db

    @Column(name = "p_tag")
    private Integer tag; // Convert to Entity in db

    public static ProductJpa fromEntity(Product product) {
        return ProductJpa.builder()
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
                .isActive(isActive)
                .category(category)
                .brand(brand)
                .tag(tag)
                .build();
    }
}
