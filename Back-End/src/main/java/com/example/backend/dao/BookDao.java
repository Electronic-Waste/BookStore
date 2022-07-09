package com.example.backend.dao;

import com.example.backend.entity.Book;

import java.util.List;

public interface BookDao {
    Book findOne(int id);

    List<Book> getBooks();

    void save(Book book);

    void deleteById(int id);
}
