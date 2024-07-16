package com.fredo.ecommerce.services.Impl;

import com.fredo.ecommerce.Dto.CartDto;
import com.fredo.ecommerce.mapper.CartMapper;
import com.fredo.ecommerce.models.CartItem;
import com.fredo.ecommerce.models.Product;
import com.fredo.ecommerce.repositories.CartitemRepository;
import com.fredo.ecommerce.repositories.ProductRepository;
import com.fredo.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartitemRepository cartitemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public int getCartCount() {
        int cartCount= cartitemRepository.findAll().size();
        return cartCount;
    }

    @Override
    public int addToCart(CartDto cartDto) {
        Product product = productRepository.findById(cartDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        CartItem existingCartItem = cartitemRepository.findByProductId(cartDto.getProductId());
        if (existingCartItem != null) {
            throw new RuntimeException("Product Already Added");
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setImageUrl(product.getImageUrl());
        cartItem.setQuantity(cartDto.getQuantity());
        cartitemRepository.save(cartItem);
        return getCartCount();
    }
    @Override
    public boolean getCartItemById(Long id) {
        return cartitemRepository.existsById(id);
    }

    @Override
    public List<CartDto> getAllCartItems() {
        List<CartItem> cartItems = cartitemRepository.findAll();
        if(cartItems.isEmpty()){
            throw new RuntimeException("No Products Found");
        }else {
            return cartItems.stream().map((cartItem)->CartMapper.mapToCartDto(cartItem))
                    .collect(Collectors.toList());
        }

    }

    @Override
    public void updateCart(CartDto cartDto) {

        CartItem cartItem = cartitemRepository.findByProductId(cartDto.getProductId());
        cartItem.setQuantity(cartDto.getQuantity());
        cartitemRepository.save(cartItem);
    }

    @Override
    public int removeFromCart(Long id) {
        CartItem cartItem = cartitemRepository.findByProductId(id);
        if (getCartItemById(cartItem.getId())) {
            cartitemRepository.deleteById(cartItem.getId());
            return getCartCount();
        }else {
            return getCartCount();
        }
    }

    @Override
    public void clearCart() {
        cartitemRepository.deleteAll();
    }
}
