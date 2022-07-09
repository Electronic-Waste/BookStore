package com.example.backend.service;

import com.example.backend.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User checkUser(int userid, String password);

    User getUserById(int userid);

    User register(Map<String, String> params);

    List<User> getUsers();

    void banUser(int userid, int role);
}
