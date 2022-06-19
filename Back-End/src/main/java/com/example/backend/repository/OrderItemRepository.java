package com.example.backend.repository;

import com.example.backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("select oi from OrderItem oi WHERE oi.orderId = :orderId")
    List<OrderItem> getOrderItemsByOrderId(@Param("orderId") int orderId);
}
