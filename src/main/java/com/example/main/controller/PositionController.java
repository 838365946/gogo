package com.example.main.controller;


import com.example.main.model.Message;
import com.example.main.model.Position;

import com.example.main.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PositionController {
    @Autowired
private PositionService positionService;
@RequestMapping("/addPosition")
@ResponseBody
public Message AddPosition(Position position){
    System.out.println(position.toString()+"444");
Message message=new Message();
if(positionService.addposition(position)!=null){
    message.setB(true);
    message.setDes("true");
}else {
    message.setB(false);
    message.setDes("false");
}
    System.out.println(message.toString());
return message;
}

}
