package com.example.main.controller;

import com.example.main.model.Message;
import com.example.main.model.Resume;
import com.example.main.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/12/7.
 */
@RestController
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    @RequestMapping("/saveresume")
public Message SaveResume(Resume resume){
    Message message =new Message();
if(resumeService.SaveResume(resume)!=null){
message.setB(true);
message.setDes("新增简历成功");
}else {
    message.setB(false);
    message.setDes("新增简历失败");
}
return message;
}
@RequestMapping("/updateresume")
public Message UpdateResume(Resume resume){
    Message message =new Message();
    if(resumeService.SaveResume(resume)!=null){
        message.setB(true);
        message.setDes("修改简历成功");
    }else {
        message.setB(false);
        message.setDes("修改简历失败");
    }
    return message;
}
@RequestMapping("/delresume")
public Message DelResume(Resume resume) {
    Message message = new Message();
    boolean b = resumeService.DelResume(resume);
    message.setB(b);
if (b){
    message.setDes("删除成功");
}else {
    message.setDes("删除失败");
}return message;
}



}

