package com.fredo.ecommerce.services.Impl;

import com.fredo.ecommerce.Dto.ProductDto;
import com.fredo.ecommerce.mapper.ProductMapper;
import com.fredo.ecommerce.models.Product;
import com.fredo.ecommerce.repositories.ProductRepository;
import com.fredo.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private final Path root = Paths.get("uploads");
    @Override
    public ProductDto addProduct(String name, String description, Double price, MultipartFile imageFile) throws IOException {
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
        String filename = System.currentTimeMillis() + "-" + imageFile.getOriginalFilename();
        Files.copy(imageFile.getInputStream(), this.root.resolve(filename));
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl("/uploads/" + filename);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(savedProduct);

     }

    @Override
    public ProductDto getProductById(long id) {
        Product product= productRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Product Not Found with id "+id));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducs() {
        List<Product> productList= productRepository.findAll();
        if(productList.isEmpty()){
            throw new RuntimeException("No Products Found");
        }else {
            return productList.stream().map((product)->ProductMapper.mapToProductDto(product))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Product product= productRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Product Not Found with id "+id));
        productRepository.delete(product);
    }

}
