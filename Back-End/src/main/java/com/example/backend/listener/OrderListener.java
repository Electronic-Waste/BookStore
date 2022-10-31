package com.example.backend.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.service.CartService;
import com.example.backend.service.OrderService;
import com.example.backend.websocket.WebSocketServer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private WebSocketServer ws;

    @KafkaListener(topics = "createOrder", groupId = "order_topic")
    public void createOrderListener(ConsumerRecord<String, String> record) {
        JSONObject value = JSONObject.parseObject(record.value());
        System.out.println(value);
        int userId = Integer.parseInt(value.get("userId").toString());
        /* Create order that contains all books in customer's cart */
        if (value.size() == 1) {
            orderService.createMultipleOrders(userId);
        }
        /* Create order that contains exactly one book */
        else {
            int bookId = Integer.parseInt(value.get("bookId").toString());
            orderService.createOneOrder(userId, bookId);
        }
        JSONObject result = new JSONObject();
        result.put("status", "Ok");
        result.put("target", "order");
        result.put("userId", userId);
        result.put("content", "The order is created successfully!");
        kafkaTemplate.send("createOrderResult", "key", result.toJSONString());
        ws.sendMessageToUser(userId, result.toJSONString());
    }

    @KafkaListener(topics = "createOrderResult", groupId = "order_topic")
    public void createOrderResultListener(ConsumerRecord<String, String> record) {
        JSONObject value = JSONObject.parseObject(record.value());
        int userId = Integer.parseInt(value.get("userId").toString());
        ws.sendMessageToUser(userId, value.toJSONString());
        System.out.println(value);
    }
}
