package com.example.main.controller;


import com.example.main.model.Company;
import com.example.main.model.Message;
import com.example.main.model.Position;
import com.example.main.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

@RequestMapping("/getpositionbycompany")
    @ResponseBody
    public Message QueryByCompany(Company company){
    Message message=new Message();
    List<Position> positions=positionService.QueryByCompany(company.getC_id());
    if(positions.size()>0){
        message.setB(true);
        message.setDes("获取职位成功");
        message.setData(positions);
    }else {
        message.setB(false);
        message.setDes("更多职位敬请期待");
    }
    return message;
}
}
