package com.example.main.controller;

import com.example.main.model.*;
import com.example.main.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2019/1/4.
 */
@RestController
public class DeliveryController {

     @Autowired
     private DeliveryService deliveryService;
@RequestMapping("/checkpost")
@ResponseBody
public Message CheckPost(User user, Position position){

    List<Delivery> deliveries=deliveryService.selectByPid(position.getP_id());
    Message message=new Message();
    for(Delivery d:deliveries){
            if(d.getUser().getId()==user.getId()){
                message.setB(true);
                message.setDes("已经投递过");
            }
    }
    return message;
}
@RequestMapping("/updatestate")
@ResponseBody
public Message CheckDelivery(Delivery delivery, @Param("state")String state, User user, HttpServletRequest request,Position position,String mess){
    PhoneMessage phoneMessage=new PhoneMessage();
    System.out.println(state+user.getId());
Message message=new Message();
int i=deliveryService.Updatestate(state,delivery);
Company company= (Company) request.getSession().getAttribute("company");
if (i>0){
    if(state.equals("邀请面试")){

        phoneMessage.sendm(user.getPhone_number());
        WebSocketServer.sendtoone(user.getId(),company.getC_name(),company.getC_name()+mess);
    }else if(state.equals("不合适")){
        WebSocketServer.sendtoone(user.getId(),company.getC_name(),company.getC_name()+"觉得您不合适"+position.getP_posi_name()+"岗位");
    }else if(state.equals("被查看")){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(position.toString());
        WebSocketServer.sendtoone(user.getId(),company.getC_name(),company.getC_name()+"查看了您投递的"+position.getP_posi_name()+"岗位的简历");
    }
    message.setB(true);
    message.setDes("成功");
}else {
    message.setB(false);
    message.setDes("失败");
}
return message;
}
@RequestMapping("/getdeliverystate")
    @ResponseBody
    public Message GetDeliveryState(String state, User user, Company company){

Message message=new Message();
List<Delivery> deliveries=deliveryService.SelectByState(state,user.getId());

if(deliveries.size()>0){
message.setB(true);
message.setDes("成功");
message.setData(deliveries);

}else {
    message.setB(false);
    message.setDes("失败");
}
return message;
}
}
