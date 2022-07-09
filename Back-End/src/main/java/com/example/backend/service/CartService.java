package com.example.backend.service;

import com.example.backend.entity.Book;
import com.example.backend.entity.Cart;

import java.util.List;

public interface CartService {

    void addBookToCart(int userId, int bookId);

    void deleteBookFromCart(int userId, int bookId);

    void deleteAllBooksFromCart(int userId);

    List<Cart> getCart(int userId);
}
