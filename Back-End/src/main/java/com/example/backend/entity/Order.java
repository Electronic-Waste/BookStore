package com.example.backend.entity;

import lombok.Data;

@Data
public class Order {
    private String UserID;
    private String OrderID;
    private Double Price;

    public Order(String userID, String orderID, Double Price) {
        this.UserID = userID;
        this.OrderID = orderID;
        this.Price = Price;
    }
}
