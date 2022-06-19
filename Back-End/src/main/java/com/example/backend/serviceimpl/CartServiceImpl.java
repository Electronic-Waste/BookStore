package com.example.backend.serviceimpl;

import com.example.backend.dao.BookDao;
import com.example.backend.dao.CartDao;
import com.example.backend.entity.Book;
import com.example.backend.entity.Cart;
import com.example.backend.entity.User;
import com.example.backend.service.CartService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;

    @Autowired
    BookDao bookDao;

    @Override
    public void addBookToCart(String userId, String bookId) {
        Cart cart = cartDao.getCartByUserIdAndBookId(userId, bookId);
        if (cart != null) {
            int num = cart.getNum();
            Double price = cart.getPrice();
            cart.setNum(num + 1);
            cart.setPrice(price / num * (num + 1));
            cartDao.save(cart);
        }
        else {
            Cart newCart = new Cart();
            Book book = bookDao.findOne(bookId);
            newCart.setUserId(userId);
            newCart.setBook(book);
            newCart.setNum(1);
            newCart.setPrice(book.getPrice());
            cartDao.save(newCart);
        }
    }

    @Override
    public void deleteBookFromCart(String userId, String bookId) {
        cartDao.deleteByUserIdAndBookId(userId, bookId);
    }

    @Override
    public void deleteAllBooksFromCart(String userId) {
        cartDao.deleteByUserId(userId);
    }

    @Override
    public List<Cart> getCart(String userId) {
        return cartDao.getCartByUserId(userId);
    }
}
