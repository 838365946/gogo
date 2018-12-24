package com.example.main.service.impl;

import com.example.main.dao.UserDao;
import com.example.main.model.User;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/12/7.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Override
    public User Login(User user) {

   return userDao.Login(user.getPhone_number(),user.getPassword());
    }

    @Override
    public User WxLogin(String phone_number) {
        return userDao.WxLogin(phone_number);
    }

    @Override
    public User AddUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User save(User user) {


        return userDao.save(user);
    }

    @Override
    public List<User> SelectById(int id) {
        return   userDao.findbyid1(id);
    }
}
