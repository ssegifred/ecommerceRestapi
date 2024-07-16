package com.fredo.ecommerce.controller;

import com.fredo.ecommerce.Dto.CartDto;
import com.fredo.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping
    public int addToCart(@RequestBody CartDto cartDto){
        try {
            int count = cartService.addToCart(cartDto);
            return count;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return getCartCount();
        }
    }

    //Delete Rest API
    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable Long id){
        int count =cartService.removeFromCart(id);
        return count;
    }
//GET All Carts products
    @GetMapping
    public List<CartDto> getAllProducts(){
        try {
            List<CartDto> cartDtos = cartService.getAllCartItems();
            return cartDtos;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    //Count Rest API
    @GetMapping("/count")
    public int getCartCount(){
        int count = cartService.getCartCount();
        return count;
    }

    //Delete Rest API
    @DeleteMapping
    public String clearCart(){
        cartService.clearCart();
        return "Cart cleared";
    }
    //Put Rest API
    @PutMapping
    public String updateCart(@RequestBody CartDto cartDto){
        try {
            cartService.updateCart(cartDto);
            return "Cart updated";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
