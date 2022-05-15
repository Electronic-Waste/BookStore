package com.example.backend.entity;

import lombok.Data;

@Data
public class OrderItem {
    private String OrderID;
    private Book Book;

    public OrderItem(String orderID, Book book) {
        this.OrderID = orderID;
        this.Book = book;
    }
}
