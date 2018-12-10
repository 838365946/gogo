package com.example.main.service;

import com.example.main.model.User;

/**
 * Created by Administrator on 2018/12/7.
 */
public interface UserService {
    User Login(User user);
    User WxLogin(String phone_number);
    User AddUser(User user);
}
