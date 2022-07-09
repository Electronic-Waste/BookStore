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
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public User checkUser(int userid, String password) {
        return userDao.checkUser(userid, password);
    }

    @Override
    public User getUserById(int userid) {
        return userDao.getUserById(userid);
    }

    @Override
    public User register(Map<String, String> params) {
        /* If has repeat username, return null */
        List<User> userList = userDao.getRepeatUsers(params.get("username"));
        if (userList == null) return null;

        /* Else register a new user */
        User user = new User();
        user.setUsername(params.get("username"));
        user.setPassword(params.get("password"));
        user.setEmail(params.get("email"));
        user.setRole(0);
        userDao.save(user);

        /* Get the newly created user */
        List<User> users = getUsers();
        int size = users.size();
        return users.get(size - 1);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void banUser(int userid, int role) {
        User user = userDao.getUserById(userid);
        user.setRole(role);
        userDao.save(user);
    }
}
