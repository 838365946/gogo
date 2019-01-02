package com.example.main.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/6.
 */
//工作经历表

    @Entity
    @Table
public class Experience implements Serializable{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
        private int e_id;
        //公司名称
    @Column
        private String e_comp_name;

        //所在职位
    @Column
    private String e_comp_position;
    //在职时间
@Column
    private String e_date;
    //所属行业
@Column
    private String e_industry;
//工作描述
    @Column
    private String e_word_des;
    //当前月薪
    @Column
    private int count;
    @Column
    private String e_sal;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "r_id")
    private Resume resume;

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_comp_name() {
        return e_comp_name;
    }

    public void setE_comp_name(String e_comp_name) {
        this.e_comp_name = e_comp_name;
    }

    public String getE_comp_position() {
        return e_comp_position;
    }

    public void setE_comp_position(String e_comp_position) {
        this.e_comp_position = e_comp_position;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }

    public String getE_industry() {
        return e_industry;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setE_industry(String e_industry) {
        this.e_industry = e_industry;
    }

    public String getE_word_des() {
        return e_word_des;
    }

    public void setE_word_des(String e_word_des) {
        this.e_word_des = e_word_des;
    }

    public String getE_sal() {
        return e_sal;
    }

    public void setE_sal(String e_sal) {
        this.e_sal = e_sal;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
