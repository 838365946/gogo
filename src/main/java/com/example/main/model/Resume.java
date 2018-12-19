package com.example.main.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */
//简历

    @Entity
    @Table
public class Resume {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int r_id;
//姓名
    @Column
private String r_name;
//工作性质
    @Column
    private String r_work_nature;
//工作地址
    @Column
    private String r_work_addr;
    //期望工资
    @Column
    private String r_hope_sal;
//职业类别
    @Column
    private String r_work_category;
    //所在行业
    @Column
    private String r_work_industry;
    //毕业学校
    @Column
    private  String r_edu_school;
    //所学专业
    @Column
    private String r_edu_class;
    //最高学历
    @Column
    private String r_edu_education;
    //学习开始时间
    @Column
    private String r_edu_startdate;
    //学习结束时间
    @Column
    String r_edu_overdate;
    //工作经历
    @OneToMany
    @JoinColumn(name = "r_id")
    private List<Experience> experiences;


    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_edu_startdate() {
        return r_edu_startdate;
    }

    public void setR_edu_startdate(String r_edu_startdate) {
        this.r_edu_startdate = r_edu_startdate;
    }

    public String getR_edu_overdate() {
        return r_edu_overdate;
    }

    public void setR_edu_overdate(String r_edu_overdate) {
        this.r_edu_overdate = r_edu_overdate;
    }


    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getR_work_nature() {
        return r_work_nature;
    }

    public void setR_work_nature(String r_work_nature) {
        this.r_work_nature = r_work_nature;
    }

    public String getR_work_addr() {
        return r_work_addr;
    }

    public void setR_work_addr(String r_work_addr) {
        this.r_work_addr = r_work_addr;
    }

    public String getR_hope_sal() {
        return r_hope_sal;
    }

    public void setR_hope_sal(String r_hope_sal) {
        this.r_hope_sal = r_hope_sal;
    }

    public String getR_work_category() {
        return r_work_category;
    }

    public void setR_work_category(String r_work_category) {
        this.r_work_category = r_work_category;
    }

    public String getR_work_industry() {
        return r_work_industry;
    }

    public void setR_work_industry(String r_work_industry) {
        this.r_work_industry = r_work_industry;
    }

    public String getR_edu_school() {
        return r_edu_school;
    }

    public void setR_edu_school(String r_edu_school) {
        this.r_edu_school = r_edu_school;
    }

    public String getR_edu_class() {
        return r_edu_class;
    }

    public void setR_edu_class(String r_edu_class) {
        this.r_edu_class = r_edu_class;
    }

    public String getR_edu_education() {
        return r_edu_education;
    }

    public void setR_edu_education(String r_edu_education) {
        this.r_edu_education = r_edu_education;
    }



    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }
}
