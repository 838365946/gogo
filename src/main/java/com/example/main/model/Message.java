package com.example.main.model;

/**
 * Created by Administrator on 2018/12/7.
 */

public class Message {


    private boolean b;

    private String des;


private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
                "b=" + b +
                ", des='" + des + '\'' +
                ", data=" + data +
                '}';
    }
}
