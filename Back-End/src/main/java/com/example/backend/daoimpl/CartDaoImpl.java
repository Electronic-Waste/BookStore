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
    public List<Cart> getCartByUserId(String userId) {
        return cartRepository.getCartByUserId(userId);
    }

    @Override
    public Cart getCartByUserIdAndBookId(String userId, String bookId) {
        return cartRepository.getCartByUserIdAndBookId(userId, bookId);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteByUserId(String userId) {
        List<Cart> result = cartRepository.getCartByUserId(userId);
        int length = result.size();
        for (int i = 0; i < length; ++i) {
            cartRepository.deleteById(result.get(i).getCartId());
        }
    }

    @Override
    public void deleteByUserIdAndBookId(String userId, String bookId) {
        Cart result = cartRepository.getCartByUserIdAndBookId(userId, bookId);
        cartRepository.deleteById(result.getCartId());
    }
}
