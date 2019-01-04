package com.example.main.service.impl;

import com.example.main.dao.ExperienceDao;
import com.example.main.model.Experience;
import com.example.main.service.ExService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/12/14.
 */
@Service("exService")
public class ExServiceImpl implements ExService{
    @Autowired
    ExperienceDao experienceDao;
    private CompanyIO companyIO=new CompanyIO();
    @Override
    public Experience AddExperience(Experience experience) throws IOException,NullPointerException {
        String des=experience.getE_word_des();
        experience.setE_word_des("上传出错");
Experience experience1=experienceDao.save(experience);
String path=companyIO.WriteExDes(des,experience1.getE_id());
experience1.setE_word_des(path);
Experience experience2=experienceDao.save(experience1);
        return experience2;
    }

    @Override
    public boolean delEX(Experience experience) {
        try {
            experienceDao.delete(experience);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Experience UpdateEcperience(Experience experience) throws IOException ,NullPointerException{
        String des=experience.getE_word_des();
        experience.setE_word_des("上传失败");
        Experience experience1=experienceDao.save(experience);
        String path=companyIO.WriteExDes(des,experience1.getE_id());
        experience1.setE_word_des(path);
        Experience experience2=experienceDao.save(experience1);
        return experience2;
    }

    @Override
    public List<Experience> QueryByUser(int id) {
        List<Experience> experiences=experienceDao.QueryByUser(id);
        for (Experience e:experiences){
            StringBuffer des=companyIO.ReadDes(e.getE_word_des());
            e.setE_word_des(String.valueOf(des));
        }
        return experiences;
    }

    @Override
    public void DelEx(int eid) {
        experienceDao.deleteById((long) eid);

    }
}
