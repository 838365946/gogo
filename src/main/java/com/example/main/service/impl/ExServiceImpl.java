package com.example.main.service.impl;

import com.example.main.dao.ExperienceDao;
import com.example.main.model.Experience;
import com.example.main.service.ExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/12/14.
 */
@Service("exService")
public class ExServiceImpl implements ExService{
    @Autowired
    ExperienceDao experienceDao;
    @Override
    public Experience AddExperience(Experience experience) {
        return experienceDao.save(experience);
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
    public Experience UpdateEcperience(Experience experience) {

        return experienceDao.save(experience);
    }

    @Override
    public List<Experience> QueryByUser(int id) {
        return experienceDao.QueryByUser(id);
    }
}
