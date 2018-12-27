package com.example.main.model;

/**
 * Created by Administrator on 2018/12/7.
 */

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

    @Override
    public String toString() {
        return "Message{" +
                "m_id=" + m_id +
                ", b=" + b +
                ", des='" + des + '\'' +
                '}';
    }
}
