package com.example.backend.serviceimpl;

import com.example.backend.entity.Book;
import com.example.backend.entity.Cart;
import com.example.backend.entity.Order;
import com.example.backend.service.BookService;
import com.example.backend.service.CartService;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CartService cartService;
    @Autowired
    BookService bookService;

    @Override
    public void createOneOrder(String username, String bookid){
        /* Get userid */
        String findUserID = "SELECT UserID FROM user WHERE UserName = ?";
        Object[] params = new Object[] {username};
        String userid = jdbcTemplate.queryForObject(findUserID, params, String.class);

        /* Get Book */
        List<Cart> bookArr = cartService.getCart(username);
        double price = 0;
        int bookNum = 0;
        for (int i = 0; i < bookArr.size(); ++i)
            if (bookArr.get(i).getBook().getBookID().equals(bookid)) {
                bookNum = bookArr.get(i).getNum();
                price = bookArr.get(i).getBook().getPrice() * bookNum;
                break;
            }

        /* Create Order */
        String orderSql = "INSERT INTO orders(UserID, Price) VALUES(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        double finalPrice = price;
        int result= jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                int index = 1;
                PreparedStatement ps = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, userid);
                ps.setDouble(2, finalPrice);
                return ps;
            }
        }, keyHolder);
        int orderid= keyHolder.getKey().intValue();
        System.out.println("OrderID: " + orderid);

        /* Create OrderItem */
        String orderItemSql = "INSERT INTO orderitem(OrderID, BookID, Price, Num) VALUES(?, ?, ?, ?)";
        params = new Object[] {orderid, bookid, price, bookNum};
        jdbcTemplate.update(orderItemSql, params);
    }

    public void createMultipleOrders(String username){
        /* Get userid */
        String findUserID = "SELECT UserID FROM user WHERE UserName = ?";
        Object[] params = new Object[] {username};
        String userid = jdbcTemplate.queryForObject(findUserID, params, String.class);

        /* Get books */
        List<Cart> bookArr = cartService.getCart(username);
        double totalPrice = 0;
        for (int i = 0; i < bookArr.size(); ++i)
            totalPrice += bookArr.get(i).getBook().getPrice() * bookArr.get(i).getNum();

        /* Create Order */
        String orderSql = "INSERT INTO orders(UserID, Price) VALUES(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        double finalTotalPrice = totalPrice;
        int result= jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                int index = 1;
                PreparedStatement ps = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, userid);
                ps.setDouble(2, finalTotalPrice);
                return ps;
            }
        }, keyHolder);
        int orderid= keyHolder.getKey().intValue();
        System.out.println("OrderID: " + orderid);

        /* Create OrderItem */
        for (int i = 0; i < bookArr.size(); ++i) {
            String bookid = bookArr.get(i).getBook().getBookID();
            Double price = bookArr.get(i).getBook().getPrice();
            int bookNum = bookArr.get(i).getNum();
            String orderItemSql = "INSERT INTO orderitem(OrderID, BookID, Price, Num) VALUES(?, ?, ?, ?)";
            params = new Object[] {orderid, bookid, price, bookNum};
            jdbcTemplate.update(orderItemSql, params);
        }
    }

    public List<Order> getOrders(String username){
        String findUserID = "SELECT UserID FROM user WHERE UserName = ?";
        Object[] params = new Object[] {username};
        String userid = jdbcTemplate.queryForObject(findUserID, params, String.class);
        String sql = "SELECT * FROM orders WHERE UserID = ?";
        params = new Object[] {userid};
        List<Order> result = jdbcTemplate.query(sql, params, (rs, rowNum) -> new Order(
                rs.getString("UserID"), rs.getString("OrderID"), rs.getDouble("Price")
        ));
        return result;
    }

    public List<Book> getOrderItems(String orderid){
        String sql = "SELECT * FROM book WHERE BookID in {SELECT BookID FROM orderitem WHERE OrderID = ?}";
        Object[] params = new Object[] {orderid};
        List<Book> result = jdbcTemplate.query(sql, params, (rs, rowNum) -> new Book(
                rs.getString("BookID"), rs.getString("BookName"), rs.getString("Author"),
                rs.getString("Type"), rs.getDouble("Price"), rs.getString("Description"),
                rs.getString("Inventory"), rs.getString("Image")
        ));
        return result;
    }
}
