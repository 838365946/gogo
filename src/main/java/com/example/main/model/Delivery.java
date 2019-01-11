package com.example.main.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/10.
 */
//投递记录
    @Entity
    @Table
public class Delivery implements Serializable{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int d_id;
        //投递日期
        @Column
    private String d_date;
        //被查看
        @Column
        private String d_beviewed;
        //邀请面试
        @Column
        private String d_offer;
        //不合适ss
        @Column
        private String d_pass;
        @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER)
        @JoinColumn(name = "u_id")
        private User user;
        @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER)
        @JoinColumn(name = "p_id")
        private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getD_beviewed() {
        return d_beviewed;
    }

    public void setD_beviewed(String d_beviewed) {
        this.d_beviewed = d_beviewed;
    }

    public String getD_offer() {
        return d_offer;
    }

    public void setD_offer(String d_offer) {
        this.d_offer = d_offer;
    }

    public String getD_pass() {
        return d_pass;
    }

    public void setD_pass(String d_pass) {
        this.d_pass = d_pass;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getD_date() {
        return d_date;
    }

    public void setD_date(String d_date) {
        this.d_date = d_date;
    }


}
