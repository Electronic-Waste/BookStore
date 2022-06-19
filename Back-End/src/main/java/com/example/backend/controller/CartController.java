package com.example.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.constant.Constant;
import com.example.backend.entity.Book;
import com.example.backend.entity.Cart;
import com.example.backend.service.CartService;
import com.example.backend.utils.msgutils.Msg;
import com.example.backend.utils.msgutils.MsgCode;
import com.example.backend.utils.msgutils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping("/addtocart")
    public void addToCart(@RequestBody Map<String, String> params) {
        String userId = params.get("userId");
        String bookId = params.get("bookId");
        cartService.addBookToCart(userId, bookId);
    }

    @RequestMapping("/getcart")
    public Msg getCart(@RequestBody Map<String, String> params) {
        String userId = params.get("userId");
        List<Cart> result = cartService.getCart(userId);
        if (result != null) {
            JSONObject data = new JSONObject();
            data.put("carts", result);
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, data);
        }
        else {
            return MsgUtil.makeMsg(MsgCode.ERROR);
        }
    }

    @RequestMapping("/delfromcart")
    public void delFromCart(@RequestBody Map<String, String> params) {
        String userId = params.get("userId");
        String bookId = params.get("bookId");
        cartService.deleteBookFromCart(userId, bookId);
    }

    @RequestMapping("delallfromcart")
    public void delAllFromCart(@RequestBody Map<String, String> params) {
        String userId= params.get("userId");
        cartService.deleteAllBooksFromCart(userId);
    }
}
