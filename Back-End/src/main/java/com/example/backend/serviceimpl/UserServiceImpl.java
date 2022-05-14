package com.example.backend.serviceimpl;

import com.example.backend.constant.Constant;
import com.example.backend.entity.UserAuth;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserAuth checkUser(String userid, String password) {
        String sql = "SELECT UserName, Role FROM user" +
                " WHERE UserID=" + userid + " AND Password=" + password;
        List<UserAuth> result = jdbcTemplate.query(sql, (rs, rowNum) -> new UserAuth(rs.getString(Constant.USERNAME), rs.getInt(Constant.USER_TYPE)));
        if (!result.isEmpty())
            return result.get(0);
        else return null;
    }
}
