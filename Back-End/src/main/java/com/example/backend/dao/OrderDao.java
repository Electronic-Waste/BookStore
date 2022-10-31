package com.example.backend.dao;

import com.example.backend.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrdersByUserId(int userId);

    int save(Order order);
}
