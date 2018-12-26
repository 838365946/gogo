package com.example.main.controller;

import com.example.main.model.Message;
import com.example.main.model.Resume;
import com.example.main.model.User;
import com.example.main.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/12/7.
 */
@RestController
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    @RequestMapping("/addresume")
public Message SaveResume(Resume resume){
    Message message =new Message();
    Resume resume1=resumeService.SaveResume(resume);
if(resume1!=null){
message.setB(true);
message.setDes("新增简历成功");
message.setData(resume1);
}else {
    message.setB(false);
    message.setDes("新增简历失败");
}
return message;
}
@RequestMapping("/updateresume")
public Message UpdateResume(Resume resume){
    Message message =new Message();
    Resume resume1=resumeService.SaveResume(resume);
    if(resume1!=null){
        message.setB(true);
        message.setDes("修改简历成功");
        message.setData(resume1);
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
@RequestMapping("/showresume")
public Message ShowResume(HttpServletRequest request){
User user= (User) request.getSession().getAttribute("user");
Resume resume=resumeService.QueryByUid(user.getId());
Message message=new Message();
if ((resume!=null)){
    message.setB(true);
    message.setDes("获取简历成功");
    message.setData(resume);
}else {
    message.setB(false);
    message.setDes("获取简历失败");
}
return message;
}

}

