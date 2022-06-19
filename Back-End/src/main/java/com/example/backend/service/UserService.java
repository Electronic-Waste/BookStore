package com.example.backend.service;

import com.example.backend.entity.User;

public interface UserService {

    User checkUser(String userid, String password);

    User getUserById(String userid);

}
