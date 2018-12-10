package com.example.main.service.impl;

import com.example.main.dao.DeliveryDao;
import com.example.main.model.Delivery;
import com.example.main.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/12/10.
 */
@Service("deliveryService")
public class DeliveryServiceImpl implements DeliveryService{
    @Autowired
    private DeliveryDao deliveryDao;
    @Override
    public Delivery SubmitRuseme(Delivery delivery) {
      Delivery delivery1=  deliveryDao.save(delivery);
      return delivery1;
    }
}
