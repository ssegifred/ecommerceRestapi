package com.fredo.ecommerce.mapper;

import com.fredo.ecommerce.Dto.ProductDto;
import com.fredo.ecommerce.models.Product;

public class ProductMapper {
    public static Product mapToProduct(ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getDescription(),
                productDto.getImageUrl()
        );
        return product;
    }

    public static ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImageUrl()
        );
        return productDto;
    }
}
