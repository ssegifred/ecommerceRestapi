package com.fredo.ecommerce.mapper;

import com.fredo.ecommerce.Dto.CartDto;
import com.fredo.ecommerce.models.CartItem;

public class CartMapper {
    public static CartDto mapToCartDto(CartItem cart){
        CartDto cartDto= new CartDto(
                cart.getId(),
                cart.getId(),
                cart.getImageUrl(),
                cart.getQuantity()
        );
        return cartDto;
    }

}
