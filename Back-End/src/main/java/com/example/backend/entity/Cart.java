package com.example.backend.entity;

import lombok.Data;

@Data
public class Cart {
    private String UserID;
    private String BookID;

    public Cart(String userID, String bookID) {
        this.UserID = userID;
        this.BookID = bookID;
    }
}
