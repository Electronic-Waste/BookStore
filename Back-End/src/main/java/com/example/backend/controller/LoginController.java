package com.example.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.constant.Constant;
import com.example.backend.entity.UserAuth;
import com.example.backend.service.UserService;
import com.example.backend.utils.msgutils.Msg;
import com.example.backend.utils.msgutils.MsgCode;
import com.example.backend.utils.msgutils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public Msg login(@RequestBody Map<String, String> params) {
        System.out.println("BackEnd YES");
        String userid = params.get(Constant.USER_ID);
        String password = params.get(Constant.PASSWORD);
        System.out.println(userid + password);
        UserAuth result = userService.checkUser(userid, password);
        if (result != null) {
            JSONObject data = new JSONObject();
            data.put(Constant.USERNAME, result.getUserName());
            data.put(Constant.USER_TYPE, result.getRole());

            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, data);
        }
        else {
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
        }
    }

}
