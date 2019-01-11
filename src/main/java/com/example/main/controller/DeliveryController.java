package com.example.main.controller;

import com.example.main.model.Delivery;
import com.example.main.model.Message;
import com.example.main.model.Position;
import com.example.main.model.User;
import com.example.main.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
public Message CheckDelivery(Delivery delivery,@Param("state")String state){
    System.out.println(state);
Message message=new Message();
int i=deliveryService.Updatestate(state,delivery);
if (i>0){
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
    public Message GetDeliveryState(String state,User user){
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
