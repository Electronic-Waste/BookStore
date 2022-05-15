package com.example.backend.entity;

import lombok.Data;

@Data
public class OrderItem {
    private String OrderID;
    private Book Book;
    private int Num;

    public OrderItem(String orderID, Book book, int num) {
        this.OrderID = orderID;
        this.Book = book;
        this.Num = num;
    }
}
