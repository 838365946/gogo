package com.example.main.controller;

import com.example.main.model.Experience;
import com.example.main.model.Message;
import com.example.main.model.Resume;
import com.example.main.model.User;
import com.example.main.service.ExService;
import com.example.main.service.ResumeService;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    @Autowired
    private UserService userService;

@RequestMapping("/addex")
public Message AddEx(User user,Experience experience){
    System.out.println(user.getId());
    Message message=new Message();
    List<Experience> experienceList=exService.QueryByUser(user.getId());

    Resume resume=resumeService.QueryByUid(user.getId());
    if(experienceList.size()>=3){
message.setB(false);
message.setDes("最多只能填写三次工作经验l");
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
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间
            Calendar birth = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date bithday = format.parse(user.getBirthday());
            birth.setTime(bithday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }

        } catch (Exception e) {//兼容性更强,异常后返回数据
            age=0;
        }

        System.out.println(user.toString());
        Message message=new Message();
        List<User> users=userService.SelectById(user.getId());

        if (users.size()>0){
          User u=  users.get(0);
          u.setSex(user.getSex());
          u.setCity(user.getCity());
          u.setBirthday(user.getBirthday());
          u.setAge(age);
          u.setEmail(user.getEmail());
          u.setExp_time(user.getExp_time());
          u.setName(user.getName());
          User user1=userService.save(u);
          if ((user1!=null)){
              message.setB(true);
              message.setDes("修改成功");
              message.setData(user1);
          }else {
              message.setB(false);
              message.setDes("修改失败");
          }
        }else {
            message.setB(false);
            message.setDes("没有找到该用户的信息");
        }
return message;
    }


    @RequestMapping("/userupdate3")
    @ResponseBody
    public Message UserUpdateE (User user, Experience experience) throws IOException {
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
    System.out.println(experiences.size());
if (experiences.size()<3){
message.setB(true);
message.setDes("可以添加");
}else {
    message.setB(false);
    message.setDes("最多三条");
}
return message;
}
@RequestMapping("/delex")
    @ResponseBody
    public Message DelEx(Experience experience){
        Message message=new Message();
        try {
            exService.DelEx(experience.getE_id());
            message.setB(true);
            message.setDes("删除成功");
        }catch (Exception e){
            message.setB(false);
            message.setDes("删除失败");
        }
return message;
}

}
