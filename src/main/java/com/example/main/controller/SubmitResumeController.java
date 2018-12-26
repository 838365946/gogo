package com.example.main.controller;

import com.example.main.model.Company;
import com.example.main.model.Delivery;
import com.example.main.model.Message;
import com.example.main.model.User;
import com.example.main.service.CompanyService;
import com.example.main.service.DeliveryService;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


@RequestMapping("/submitresume")
@ResponseBody
public Message Submit(HttpServletRequest request, Company company){
    User user= (User) request.getSession().getAttribute("user");
    Message message=new Message();
    Date date=new Date();
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    String date1=simpleDateFormat.format(date);

    System.out.println(date);
    Delivery delivery =new Delivery();
    delivery.setD_date(date1);
    delivery.setCompany(company);
    delivery.setUser(user);
Delivery delivery1=deliveryService.SubmitRuseme(delivery);
    List<Delivery> deliveryList=new ArrayList<Delivery>();
    deliveryList.add(delivery1);
    user.setDeliveries(deliveryList);
company.setDeliveries(deliveryList);
userService.save(user);
companyService.addcompany(company);
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
