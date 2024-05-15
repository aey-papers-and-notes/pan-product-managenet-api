package com.aey.products.application;

import com.aey.products.domain.entity.Product;
import com.aey.products.domain.repository.ProductRepository;
import com.aey.products.domain.service.ProductService;
import com.aey.products.infrastructure.rest.dto.CreateProductDto;
import com.aey.products.infrastructure.rest.dto.PaginationProductDto;
import com.aey.products.infrastructure.rest.dto.ProductDto;
import common.errors.ErrorCode;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class ProductBs implements ProductService {

    @Inject
    ProductRepository productRepository;

    @Override
    public PaginationProductDto getAllProducts(Integer limit, Integer offset) {
        limit = limit == null ? 10 : limit;
        offset = offset == null ? 0 : offset;
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

    @Override
    public Either<ErrorCode, ProductDto> getProductById(UUID productId) {
        Optional<Product> product = productRepository.findProductById(productId);

        if (product.isEmpty()) {
            return Either.left(ErrorCode.NOT_FOUND);
        }
        if (product.get().getIsActive().equals(Boolean.FALSE)) {
            return Either.left(ErrorCode.RESOURCE_NOT_AVAILABLE);
        }

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        ProductDto productFound = product.map(ProductDto::fromEntity).get();
        return Either.right(productFound);
    }

    @Override
    public Either<ErrorCode, ProductDto> createProduct(CreateProductDto createProductDto) {
        Product product = Product.builder()
                .name(createProductDto.getName())
                .description(createProductDto.getDescription())
                .stock(createProductDto.getStock())
                .price(createProductDto.getPrice())
                .imageUrl(createProductDto.getImageUrl())
                .createdAt(new Date())
                .updatedAt(new Date())
                .isActive(Boolean.TRUE)
                .category(createProductDto.getCategory())
                .brand(createProductDto.getBrand())
                .tag(createProductDto.getTag())
                .build();
        Optional<Product> productCreated = productRepository.createProduct(product);
        return productCreated
                .<Either<ErrorCode, ProductDto>>map(p -> Either.right(ProductDto.fromEntity(p)))
                .orElseGet(() -> Either.left(ErrorCode.ERROR_TO_CREATE));
    }

    @Override
    public Either<ErrorCode, ProductDto> disableProduct(UUID productId) {
        Optional<Product> product = productRepository.findProductById(productId);

        if (product.isEmpty()) {
            return Either.left(ErrorCode.NOT_FOUND);
        }
        if (product.get().getIsActive().equals(Boolean.FALSE)) {
            return Either.left(ErrorCode.RESOURCE_NOT_AVAILABLE);
        }

        Product productToDisable = product.get();
        productToDisable.setIsActive(Boolean.FALSE);
        productToDisable.setUpdatedAt(new Date());
        productRepository.disableProduct(productToDisable);
        return Either.right(ProductDto.fromEntity(productToDisable));
    }
}
