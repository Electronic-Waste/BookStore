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
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public Book findBookById(int id) {
       return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
       return bookDao.getBooks();
    }

    @Override
    public void addBook(Map<String, String> params) {
        Book book = new Book();
        book.setBookname(params.get("bookname"));
        book.setAuthor(params.get("author"));
        book.setPrice(Double.valueOf(params.get("price")));
        book.setType(params.get("type"));
        book.setDescription(params.get("description"));
        book.setInventory(Integer.parseInt(params.get("inventory")));
        book.setImage(params.get("image"));
        bookDao.save(book);
    }

    @Override
    public void updateBook(Map<String, String> params) {
        Book book = bookDao.findOne(Integer.parseInt(params.get("bookId")));
        book.setBookname(params.get("bookname"));
        book.setAuthor(params.get("author"));
        book.setPrice(Double.valueOf(params.get("price")));
        book.setType(params.get("type"));
        book.setDescription(params.get("description"));
        book.setInventory(Integer.parseInt(params.get("inventory")));
        book.setImage(params.get("image"));
        bookDao.save(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookDao.deleteById(id);
    }
}
