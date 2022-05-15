package com.example.backend.controller;

import com.alibaba.fastjson.JSON;
import com.example.backend.entity.Book;
import com.example.backend.entity.Order;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/oneorder")
    public void createOrder(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String bookid = params.get("bookid");
        System.out.println(bookid);
        orderService.createOneOrder(username, bookid);
    }

    @RequestMapping("/multipleorders")
    public void createMultipleOrders(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        orderService.createMultipleOrders(username);
    }

    @RequestMapping("/getorders")
    public List<Order> getOrders(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        return orderService.getOrders(username);
    }

    @RequestMapping("getorderitems")
    public List<Book> getOrderItems(@RequestBody Map<String, String> params) {
        String orderid = params.get("orderid");
        return orderService.getOrderItems(orderid);
    }
}
