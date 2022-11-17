package com.example.microorderservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderSumController {
    @RequestMapping("/get-order-sum")
    ResponseEntity<?> getOrderSum(@RequestBody Map<String, JSONArray> params) {
        JSONArray carts = params.get("carts");
        int cartSize = carts.size();
        Double totalPrice = 0.0;
        for (int i = 0; i < cartSize; ++i) {
            JSONObject result = (JSONObject) JSONObject.toJSON(carts.get(i));
            totalPrice += (Double) result.get("price");
        }
        System.out.println(carts);
        System.out.println(totalPrice);

        JSONObject result = new JSONObject();
        result.put("price", totalPrice);
        return ResponseEntity.ok().body(result);

    }
}
