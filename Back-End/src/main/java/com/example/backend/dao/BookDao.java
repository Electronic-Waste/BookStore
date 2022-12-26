package com.example.backend.dao;

import com.example.backend.entity.Book;

import java.util.List;

public interface BookDao {
    Book findOne(int id);

    List<Book> getBooks();

    List<Book> getBooksByType(String type);

    void save(Book book);

    void deleteById(int id);
}
