package com.example.backend.dao;

import com.example.backend.entity.Cart;

import java.util.List;

public interface CartDao {
    List<Cart> getCartByUserId(String userId);

    Cart getCartByUserIdAndBookId(String userId, String bookId);

    void save(Cart cart);

    void deleteByUserId(String userId);

    void deleteByUserIdAndBookId(String userId, String bookId);
}
