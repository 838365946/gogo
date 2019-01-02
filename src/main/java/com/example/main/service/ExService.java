package com.example.main.service;

import com.example.main.model.Experience;
import com.example.main.model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/12/14.
 */
public interface ExService {
    Experience AddExperience(Experience experience);
    boolean delEX(Experience experience);
    Experience UpdateEcperience(Experience experience);
    List<Experience> QueryByUser(User user);
}
