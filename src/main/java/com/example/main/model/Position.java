package com.example.main.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/6.
 */
@Entity
@Table
public class Position implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int p_id;
    //发布的工作名称
    @Column
    private String p_posi_name;
    //工资待遇
    @Column
    private String p_sal;
    //学历限制
    @Column
    private String p_education;
    //工作经验限制
    @Column
    private String P_exp_time;
    //发布日期
    @Column
    private String p_date;
    //工作地址
    @Column
    private String p_addr;
    @Column
    private String p_des;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "c_id")
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Position{" +
                "p_id=" + p_id +
                ", p_posi_name='" + p_posi_name + '\'' +
                ", p_sal='" + p_sal + '\'' +
                ", p_education='" + p_education + '\'' +
                ", P_exp_time='" + P_exp_time + '\'' +
                ", p_date='" + p_date + '\'' +
                ", p_addr='" + p_addr + '\'' +
                ", p_des='" + p_des + '\'' +
                ", company=" + company +
                '}';
    }

    public String getP_des() {
        return p_des;
    }

    public void setP_des(String p_des) {
        this.p_des = p_des;
    }

    public int getP_id() {
        return p_id;
    }


    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_posi_name() {
        return p_posi_name;
    }

    public void setP_posi_name(String p_posi_name) {
        this.p_posi_name = p_posi_name;
    }

    public String getP_sal() {
        return p_sal;
    }

    public void setP_sal(String p_sal) {
        this.p_sal = p_sal;
    }

    public String getP_education() {
        return p_education;
    }

    public void setP_education(String p_education) {
        this.p_education = p_education;
    }

    public String getP_exp_time() {
        return P_exp_time;
    }

    public void setP_exp_time(String p_exp_time) {
        P_exp_time = p_exp_time;
    }

    public String getP_date() {
        return p_date;
    }

    public void setP_date(String p_date) {
        this.p_date = p_date;
    }

    public String getP_addr() {
        return p_addr;
    }

    public void setP_addr(String p_addr) {
        this.p_addr = p_addr;
    }
}
