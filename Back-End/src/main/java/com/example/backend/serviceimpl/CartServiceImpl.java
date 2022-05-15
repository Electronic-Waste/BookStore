package com.example.backend.serviceimpl;

import com.example.backend.entity.Book;
import com.example.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean addBookToCart(String username, String bookid) {
        String findUserID = "SELECT UserID FROM user WHERE UserName = ?";
        Object[] params = new Object[] {username};
        String userid = jdbcTemplate.queryForObject(findUserID, params, String.class);
        String sql = "INSERT INTO cart (UserID, BookID) VALUES (?, ?);";
        return jdbcTemplate.update(sql, userid, bookid) > 0;
    }

    @Override
    public boolean deleteBookFromCart(String username, String bookid) {
        String findUserID = "SELECT UserID FROM user WHERE UserName = ?";
        Object[] params = new Object[] {username};
        String userid = jdbcTemplate.queryForObject(findUserID, params, String.class);
        String sql = "DELETE FROM cart WHERE UserID = ? AND BookID = ?";
        return jdbcTemplate.update(sql, userid, bookid) > 0;
    }

    @Override
    public boolean deleteAllBooksFromCart(String username) {
        String findUserID = "SELECT UserID FROM user WHERE UserName = ?";
        Object[] params = new Object[] {username};
        String userid = jdbcTemplate.queryForObject(findUserID, params, String.class);
        String sql = "DELETE FROM cart WHERE UserID = ?";
        return jdbcTemplate.update(sql, userid) > 0;
    }

    @Override
    public List<Book> getCart(String username) {
        String findUserID = "SELECT UserID FROM user WHERE UserName = ?";
        Object[] params = new Object[] {username};
        String userid = jdbcTemplate.queryForObject(findUserID, params, String.class);
        String sql = "SELECT * FROM book WHERE BookID in (" +
                "SELECT BookID FROM cart WHERE UserID = ?)";
        params = new Object[] {userid};
        List<Book> result = jdbcTemplate.query(sql, params, (rs, rowNum)-> new Book(
                rs.getString("BookID"), rs.getString("BookName"), rs.getString("Author"),
                rs.getString("Type"), rs.getDouble("Price"), rs.getString("Description"),
                rs.getString("Inventory"), rs.getString("Image")
        ));
        if (!result.isEmpty()) return result;
        else return null;
    }
}
