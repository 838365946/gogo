package com.example.main.service.impl;

import com.example.main.dao.ResumeDao;
import com.example.main.model.Resume;
import com.example.main.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/12/7.
 */
@Service("resumeService")
public class ResumeServiceImpl implements ResumeService{
    @Autowired
    private ResumeDao resumeDao;
    @Override
    public Resume SaveResume(Resume resume) {
       Resume resume1= resumeDao.save(resume);
        return resume1;
    }

    @Override
    public Resume QueryByPhone(String phone) {
        Resume resume=resumeDao.QueryResumeByPhoneNumber(phone);
        return resume;
    }



}
