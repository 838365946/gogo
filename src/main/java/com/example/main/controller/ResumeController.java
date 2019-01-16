package com.example.main.controller;

import com.example.main.dao.ResumeDao;
import com.example.main.model.Experience;
import com.example.main.model.Message;
import com.example.main.model.Resume;
import com.example.main.model.User;
import com.example.main.service.ExService;
import com.example.main.service.ResumeService;
import com.example.main.util.CompanyIO;
import com.example.main.util.DocUtil;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/7.
 */
@RestController
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private ResumeDao resumeDao;
@Autowired
    private ExService exService;
    @RequestMapping("/addresume")
public Message SaveResume(Resume resume, User user, Experience experience){
    Message message =new Message();
    List<Experience>experiences=new ArrayList<Experience>();
    experiences.add(experience);
    resume.setUser(user);
    resume.setExperiences(experiences);
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
@RequestMapping("/getresume")
@ResponseBody
public Message QueryByUser(User user){
    Message message=new Message();
    Resume resume=resumeService.QueryByUid(user.getId());
    List<Experience> experiences=exService.QueryByUser(user.getId());
    resume.setExperiences(experiences);
    System.out.println(resume.getExperiences());
if(resume!=null){
    message.setB(true);
    message.setDes("获取成功");
    message.setData(resume);
}else {
    message.setB(false);
    message.setDes("获取失败");
}
    return message;
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


    @RequestMapping("/userupdate4")
    @ResponseBody
    public Message UserUpdateS (User user, Resume resume){
        System.out.println(user.getId());
        Message message=new Message();
        Resume resume1=resumeService.QueryByUid(user.getId());
        if (resume1!=null){
            try {
              resume1.setR_edu_school(resume.getR_edu_school());
              resume1.setR_edu_class(resume.getR_edu_class());
              resume1.setR_edu_education(resume.getR_edu_education());
              resume1.setR_edu_startdate(resume.getR_edu_startdate());
              resume1.setR_edu_overdate(resume.getR_edu_overdate());
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
    }@RequestMapping("/getallresume")
    public List<Resume> FetAllResume(){
        return resumeDao.findAll();
    }
@RequestMapping("/downloadresume")
public Message OutputResume(HttpServletRequest request,HttpServletResponse response,User user) throws IOException, XmlException {
    Resume resume=resumeService.QueryByUid(user.getId());
    resume.setExperiences(exService.QueryByUser(user.getId()));
 String path=  DocUtil.download(resume);
Message message=new Message();
message.setB(true);
message.setDes("获取成功");
message.setData(path);

return message;
}

    @RequestMapping("/deldocx")
    public void Deldocs(User user){
        Resume resume=resumeService.QueryByUid(user.getId());
        String propath=System.getProperty("user.dir");
        CompanyIO.delAllFile(propath+"\\src\\main\\resources\\static\\resume\\"+resume.getR_id());
    }


}

