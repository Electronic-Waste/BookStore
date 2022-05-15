package com.example.backend.service;

import com.example.backend.entity.Book;
import com.example.backend.entity.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    void createOneOrder(String username, String bookid);

    void createMultipleOrders(String username);

    List<Order> getOrders(String username);

    List<Book> getOrderItems(String orderid);
}
