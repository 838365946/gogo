package com.example.main.service;

import com.example.main.model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/12/7.
 */
public interface UserService {
    User Login(User user);
    User WxLogin(String phone_number);
    User AddUser(User user);
    User save(User user);
   List<User> SelectById(int id);
}
