package com.example.main.service;

import com.example.main.model.Experience;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/12/14.
 */
public interface ExService {
    Experience AddExperience(Experience experience) throws IOException;
    boolean delEX(Experience experience);
    Experience UpdateEcperience(Experience experience) throws IOException;
    List<Experience> QueryByUser(int id);
    void DelEx(int eid);
}
