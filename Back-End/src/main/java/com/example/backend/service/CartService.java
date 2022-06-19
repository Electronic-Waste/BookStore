package com.example.backend.service;

import com.example.backend.entity.Book;
import com.example.backend.entity.Cart;

import java.util.List;

public interface CartService {

    void addBookToCart(String userId, String bookId);

    void deleteBookFromCart(String userId, String bookId);

    void deleteAllBooksFromCart(String userId);

    List<Cart> getCart(String userId);
}
