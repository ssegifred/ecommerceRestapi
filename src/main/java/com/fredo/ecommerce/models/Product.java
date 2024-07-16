package com.fredo.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name", nullable = false)
    private String name;
    @Column(name = "product_price", nullable = false)
    private double price;
    @Column(name = "product_description", nullable = false)
    private String description;
    @Column(name = "product_image_url", nullable = false)
    private String imageUrl;

}
