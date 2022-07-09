package com.example.backend.daoimpl;

import com.example.backend.dao.UserDao;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(int userid, String password) {
        return userRepository.checkUser(userid, password);
    }

    @Override
    public User getUserById(int userid) {
        return userRepository.getById(userid);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public List<User> getRepeatUsers(String username) {
        return userRepository.getRepeatUsers(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
