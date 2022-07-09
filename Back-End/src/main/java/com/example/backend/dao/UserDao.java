package com.example.backend.dao;

import com.example.backend.entity.User;

import java.util.List;

public interface UserDao {

    User checkUser(int userid, String password);

    User getUserById(int userid);

    List<User> getUsers();

    List<User> getRepeatUsers(String username);

    void save(User user);
}
