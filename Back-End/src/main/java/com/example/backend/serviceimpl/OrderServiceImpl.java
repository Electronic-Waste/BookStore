package com.example.backend.serviceimpl;

import com.example.backend.dao.BookDao;
import com.example.backend.dao.CartDao;
import com.example.backend.dao.OrderDao;
import com.example.backend.dao.OrderItemDao;
import com.example.backend.entity.*;
import com.example.backend.service.BookService;
import com.example.backend.service.CartService;
import com.example.backend.service.OrderService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    CartDao cartDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    CartService cartService;

    @Transactional
    public void createOneOrder(int userId, int bookId){
        /* Get Book */
        Cart cart = cartDao.getCartByUserIdAndBookId(userId, bookId);
        Book book = bookDao.findOne(bookId);
        double totalPrice = book.getPrice() * cart.getNum();

        /* Create Order */
        Order order = new Order();
        order.setUserId(userId);
        order.setPrice(totalPrice);
        int orderId = orderDao.save(order);

        /* Delete Cart */
        cartService.deleteBookFromCart(userId, bookId);

        /* Create OrderItem */
        try {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setNum(cart.getNum());
            orderItem.setPrice(cart.getPrice());
            orderItem.setOrderId(orderId);
            orderItemDao.save(orderItem);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public void createMultipleOrders(int userId){
        /* Get books */
        List<Cart> cartList = cartDao.getCartByUserId(userId);
        Double totalPrice = 0.0;
        for (int i = 0; i < cartList.size(); ++i)
            totalPrice += cartList.get(i).getPrice();
        System.out.println("totalprice: " + totalPrice);
//        int num = 10 /0;  // error test

        /* Create Order */
        Order order = new Order();
        order.setUserId(userId);
        order.setPrice(totalPrice);
        int orderId = orderDao.save(order);

        /* Delete Cart */
        cartService.deleteAllBooksFromCart(userId);

        /* Create OrderItem */
        try {
            for (int i = 0; i < cartList.size(); ++i) {
                OrderItem orderItem = new OrderItem();
                orderItem.setBook(cartList.get(i).getBook());
                orderItem.setNum(cartList.get(i).getNum());
                orderItem.setPrice(cartList.get(i).getPrice());
                orderItem.setOrderId(orderId);
                orderItemDao.save(orderItem);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

//        int num = 10 /0;  // error test
    }

    public List<Order> getOrders(int userId){
        return orderDao.getOrdersByUserId(userId);
    }

    public List<Book> getOrderItemsBooks(int orderId){
        List<OrderItem> orderItemList = orderItemDao.getOrderItemsByOrderId(orderId);
        List<Book> bookList = new ArrayList<>();
        int length = orderItemList.size();
        for (int i = 0; i < length; ++i)
            bookList.add(orderItemList.get(i).getBook());
        return bookList;
    }
}
