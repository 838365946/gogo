package com.example.main.controller;

import com.example.main.model.Message;
import com.example.main.model.User;
import com.example.main.service.UserService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
@RequestMapping("/updateuser")
@ResponseBody
public Message UpdateUser(User user) throws NullPointerException{
Message message=new Message();
List<User> users=userService.SelcectByuser(user.getId());
if (users.size()>0){
    User user1=users.get(0);
    user1.setNickname(user.getNickname());
    user1.setEmail(user.getEmail());
    user1.setBirthday(user.getBirthday());
    user1.setCity(user.getCity());
    user1.setSex(user.getSex());
    User u=userService.save(user1);
    if(u!=null){
        message.setB(true);
        message.setDes("修改成功");
        message.setData(u);
    }else {
        message.setB(false);
        message.setDes("修改失败");
    }
}else {
    message.setB(false);
    message.setDes("你还没有个人资料");
}

return message;

}


@RequestMapping(value = "/updatehead", method = { RequestMethod.POST,RequestMethod.GET})
@ResponseBody
public Message UpdateHead(HttpServletRequest request,User user){
    MultipartHttpServletRequest req= (MultipartHttpServletRequest) request;
    MultipartFile head=req.getFile("file");
Message message=new Message();
    CompanyIO companyIO=new CompanyIO();
if (head!=null){
    try {
        companyIO.delFolder("C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.0.45\\bin\\src\\main\\resources\\static\\userlogo\\"+user.getId()+"\\");
    }catch (NullPointerException e){

    }
String path=companyIO.Updatehead(head,user.getId());
List<User> users=userService.SelcectByuser(user.getId());
if (users.size()>0){
    User u=users.get(0);
    u.setHeadicon(path);
    User user1=userService.save(u);
    if(user1!=null){
        message.setB(true);
        message.setDes("修改成功");
        message.setData(user1);
    }else {
        message.setB(false);
        message.setDes("修改失败");
    }
}
}else {
    message.setB(false);
    message.setDes("请先注册");
}
return message;
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
        user.setHeadicon("/userlogo/moren/moren.png");
        User user1=userService.AddUser(user);
        if (user1!=null){
            message.setB(true);
            message.setDes("注册成功");
        message.setData(user1);
        }else{
            message.setB(false);
            message.setDes("注册失败");
        }
        return message;
    }
@RequestMapping("/adduserdata")
@ResponseBody
    public Message AddUserData(User user) throws NullPointerException{
    System.out.println(user.toString());
        List<User> users=userService.SelcectByuser(user.getId());
    System.out.println(users.toString());
        Message message=new Message();
            if(users.size()>0){
                User u= users.get(0);
                u.setBirthday(user.getBirthday());
                u.setCity(user.getCity());
                u.setEmail(user.getEmail());
                u.setExp_time(user.getExp_time());
                u.setName(user.getName());
                u.setSex(user.getSex());
                  User user1=  userService.save(u);
                  if (user1!=null){
                      message.setB(true);
                      message.setDes("fanhui成功");
                      message.setData(user1);
                  }else {
                      message.setB(false);
                      message.setDes("失败");
                  }
            }else {
                message.setB(false);
                message.setDes("请先注册");
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
        int i=userService.UpdatePsw(user);
    if (i>0){
    message.setB(true);
    message.setDes("修改成功");
    }else {
    message.setB(false);
    message.setDes("修改失败");
    }
    return message;
}
@RequestMapping("/getalluser")
@ResponseBody
    public Message Getalluser(String state){
        Message message=new Message();
        message.setB(true);
        message.setDes("获取成功");
        message.setData(userService.FindAll());
        return message;
}

    @RequestMapping("/getuserbystatus")
    @ResponseBody
    public Message GetuserByStatus(String status){
        Message message=new Message();
        message.setB(true);
        message.setDes("获取成功");
        message.setData(userService.FindbyStatus(status));
        return message;
    }
    @RequestMapping("/jcstatus")
    @ResponseBody
    public  Message GaiZt(User user){
        Message message=new Message();
        int i = userService.GaiZt(user,"普通用户");
        if(i>0){
            message.setB(true);
            message.setDes("解除");

        }else {
            message.setB(false);
            message.setDes("解除失败");
        }
        return message;
    }

    @RequestMapping("/ggstatus")
    @ResponseBody
    public  Message JcFy(User user){
        Message message=new Message();
        int i=userService.GaiZt(user,"被封禁");
        if(i>0){
            message.setB(true);
            message.setDes("被封禁");
        }else {
            message.setB(false);
            message.setDes("封失败");
        }return  message;
    }
}
