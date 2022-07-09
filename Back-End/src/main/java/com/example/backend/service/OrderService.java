package com.example.backend.service;

import com.example.backend.entity.Book;
import com.example.backend.entity.Order;

import java.util.List;

public interface OrderService {
    void createOneOrder(int userId, int bookId);

    void createMultipleOrders(int userId);

    List<Order> getOrders(int userId);

    List<Book> getOrderItemsBooks(int orderId);
}
