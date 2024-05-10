package com.aey.products.infrastructure.rest.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginationProductDto {
    private Integer totalProducts;
    private Integer page;
    private Integer lastPage;
    private List<ProductDto> products;
}
