package com.example.main.service;

import com.example.main.model.Resume;

/**
 * Created by Administrator on 2018/12/7.
 */
public interface ResumeService {

     Resume SaveResume(Resume resume);
    Resume QueryByPhone(String phone);
    boolean DelResume(Resume resume);
Resume QueryByUid(int uid);
}
