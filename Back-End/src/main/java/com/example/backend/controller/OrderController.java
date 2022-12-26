package com.example.backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.OrderItemDao;
import com.example.backend.entity.Book;
import com.example.backend.entity.Cart;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderItem;
import com.example.backend.service.OrderService;
import com.example.backend.utils.msgutils.Msg;
import com.example.backend.utils.msgutils.MsgCode;
import com.example.backend.utils.msgutils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

//    @Autowired
//    KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/oneorder")
    public void createOrder(@RequestBody Map<String, String> params) {
        int userId = Integer.parseInt(params.get("userId"));
        int bookId = Integer.parseInt(params.get("bookId"));
        System.out.println(bookId);
        orderService.createOneOrder(userId, bookId);
//        JSONObject object = new JSONObject();
//        object.put("userId", params.get("userId"));
//        object.put("bookId", params.get("bookId"));
//        kafkaTemplate.send("createOrder", "key", object.toJSONString());
    }

    @RequestMapping("/multipleorders")
    public void createMultipleOrders(@RequestBody Map<String, String> params) {
        int userId = Integer.parseInt(params.get("userId"));
        orderService.createMultipleOrders(userId);
//        JSONObject object = new JSONObject();
//        object.put("userId", params.get("userId"));
//        kafkaTemplate.send("createOrder", "key", object.toJSONString());
    }

    @RequestMapping("/getorders")
    public Msg getOrders(@RequestBody Map<String, String> params) {
        int userId = Integer.parseInt(params.get("userId"));
        List<Order> result = orderService.getOrders(userId);
        if (result != null) {
            JSONObject data = new JSONObject();
            data.put("orders", result);
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, data);
        }
        else {
            return MsgUtil.makeMsg(MsgCode.ERROR);
        }

    }

    @RequestMapping("getorderitems")
    public Msg getOrderItemsBooks(@RequestBody Map<String, String> params) {
        String orderid = params.get("orderId");
        List<Book> result = orderService.getOrderItemsBooks(Integer.parseInt(orderid));
        if (result != null) {
            JSONObject data = new JSONObject();
            data.put("books", result);
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, data);
        }
        else {
            return MsgUtil.makeMsg(MsgCode.ERROR);
        }
    }
}
