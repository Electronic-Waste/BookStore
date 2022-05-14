package com.example.backend.serviceimpl;

import com.example.backend.entity.Book;
import com.example.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Book findBookById(String id) {
        String sql = "SELECT * FROM book WHERE BookID=" + id;
        List<Book> books = jdbcTemplate.query(sql, ((rs, rowNum) -> new Book(
                rs.getString("BookID"), rs.getString("BookName"), rs.getString("Author"),
                rs.getString("Type"), rs.getDouble("Price"), rs.getString("Description"),
                rs.getString("Inventory"), rs.getString("Image")
        )));
        if (!books.isEmpty()) return books.get(0);
        else return null;
    }

    @Override
    public List<Book> getBooks() {
        String sql = "SELECT * FROM book";
        List<Book> bookExcel = jdbcTemplate.query(sql, ((rs, rowNum) -> new Book(
                rs.getString("BookID"), rs.getString("BookName"), rs.getString("Author"),
                rs.getString("Type"), rs.getDouble("Price"), rs.getString("Description"),
                rs.getString("Inventory"), rs.getString("Image")
        )));
        return bookExcel;
    }
}
