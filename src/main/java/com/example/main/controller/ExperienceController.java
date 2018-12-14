package com.example.main.controller;

import com.example.main.model.Experience;
import com.example.main.model.Message;
import com.example.main.service.ExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/12/14.
 */
@RestController
public class ExperienceController {
    @Autowired
    private ExService exService;
@RequestMapping("addex")
public Message AddEx(Experience experience){
    Message message=new Message();
    Experience experience1=exService.AddExperience(experience);
    if (experience1!=null){
        message.setB(true);
        message.setDes("新增工作经验成功");
    }else {
        message.setB(false);
        message.setDes("新增工作经验失败");
    }
return message;
}

    @RequestMapping("updateex")
    public Message DelEx(Experience experience){
        Message message=new Message();
        Experience experience1=exService.UpdateEcperience(experience);
        if (experience1!=null){
            message.setB(true);
            message.setDes("修改工作经验成功");
        }else {
            message.setB(false);
            message.setDes("修改工作经验失败");
        }
        return message;
    }

    @RequestMapping("delex")
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
}
