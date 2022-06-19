package com.example.backend.daoimpl;

import com.example.backend.dao.BookDao;
import com.example.backend.entity.Book;
import com.example.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(String id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }
}
