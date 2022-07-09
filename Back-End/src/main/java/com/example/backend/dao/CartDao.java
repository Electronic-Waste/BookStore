package com.example.backend.dao;

import com.example.backend.entity.Cart;

import java.util.List;

public interface CartDao {
    List<Cart> getCartByUserId(int userId);

    Cart getCartByUserIdAndBookId(int userId, int bookId);

    void save(Cart cart);

    void deleteByUserId(int userId);

    void deleteByUserIdAndBookId(int userId, int bookId);
}
