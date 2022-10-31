package com.example.backend.daoimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.BookDao;
import com.example.backend.entity.Book;
import com.example.backend.redis.RedisUtil;
import com.example.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Book findOne(int id) {
        Book book = null;
        List<Book> bookList = null;
        System.out.println("Searching Book: " + id + " in Redis");
        Object bs = redisUtil.get("books");
        if (bs == null) {
            System.out.println("Book: " + id + " is not in Redis");
            System.out.println("Searching Book: " + id + " in DB");
            bookList = bookRepository.getBooks();
            for (Book b: bookList)
                if (b.getBookId() == id) book = b;
            redisUtil.set("books", JSONArray.toJSON(bookList));
        } else {
            System.out.println("Book: " + id + " is in Redis");
            bookList = JSONArray.parseArray(bs.toString(), Book.class);
            for (Book b: bookList)
                if (b.getBookId() == id) book = b;
        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> bookList = null;
        System.out.println("Getting all books from Redis");
        Object bs = redisUtil.get("books");
        if (bs == null) {
            System.out.println("Books are not cached in Redis");
            System.out.println("Getting all books from DB");
            bookList = bookRepository.getBooks();
            System.out.println("Saving books to Redis");
            redisUtil.set("books", JSONArray.toJSON(bookList));
        } else {
            System.out.println("Books are in Redis");
            bookList = JSONArray.parseArray(bs.toString(), Book.class);
        }
        return bookRepository.getBooks();
    }

    @Override
    public void save(Book book) {
        Book newBook = bookRepository.save(book);
        int newBookId = newBook.getBookId();
        System.out.println("Saving book: " + newBookId + " to DB");
        System.out.println("Saving book: " + newBookId + " to Redis");
        List<Book> bookList = JSONArray.parseArray(redisUtil.get("books").toString(), Book.class);
        bookList.add(newBook);
        redisUtil.set("books", JSONArray.toJSON(bookList));

    }

    @Override
    public void deleteById(int id) {
        System.out.println("Deleting book: " + id + " from Redis");
        redisUtil.del("book" + id);
        System.out.println("Deleting book: " + id + " from DB");
        List<Book> bookList = JSONArray.parseArray(redisUtil.get("books").toString(), Book.class);
        bookList.removeIf(b -> b.getBookId() == id);
        redisUtil.set("books", JSONArray.toJSON(bookList));
        bookRepository.deleteById(id);
    }
}
