package com.example.backend.daoimpl;

import com.example.backend.dao.OrderItemDao;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderItem;
import com.example.backend.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemDaoImpl implements OrderItemDao {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public int save(OrderItem orderItem) {
//        int i = 10 / 0;     // error test
        return orderItemRepository.save(orderItem).getOrderitemId();
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        return orderItemRepository.getOrderItemsByOrderId(orderId);
    }
}
