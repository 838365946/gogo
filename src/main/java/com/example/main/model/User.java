package com.example.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */
//用户类

    @Entity
    @Table
public class User implements Serializable{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
@Column
        private String nickname;
@Column
        private String headicon;
@Column
private String name;

@Column
        private String password;
@Column
        private String wx_number;
@Column
        private String phone_number;
@Column
        private String email;
@Column
private String birthday;
@Column
        private String city;
@Column
        private String sex;
@Column
        private int age;
@Column
private String isadmin;
@Column
private String exp_time;
    @JsonIgnore
@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,mappedBy = "user")
        private List<Resume> resumes;
    @JsonIgnore
@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,mappedBy = "user")
private List<Delivery> deliveries;

    public String getIsadmin() {
        return isadmin;
    }

    public String getHeadicon() {
        return headicon;
    }

    public void setHeadicon(String headicon) {
        this.headicon = headicon;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }
    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWx_number() {
        return wx_number;
    }

    public void setWx_number(String wx_number) {
        this.wx_number = wx_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExp_time() {
        return exp_time;
    }

    public void setExp_time(String exp_time) {
        this.exp_time = exp_time;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", wx_number='" + wx_number + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", city='" + city + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", isadmin='" + isadmin + '\'' +
                ", exp_time='" + exp_time + '\'' +
                ", resumes=" + resumes +
                ", deliveries=" + deliveries +
                '}';
    }
}
