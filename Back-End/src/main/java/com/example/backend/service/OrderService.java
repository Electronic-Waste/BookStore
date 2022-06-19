package com.example.backend.service;

import com.example.backend.entity.Book;
import com.example.backend.entity.Order;

import java.util.List;

public interface OrderService {
    void createOneOrder(String userId, String bookId);

    void createMultipleOrders(String userId);

    List<Order> getOrders(String userId);

    List<Book> getOrderItemsBooks(int orderId);
}
