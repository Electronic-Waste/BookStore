package com.example.backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.entity.Book;
import com.example.backend.service.BookService;
import com.example.backend.solr.SolrUtil;
import com.example.backend.utils.msgutils.Msg;
import com.example.backend.utils.msgutils.MsgCode;
import com.example.backend.utils.msgutils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private SolrUtil solrUtil;

    @RequestMapping("/getBooks")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestBody Map<String, String> params){
        int id = Integer.parseInt(params.get("id"));
        return bookService.findBookById(id);
    }

    @RequestMapping("/getBooksByLabel")
    public List<Book> getBooksByLabel(@RequestBody Map<String, String> params) {
        String label = params.get("label");
        return bookService.getBooksByLabel(label);
    }

    @RequestMapping("/addBook")
    public void addBook(@RequestBody Map<String, String> params) {
        bookService.addBook(params);
    }

    @RequestMapping("/updateBook")
    public void updateBook(@RequestBody Map<String, String> params) {
        bookService.updateBook(params);
    }

    @RequestMapping("/deleteBook")
    public void deleteBook(@RequestBody Map<String, String> params) {
        int id = Integer.parseInt(params.get("id"));
        bookService.deleteBookById(id);
    }

    @RequestMapping("/solr/search")
    public JSONArray solrFullTextSearch(@RequestBody Map<String, String> params) {
        String filterText = params.get("filterText");
        List<Book> result = solrUtil.fullTextSearch(filterText);
        return JSONArray.parseArray(JSON.toJSONString(result));
    }
}
