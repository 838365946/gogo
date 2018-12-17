package com.example.main.controller;


import com.example.main.model.Message;
import com.example.main.model.Position;

import com.example.main.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {
    @Autowired
private PositionService positionService;
@RequestMapping("works_home/addPosition")
public Message AddPosition(Position position){
Message message=new Message();
if(positionService.addPosition(position)!=null){
    message.setB(true);
    message.setDes("发布职位成功");

}else {
    message.setB(false);
    message.setDes("发布职位失败");
}
return message;
}

}
