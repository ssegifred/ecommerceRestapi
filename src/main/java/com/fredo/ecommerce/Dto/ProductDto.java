package com.fredo.ecommerce.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private String description;
    private String imageUrl;
}
