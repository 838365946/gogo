package com.example.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */
//公司表
    @Entity
    @Table
public class Company implements Serializable{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
        private int c_id;
@Column
        private String c_name;
@Column
private String c_img;
@Column
        private String c_username;
@Column
        private String c_password;
@Column
private String logopath;

//公司福利
    @Column
        private String c_welfare;
        //公司所在行业
    @Column
        private String c_industry;
        //企业性质
    @Column
    private String c_nature;
    //企业规模
    @Column
    private String c_scale;
    //公司介绍
    @Column
    private String c_des;
@Column
    private String c_check_status;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "company")
    private List<Position> positions;

    @Column
    private String c_phone_number;
    @Column
    private String c_addr;


    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public String getC_check_status() {
        return c_check_status;
    }

    public void setC_check_status(String c_check_status) {
        this.c_check_status = c_check_status;
    }

    public String getC_img() {
        return c_img;
    }

    public void setC_img(String c_img) {
        this.c_img = c_img;
    }


    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    public String getC_welfare() {
        return c_welfare;
    }

    public void setC_welfare(String c_welfare) {
        this.c_welfare = c_welfare;
    }

    public String getC_industry() {
        return c_industry;
    }

    public void setC_industry(String c_industry) {
        this.c_industry = c_industry;
    }

    public String getC_nature() {
        return c_nature;
    }

    public void setC_nature(String c_nature) {
        this.c_nature = c_nature;
    }

    public String getC_scale() {
        return c_scale;
    }

    public void setC_scale(String c_scale) {
        this.c_scale = c_scale;
    }

    public String getC_des() {
        return c_des;
    }

    public void setC_des(String c_des) {
        this.c_des = c_des;
    }



    public String getC_phone_number() {
        return c_phone_number;
    }

    public void setC_phone_number(String c_phone_number) {
        this.c_phone_number = c_phone_number;
    }

    public String getC_addr() {
        return c_addr;
    }

    public void setC_addr(String c_addr) {
        this.c_addr = c_addr;
    }

    @Override
    public String toString() {
        return "Company{" +
                "c_id=" + c_id +
                ", c_name='" + c_name + '\'' +
                ", c_img='" + c_img + '\'' +
                ", c_username='" + c_username + '\'' +
                ", c_password='" + c_password + '\'' +
                ", c_welfare='" + c_welfare + '\'' +
                ", c_industry='" + c_industry + '\'' +
                ", c_nature='" + c_nature + '\'' +
                ", c_scale='" + c_scale + '\'' +
                ", c_des='" + c_des + '\'' +
                ", deliveries="  +
                ", c_phone_number='" + c_phone_number + '\'' +
                ", c_addr='" + c_addr + '\'' +
                '}';
    }
}
