package com.example.backend.dao;

import com.example.backend.entity.User;

public interface UserDao {

    User checkUser(String username, String password);

    User getUserById(String userid);

}
