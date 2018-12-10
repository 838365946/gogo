package com.example.main.model;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2018/12/7.
 */
@Entity
public class Message {

    private int m_id;

    private boolean b;

    private String des;

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
