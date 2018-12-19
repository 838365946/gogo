package com.example.main.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/12/10.
 */
//投递记录
    @Entity
    @Table
public class Delivery {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int d_id;
        //投递日期
        @Column
    private String d_date;
        //被查看
        @Column
        private boolean d_BeViewed;
        //有意向
        @Column
        private boolean d_intent;
        //邀请面试
        @Column
        private boolean d_offer;
        //不合适ss
        @Column
        private boolean d_pass;

    public boolean isD_BeViewed() {
        return d_BeViewed;
    }

    public void setD_BeViewed(boolean d_BeViewed) {
        this.d_BeViewed = d_BeViewed;
    }

    public boolean isD_intent() {
        return d_intent;
    }

    public void setD_intent(boolean d_intent) {
        this.d_intent = d_intent;
    }

    public boolean isD_offer() {
        return d_offer;
    }

    public void setD_offer(boolean d_offer) {
        this.d_offer = d_offer;
    }

    public boolean isD_pass() {
        return d_pass;
    }

    public void setD_pass(boolean d_pass) {
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
