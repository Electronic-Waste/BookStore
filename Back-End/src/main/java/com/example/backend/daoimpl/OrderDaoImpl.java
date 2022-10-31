package com.example.backend.daoimpl;

import com.example.backend.dao.OrderDao;
import com.example.backend.entity.Order;
import com.example.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderRepository.getOrdersByUserId(userId);
    }

    @Override
    @Transactional
    public int save(Order order) {
//        int i = 10 / 0;         // error test
        return orderRepository.save(order).getOrderId();
    }
}
