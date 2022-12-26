package com.example.backend.service;

import com.example.backend.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    Book findBookById(int id);

    List<Book> getBooks();

    List<Book> getBooksByLabel(String label);

    void addBook(Map<String, String> params);

    void updateBook(Map<String, String> params);

    void deleteBookById(int id);
}
