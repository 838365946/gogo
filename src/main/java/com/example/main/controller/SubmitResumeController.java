package com.example.main.controller;

import com.example.main.model.Delivery;
import com.example.main.model.Message;
import com.example.main.model.Position;
import com.example.main.model.User;
import com.example.main.service.CompanyService;
import com.example.main.service.DeliveryService;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/10.
 */
@RestController
public class SubmitResumeController {
    @Autowired
    private UserService userService;
@Autowired
    private CompanyService companyService;
@Autowired
    private DeliveryService deliveryService;


@RequestMapping("/postresume")
@ResponseBody
public Message Submit(User user, Position position){
    Delivery delivery=new Delivery();
    delivery.setUser(user);
    delivery.setPosition(position);
    Message message=new Message();
    Date date=new Date();
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    String date1=simpleDateFormat.format(date);
    delivery.setD_date(date1);
    Delivery d=deliveryService.SubmitRuseme(delivery);
    if (d!=null){
        message.setB(true);
        message.setDes("投递成功");
    }else {
        message.setB(false);
        message.setDes("投递失败，请重试");
    }
return message;
}

}
