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
        @Column
    private String d_date;
        @ManyToOne
        @JoinColumn(name = " c_id")
        private Company company;
        @ManyToOne
        @JoinColumn(name = "phone_number")
        private User user;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
