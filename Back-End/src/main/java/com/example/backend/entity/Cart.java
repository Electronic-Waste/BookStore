package com.example.backend.entity;

import lombok.Data;

@Data
public class Cart {
    private String UserID;
    private Book Book;
    private int Num;

    public Cart(String userID, Book book, int num) {
        this.UserID = userID;
        this.Book = book;
        this.Num = num;
    }
}
