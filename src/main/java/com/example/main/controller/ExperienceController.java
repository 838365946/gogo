package com.example.main.controller;

import com.example.main.model.Experience;
import com.example.main.model.Message;
import com.example.main.model.User;
import com.example.main.service.ExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/12/14.
 */
@RestController
public class ExperienceController {
    @Autowired
    private ExService exService;
@RequestMapping("/addex")
public Message AddEx(Experience experience){
    Message message=new Message();
    Experience experience1=exService.AddExperience(experience);
    if (experience1!=null){
        message.setB(true);
        message.setDes("新增工作经验成功");
        message.setData(experience1);
    }else {
        message.setB(false);
        message.setDes("新增工作经验失败");
    }
return message;
}

    @RequestMapping("/updateex")
    public Message DelEx(Experience experience){
        Message message=new Message();
        Experience experience1=exService.UpdateEcperience(experience);
        if (experience1!=null){
            message.setB(true);
            message.setDes("修改工作经验成功");
            message.setData(experience1);
        }else {
            message.setB(false);
            message.setDes("修改工作经验失败");
        }
        return message;
    }

    @RequestMapping("/delex")
    public Message UpdateEx(Experience experience){
        Message message=new Message();
        boolean b=exService.delEX(experience);
        message.setB(b);
        if (b){
            message.setDes("删除工作经验成功");
        }else {
            message.setDes("删除工作经验失败");
        }
        return message;
    }
@RequestMapping("/getuserresume")
@ResponseBody
    public Message QueryByUser(User user) {
    System.out.println("第一"+user.getId());
    List<Experience> experiences=exService.QueryByUser(user);
    Message message=new Message();
        if (experiences.size()>0){
        message.setB(true);
        message.setDes("获取简历成功");
        message.setData(experiences);
        }else {
            message.setB(false);
            message.setDes("获取简历失败");
        }
        return message;
    }
    @RequestMapping("/userupdate1")
    public Message UserUpdate (@Param("formdata")Experience experience, @Param("resume")Experience experience1){
        Message message=new Message();
        try {
            experience1.getResume().setR_name(experience.getResume().getR_name());
            experience1.getResume().getUser().setEmail(experience.getResume().getUser().getEmail());
            experience1.getResume().getUser().setSex(experience.getResume().getUser().getSex());
            experience1.getResume().getUser().setCity(experience.getResume().getUser().getCity());
            experience1.getResume().getUser().setBirthday(experience.getResume().getUser().getBirthday());
            experience1.getResume().setR_exp_time(experience1.getResume().getR_exp_time());
        }catch (NullPointerException e){
            message.setB(false);
            message.setDes("传值失败");
        }
        Experience e=exService.UpdateEcperience(experience1);
        if (e!=null){
            message.setB(true);
            message.setDes("修改成功");
            message.setData(e);
        }else {
            message.setB(false);
            message.setDes("修改失败");
        }
return message;
    }
}
