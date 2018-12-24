package com.example.main.service.impl;

import com.example.main.dao.DeliveryDao;
import com.example.main.model.Delivery;
import com.example.main.model.User;
import com.example.main.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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



    @Override
    public void selectByCompany(int id) {
       List<User> users=null;
       List<Object> result=deliveryDao.selectresumebycompany(id);
        for (Object o:result){
            Object[] rowarray=(Object[])o;
            User user=new User();
            System.out.println("对象"+rowarray.toString());
        }
    }
}
