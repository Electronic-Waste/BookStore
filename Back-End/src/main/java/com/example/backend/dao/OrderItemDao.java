package com.example.backend.dao;

import com.example.backend.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    void save(OrderItem orderItem);

    List<OrderItem> getOrderItemsByOrderId(int orderId);
}
