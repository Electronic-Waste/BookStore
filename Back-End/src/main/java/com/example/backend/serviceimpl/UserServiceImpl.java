package com.example.backend.serviceimpl;

import com.example.backend.constant.Constant;
import com.example.backend.dao.BookDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.Book;
import com.example.backend.entity.Cart;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public User checkUser(String userid, String password) {
        return userDao.checkUser(userid, password);
    }

    @Override
    public User getUserById(String userid) {
        return userDao.getUserById(userid);
    }
}
