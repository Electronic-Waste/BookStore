package com.example.backend.daoimpl;

import com.example.backend.dao.OrderDao;
import com.example.backend.entity.Order;
import com.example.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.getOrdersByUserId(userId);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
