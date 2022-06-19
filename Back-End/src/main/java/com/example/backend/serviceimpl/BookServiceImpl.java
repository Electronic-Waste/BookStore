package com.example.backend.serviceimpl;

import com.example.backend.dao.BookDao;
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
    BookDao bookDao;

    @Override
    public Book findBookById(String id) {
       return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
       return bookDao.getBooks();
    }
}
