package com.example.main.service;

import com.example.main.model.Delivery;

import java.util.List;

/**
 * Created by Administrator on 2018/12/10.
 */
public interface DeliveryService {
    Delivery SubmitRuseme(Delivery delivery);

    List<Delivery> selectByPid(int id);
    int Updatestate(String str,Delivery delivery);
}
