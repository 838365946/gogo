package com.example.main.controller;

import com.example.main.model.Message;
import com.example.main.model.Resume;
import com.example.main.model.User;
import com.example.main.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/tt")
@ResponseBody
public Resume QueryByUser(User user){
    System.out.println(user.getId());
   return resumeService.QueryByUid(user.getId());
}


    @RequestMapping("/userupdate2")
    @ResponseBody
    public Message UserUpdateR (User user, Resume resume){
        System.out.println(user.getId());
        Message message=new Message();
        Resume resume1=resumeService.QueryByUid(user.getId());
            if (resume1!=null){
                try {
                    resume1.setR_work_nature(resume.getR_work_nature());
                    resume1.setR_hope_sal(resume.getR_hope_sal());
                    resume1.setR_work_addr(resume.getR_work_addr());
                    resume1.setR_work_industry(resume.getR_work_industry());
                    resume1.setR_work_category(resume.getR_work_category());
                    Resume resume2=resumeService.SaveResume(resume1);
                  if(resume2!=null){
                      message.setB(true);
                      message.setDes("修改成功");
                      message.setData(resume2);
                  }else {
                      message.setB(false);
                      message.setDes("修改失败");
                  }
                }catch (NullPointerException e){
                    message.setB(false);
                    message.setDes("传值失败");
                }


            }else {
                message.setB(false);
                message.setDes("你还没有简历");
            }

        return message;
    }
}

