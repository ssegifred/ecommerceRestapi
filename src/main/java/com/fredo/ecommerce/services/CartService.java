package com.fredo.ecommerce.services;

import com.fredo.ecommerce.Dto.CartDto;

import java.util.List;

public interface CartService {
    int getCartCount();
    int addToCart(CartDto cartDto);
    int removeFromCart(Long id);
    void clearCart();
    boolean getCartItemById(Long id);
    List<CartDto> getAllCartItems();
    void updateCart(CartDto cartDto);

}
