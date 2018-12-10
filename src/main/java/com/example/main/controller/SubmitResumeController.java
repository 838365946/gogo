package com.example.main.controller;

import com.example.main.model.Company;
import com.example.main.model.Delivery;
import com.example.main.model.Message;
import com.example.main.model.User;
import com.example.main.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Administrator on 2018/12/10.
 */
@RestController
public class SubmitResumeController {
@Autowired
    private DeliveryService deliveryService;
@RequestMapping("submitresume")
@ResponseBody
public Message Submit(User user, Company company){
    Message message=new Message();
    Date date=new Date("yyyy-mm-dd");
    System.out.println(date);
    Delivery delivery =new Delivery();
    delivery.setUser(user);
    delivery.setCompany(company);
    delivery.setD_date(String.valueOf(date));
Delivery delivery1=deliveryService.SubmitRuseme(delivery);
if (delivery1!=null){
    message.setB(true);
    message.setDes("投递成功");
}else {
    message.setB(false);
    message.setDes("投递失败");
}
return message;
}

}
