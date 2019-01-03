package com.example.main.controller;

import com.example.main.model.Experience;
import com.example.main.model.Message;
import com.example.main.model.Resume;
import com.example.main.model.User;
import com.example.main.service.ExService;
import com.example.main.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/12/14.
 */
@RestController
public class ExperienceController {
    @Autowired
    private ExService exService;
    @Autowired
    private ResumeService resumeService;

@RequestMapping("/addex")
public Message AddEx(User user,Experience experience){
    System.out.println(user.getId());
    Message message=new Message();
    List<Experience> experienceList=exService.QueryByUser(user.getId());

    Resume resume=resumeService.QueryByUid(user.getId());
    if(experienceList.size()>=3){
message.setB(false);
message.setDes("最多只能填写三次工作经验");
    }else {
        Experience experience2=new Experience();
        int count=experienceList.size()+1;
        experience2.setE_comp_name(experience.getE_comp_name());
        experience2.setE_comp_position(experience.getE_comp_position());
        experience2.setE_industry(experience.getE_industry());
        experience2.setE_word_des(experience.getE_word_des());
        experience2.setE_sal(experience.getE_sal());
        experience2.setE_date(experience.getE_date());
        experience2.setCount(count);
   experience2.setResume(resume);
        Experience experience1= null;
        try {
            experience1 = exService.AddExperience(experience2);
        } catch (IOException e) {
            message.setB(false);
            message.setDes("新增工作经验失败");
        }
        if (experience1!=null){
            message.setB(true);
            message.setDes("新增工作经验成功");
        }else {
            message.setB(false);
            message.setDes("新增工作经验失败");
        }
    }
return message;
}

@RequestMapping("/getuserresume")
@ResponseBody
    public Message QueryByUser(User user) {
    List<Experience> experiences=exService.QueryByUser(user.getId());
    System.out.println(experiences);
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
    @ResponseBody
    public Message UserUpdate (User user) throws IOException {
        System.out.println(user.toString());
        Message message=new Message();
       List<Experience> experiences= exService.QueryByUser(user.getId());
     if(experiences.size()>0){
         Experience experience1=experiences.get(0);
        if (experience1!=null){
            try {
                experience1.getResume().setR_name(user.getName());
                experience1.getResume().getUser().setName(user.getName());
                experience1.getResume().getUser().setEmail(user.getEmail());
                experience1.getResume().getUser().setSex(user.getSex());
                experience1.getResume().getUser().setCity(user.getCity());
                experience1.getResume().getUser().setBirthday(user.getBirthday());
                experience1.getResume().setR_exp_time(user.getExp_time());
                experience1.getResume().getUser().setExp_time(user.getExp_time());
            }catch (NullPointerException e){
                message.setB(false);
                message.setDes("传值失败");
            }
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
     }else {
         message.setB(false);
         message.setDes("出现未知错误");
     }
return message;
    }


    @RequestMapping("/userupdate3")
    @ResponseBody
    public Message UserUpdateE (User user,Experience experience) throws IOException {
        System.out.println(user.toString());
        Message message=new Message();
        List<Experience> experiences= exService.QueryByUser(user.getId());
        if(experiences.size()>0){
            Experience experience1=experiences.get(experience.getCount());
            if (experience1!=null){
                try {
                    experience1.setE_comp_name(experience.getE_comp_name());
                    experience1.setE_comp_position(experience.getE_comp_position());
                    experience1.setE_date(experience.getE_date());
                    experience1.setE_industry(experience.getE_industry());
                    experience1.setE_word_des(experience.getE_word_des());
                    experience1.setE_sal(experience.getE_sal());
                }catch (NullPointerException e){
                    message.setB(false);
                    message.setDes("传值失败");
                }
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
        }else {
            message.setB(false);
            message.setDes("出现未知错误");
        }
        return message;
    }
@RequestMapping("/checkex")
    @ResponseBody
    public Message CheckEx(User user){
        Message message=new Message();
List<Experience> experiences=exService.QueryByUser(user.getId());
if (experiences.size()>3){
message.setB(true);
message.setDes("可以添加");
}else {
    message.setB(false);
    message.setDes("最多只能添加三条工作经验");
}
return message;
}

}
