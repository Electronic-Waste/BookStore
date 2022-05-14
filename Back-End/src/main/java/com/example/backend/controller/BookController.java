package com.example.backend.controller;

import com.example.backend.entity.Book;
import com.example.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
    public List<Book> getBooks() {
        System.out.println("In /getBooks");
        return bookService.getBooks();
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestBody Map<String, String> params){
        String id = params.get("id");
        System.out.println("In /getBook");
        return bookService.findBookById(id);
    }
}
