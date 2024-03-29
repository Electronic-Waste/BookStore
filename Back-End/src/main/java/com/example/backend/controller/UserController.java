package com.example.backend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.constant.Constant;
import com.example.backend.entity.Cart;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.msgutils.Msg;
import com.example.backend.utils.msgutils.MsgCode;
import com.example.backend.utils.msgutils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Decide whether this user can log in according to userId & password.
     * @param params composed of 'userId', 'password'.
     * @return user's info: if login successfully; LOGIN_USER_ERROR: otherwise.
     */
    @RequestMapping("/login")
    public Msg login(@RequestBody Map<String, String> params) {
//        System.out.println("BackEnd YES");
        int userid = Integer.parseInt(params.get(Constant.USER_ID));
        String password = params.get(Constant.PASSWORD);
//        System.out.println(userid + " " + password);
        User result = userService.checkUser(userid, password);
        System.out.println(result);
        if (result != null) {
            JSONObject data = new JSONObject();
            data.put(Constant.USER_ID, result.getUserId());
            data.put(Constant.USERNAME, result.getUsername());
            data.put(Constant.USER_TYPE, result.getRole());

            userService.startCountTime();   // start count time
//            System.out.println(userService);
//            System.out.println(this);
//            System.out.println(request.getSession());

            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, data);
        }
        else {
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
        }
    }

    /**
     * User logout and end counting time
     * @return online time: long.
     */
    @RequestMapping("/logout")
    public Msg logout() {
//        System.out.println("Logout");
//        System.out.println(userService);
//        System.out.println(this);
//        System.out.println(request.getSession());
        long interval = userService.finishCountTime();  // finish count time
        JSONObject data = new JSONObject();
        data.put("interval", interval);
        System.out.println("Interval" + interval);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, data);
    }

    @RequestMapping("/getUser")
    public Msg getUser(@RequestBody Map<String, String> params) {
        int userid = Integer.parseInt(params.get(Constant.USER_ID));
        User result = userService.getUserById(userid);
        if (result != null) {
            JSONObject data = new JSONObject();
            data.put(Constant.USER_ID, result.getUserId());
            data.put(Constant.USERNAME, result.getUsername());
            data.put(Constant.USER_TYPE, result.getRole());

            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, data);
        }
        else {
            return MsgUtil.makeMsg(MsgCode.ERROR);
        }
    }

    @RequestMapping("/getUsers")
    public JSONArray getUsers() {
        List<User> result = userService.getUsers();
        JSONArray data = new JSONArray();
        for (int i = 0; i < result.size(); ++i) {
            JSONObject subData = new JSONObject();
            subData.put(Constant.USER_ID, result.get(i).getUserId());
            subData.put(Constant.USERNAME, result.get(i).getUsername());
            subData.put(Constant.USER_TYPE, result.get(i).getRole());
            data.add(subData);
        }

        return data;
    }

    @RequestMapping("/banUser")
    public JSONArray banUser(@RequestBody Map<String, String> params) {
        int userId = Integer.parseInt(params.get(Constant.USER_ID));
        int role = Integer.parseInt(params.get(Constant.USER_TYPE));
        userService.banUser(userId, role);
        return getUsers();
    }

    @RequestMapping("/register")
    public Msg register(@RequestBody Map<String, String> params) {
        User user = userService.register(params);
        if (user != null) {
            JSONObject data = new JSONObject();
            data.put(Constant.USER_ID, user.getUserId());
            data.put(Constant.PASSWORD, user.getPassword());

            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, data);
        }
        else {
            return MsgUtil.makeMsg(MsgCode.ERROR);
        }
    }
}
