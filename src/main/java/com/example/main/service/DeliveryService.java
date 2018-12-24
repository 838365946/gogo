package com.example.main.service;

import com.example.main.model.Delivery;

/**
 * Created by Administrator on 2018/12/10.
 */
public interface DeliveryService {
    Delivery SubmitRuseme(Delivery delivery);

    void selectByCompany(int id);
}
