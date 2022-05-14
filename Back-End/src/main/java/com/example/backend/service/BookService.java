package com.example.backend.service;

import com.example.backend.entity.Book;

import java.util.List;

public interface BookService {

    Book findBookById(String id);

    List<Book> getBooks();
}
