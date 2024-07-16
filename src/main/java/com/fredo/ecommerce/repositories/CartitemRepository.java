package com.fredo.ecommerce.repositories;

import com.fredo.ecommerce.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartitemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByProductId(Long productId);
}
