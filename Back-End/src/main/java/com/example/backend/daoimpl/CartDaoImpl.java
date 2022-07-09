package com.example.backend.daoimpl;

import com.example.backend.dao.CartDao;
import com.example.backend.entity.Cart;
import com.example.backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDaoImpl implements CartDao {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<Cart> getCartByUserId(int userId) {
        return cartRepository.getCartByUserId(userId);
    }

    @Override
    public Cart getCartByUserIdAndBookId(int userId, int bookId) {
        return cartRepository.getCartByUserIdAndBookId(userId, bookId);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteByUserId(int userId) {
        List<Cart> result = cartRepository.getCartByUserId(userId);
        int length = result.size();
        for (int i = 0; i < length; ++i) {
            cartRepository.deleteById(result.get(i).getCartId());
        }
    }

    @Override
    public void deleteByUserIdAndBookId(int userId, int bookId) {
        Cart result = cartRepository.getCartByUserIdAndBookId(userId, bookId);
        cartRepository.deleteById(result.getCartId());
    }
}
