package com.example.main.service.impl;

import com.example.main.dao.DeliveryDao;
import com.example.main.model.Delivery;
import com.example.main.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Delivery> selectByPid(int id) {
        return deliveryDao.QueryByPID(id);
    }

    @Override
    public int Updatestate(String str, Delivery delivery) {
        int i=0;
        if (str.equals("被查看")){
            i=deliveryDao.Beviewed("是",delivery.getD_id());
        }else if (str.equals("不合适")){
            i=deliveryDao.Pass("是",delivery.getD_id());
        }else if (str.equals("邀请面试")){
            i=deliveryDao.Offer("是",delivery.getD_id());
        }
        return i;
    }

    @Override
    public List<Delivery> SelectByState(String str, int uid) {
        int i=0;
        List<Delivery> deliveries=new ArrayList<Delivery>();
        if (str.equals("被查看")){
            deliveries=deliveryDao.QueryBeviewd("是",uid);
        }else if (str.equals("不合适")){
            deliveries=deliveryDao.QueryPass("是",uid);
        }else if (str.equals("邀请面试")){
          deliveries=deliveryDao.QueryOffer("是",uid);
        }
        return deliveries;
    }
}
