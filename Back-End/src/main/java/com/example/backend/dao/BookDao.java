package com.example.backend.dao;

import com.example.backend.entity.Book;

import java.util.List;

public interface BookDao {
    Book findOne(String id);

    List<Book> getBooks();
}
