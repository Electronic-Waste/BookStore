package com.example.backend.service;

import com.example.backend.entity.Book;
import com.example.backend.entity.Cart;

import java.util.List;

public interface CartService {

    boolean addBookToCart(String username, String bookid);

    boolean deleteBookFromCart(String username, String bookid);

    boolean deleteAllBooksFromCart(String username);

    List<Cart> getCart(String username);
}
