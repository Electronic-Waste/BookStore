package com.example.backend.serviceimpl;

import com.example.backend.entity.Book;
import com.example.backend.entity.Cart;
import com.example.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean addBookToCart(String username, String bookid) {
        /* Get UserID */
        String findUserID = "SELECT UserID FROM user WHERE UserName = ?";
        Object[] params = new Object[] {username};
        String userid = jdbcTemplate.queryForObject(findUserID, params, String.class);

        /* Get updated Num */
        int num = 0;
        boolean isEmpty = false;
        String findNum = "SELECT Num From cart WHERE UserID = ? AND BookID = ?";
        params = new Object[] {userid, bookid};
        try {
            num = jdbcTemplate.queryForObject(findNum, params, int.class);
        } catch (EmptyResultDataAccessException e) {
            isEmpty = true;
        }
        num = isEmpty ? 1 : ++num;


        /* Delete that line if not empty */
        if (!isEmpty) {
            String deleteLine = "DELETE FROM cart WHERE UserID = ? AND BookID = ?";
            params = new Object[] {userid, bookid};
            jdbcTemplate.update(deleteLine, params);
        }

        /* Insert element to cart */
        String sql = "INSERT INTO cart (UserID, BookID, Num) VALUES (?, ?, ?);";
        return jdbcTemplate.update(sql, userid, bookid, num) > 0;
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
    public List<Cart> getCart(String username) {
        String findUserID = "SELECT UserID FROM user WHERE UserName = ?";
        Object[] params = new Object[] {username};
        String userid = jdbcTemplate.queryForObject(findUserID, params, String.class);
        String sql = "SELECT * FROM book natural join cart";
        List<Cart> result = jdbcTemplate.query(sql, (rs, rowNum)-> new Cart(rs.getString("UserID"),new Book(
                rs.getString("BookID"), rs.getString("BookName"), rs.getString("Author"),
                rs.getString("Type"), rs.getDouble("Price"), rs.getString("Description"),
                rs.getString("Inventory"), rs.getString("Image")), rs.getInt("Num")
        ));
        if (!result.isEmpty()) return result;
        else return null;
    }
}
