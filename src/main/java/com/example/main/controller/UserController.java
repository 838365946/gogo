package com.example.main.controller;

import com.example.main.model.Message;
import com.example.main.model.User;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Created by Administrator on 2018/12/7.
 */
@Controller
public class UserController {
@Autowired
    private UserService userService;
@RequestMapping("/")
public String ToIndex(){

    return "index";
}
@RequestMapping("login")
@ResponseBody
    public ModelAndView Login(User user){

    ModelAndView modelAndView=new ModelAndView();
    Message message=new Message();
if (userService.Login(user)!=null){
    message.setB(true);

    message.setDes("登录成功");
    modelAndView.addObject(message);
    modelAndView.addObject(user);
}else {
    message.setB(false);
    message.setDes("登录失败");
modelAndView.addObject(message);
}
modelAndView.setView(new MappingJackson2JsonView());
return modelAndView;
    }
@RequestMapping("wxlogin")
@ResponseBody
    public User WxLogin(String phone_number){
        return userService.WxLogin(phone_number);
    }
    @RequestMapping("adduser")
    public Message AddUser(User user){
        Message message=new Message();
        if (userService.AddUser(user)!=null){
            message.setB(true);
            message.setDes("注册成功");
        }else{
            message.setB(false);
            message.setDes("注册失败");
        }
        return message;
    }

@RequestMapping("addcheck")
    public Message addcheck(String phonenumber){

        Message message =new Message();
if (userService.WxLogin(phonenumber)!=null){
    message.setB(true);
    message.setDes("该手机号可以使用");
}else {
    message.setB(false);
    message.setDes("该账号已被注册");
}
return message;
}
}
