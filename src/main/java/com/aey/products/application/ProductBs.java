package com.aey.products.application;

import com.aey.products.domain.repository.ProductRepository;
import com.aey.products.domain.service.ProductService;
import com.aey.products.infrastructure.rest.dto.PaginationProductDto;
import com.aey.products.infrastructure.rest.dto.ProductDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;


@ApplicationScoped
public class ProductBs implements ProductService {

    @Inject
    ProductRepository productRepository;

    @Override
    public PaginationProductDto getAllProducts(Integer limit, Integer offset) {
        List<ProductDto> products = productRepository
                .findAllProducts(limit, offset)
                .stream()
                .map(ProductDto::fromEntity)
                .toList();
        Integer totalProducts = productRepository.countAllAvailableProducts();
        Integer lastPage = totalProducts % limit == 0 ? totalProducts / limit : (totalProducts / limit) + 1;
        Integer page = (offset / limit) + 1;
        return PaginationProductDto.builder()
                .totalProducts(totalProducts)
                .lastPage(lastPage)
                .products(products)
                .page(page)
                .build();
    }
}
