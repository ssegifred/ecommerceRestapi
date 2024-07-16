package com.fredo.ecommerce.controller;

import com.fredo.ecommerce.Dto.ProductDto;
import com.fredo.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class productController {
    private ProductService productService;

    //Dependency Injection
    public productController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public String addProduct(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("price") double price,
                                 @RequestParam("imageFile") MultipartFile imageFile){
        try {
            productService.addProduct(name, description, price, imageFile);
            return "Product Saved Successfully";
        } catch (IOException exception){
            System.out.println(exception.getMessage());
            return "Failed to Save a product";
        }
    }
    //GET BYID Rest API
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        try {
            ProductDto productDto = productService.getProductById(id);
            return productDto;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    //Delete Rest API
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        try{
            productService.deleteProduct(id);
            return "Product deleted successfully";
        }catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping
    public List<ProductDto> getAllProducts(){
        try {
            List<ProductDto> productDtos = productService.getAllProducs();
            return productDtos;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}
