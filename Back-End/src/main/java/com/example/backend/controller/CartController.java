package com.example.backend.controller;

import com.example.backend.entity.Book;
import com.example.backend.service.CartService;
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
    public void addTOCart(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String bookid = params.get("bookid");
        cartService.addBookToCart(username, bookid);
    }

    @RequestMapping("/getcart")
    public List<Book> getCart(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        return cartService.getCart(username);
    }

    @RequestMapping("/delfromcart")
    public void delFromCart(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String bookid = params.get("bookid");
        cartService.deleteBookFromCart(username, bookid);
    }

    @RequestMapping("delallfromcart")
    public void delAllFromCart(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        cartService.deleteAllBooksFromCart(username);
    }
}
