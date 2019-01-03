package com.example.main.controller;

import com.example.main.model.Message;
import com.example.main.model.User;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/12/7.
 */
@Controller
public class UserController {
@Autowired
    private UserService userService;
@RequestMapping("/")
public String ToIndex(HttpServletRequest request){
if(request.getSession().getAttribute("user")!=null||request.getSession().getAttribute("company")!=null){
    return "main";
}
    return "login";
}



@RequestMapping("/login")
@ResponseBody
    public Message Login(User user){


    Message message=new Message();
    User user1=userService.Login(user);
if (user1!=null){
        message.setB(true);
        message.setDes("登录成功");
        message.setData(user1);
}else {
    message.setB(false);
    message.setDes("账号或密码错误");
}
return message;
    }

    @RequestMapping("/adminlogin")
    @ResponseBody
    public ModelAndView Login(User user,HttpServletRequest request){

ModelAndView modelAndView=new ModelAndView();
        User user1=userService.Login(user);
      
        if (user1!=null){
            if (user1.getIsadmin().equals("是")){
                modelAndView.setViewName("mainht");
                    request.getSession().setAttribute("user",user1);
            }else {
                modelAndView.setViewName("error1");
            }
        }else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

@RequestMapping("/wxlogin")
@ResponseBody
    public Message WxLogin(String phone_number){
        Message message=new Message();
    User user=userService.WxLogin(phone_number);
    if (user!=null){
        message.setB(true);
        message.setDes("微信登录获取数据成功");
        message.setData(user);
    }else {
        message.setB(false);
        message.setDes("没有数据，请绑定您的智障号");
    }
        return message;
    }
    @RequestMapping("adduser")
    @ResponseBody
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

@RequestMapping("/check")
@ResponseBody
    public Message addcheck(String phone_number){
        Message message =new Message();
if (userService.Check(phone_number)==null){
    message.setB(true);
    message.setDes("该帐号可以使用");
}else {
    message.setB(false);
    message.setDes("该账号已被注册");
}
return message;
}
@RequestMapping("/updatepassword")
@ResponseBody
    public Message updatepassword(User user){
        Message message=new Message();
        User u=userService.save(user);
    if (u!=null){
    message.setB(true);
    message.setDes("修改成功");
    }else {
    message.setB(false);
    message.setDes("修改失败");
    }
    return message;
}
}
