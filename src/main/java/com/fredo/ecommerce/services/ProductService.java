package com.fredo.ecommerce.services;

import com.fredo.ecommerce.Dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductDto addProduct(String name, String description, Double price, MultipartFile imageFile) throws IOException;
    ProductDto getProductById(long id);
    List<ProductDto> getAllProducs();
    void deleteProduct(Long id);
}
