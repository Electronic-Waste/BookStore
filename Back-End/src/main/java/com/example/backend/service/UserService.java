package com.example.backend.service;

import com.example.backend.entity.UserAuth;
import org.springframework.stereotype.Component;

public interface UserService {

    UserAuth checkUser(String userid, String password);

}
