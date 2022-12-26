package com.example.backend.serviceimpl;

import com.example.backend.dao.BookDao;
import com.example.backend.dao.TypeDao;
import com.example.backend.entity.Book;
import com.example.backend.entity.Type;
import com.example.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Autowired
    TypeDao typeDao;

    @Override
    public Book findBookById(int id) {
       return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
       return bookDao.getBooks();
    }

    @Override
    public List<Book> getBooksByLabel(String label) {
        /* Get near types in Neo4jDB */
        List<Type> types = typeDao.findNearTypesByTypename(label);
        /* Get typename(label) of types */
        List<String> labels = new ArrayList<>();
        labels.add(label);
        types.forEach(type -> labels.add(type.getTypeName()));
        /* Search for book using Mysql Database */
        List<Book> result = new ArrayList<>();
        for (String l : labels) {
            List<Book> subBookList = bookDao.getBooksByType(l);
            result.addAll(subBookList);
        }
        return result;
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
