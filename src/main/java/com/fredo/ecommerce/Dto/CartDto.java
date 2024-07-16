package com.fredo.ecommerce.Dto;

import com.fredo.ecommerce.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CartDto {
    private Long id;
    private Long productId;
    private String imageUrl;
    private int quantity;
}
