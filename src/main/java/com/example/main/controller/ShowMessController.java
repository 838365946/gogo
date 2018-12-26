package com.example.main.controller;

import com.example.main.model.Message;
import com.example.main.model.Position;
import com.example.main.service.FindPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */
@RestController
public class ShowMessController {
@Autowired
    private FindPositionService fp;
@RequestMapping("/showmess")
    public Message ShowMess(Integer page){
    Message message=new Message();
    PageRequest pageRequest=PageRequest.of(page,5);
    Page<Position> positionspage=  fp.ShowMess(pageRequest);
    List<Position> positions = null;
    if(positionspage.getContent()!=null){
        positions=positionspage.getContent();
        message.setB(true);
        message.setDes("获取职位成功");
        message.setData(positions);
    }
    return message;
}

@RequestMapping("/querybyinput")
public Message QueryByInput(int page,String input){
        Message message=new Message();
        PageRequest pageRequest=PageRequest.of(page,5);
        Page<Position> positionpage=fp.QueryByInput(input,pageRequest);
    System.out.println(positionpage.getContent());
        List<Position> positions=null;
        if (positionpage.getContent()!=null){
            positions=positionpage.getContent();
            message.setB(true);
            message.setDes("搜索职位成功");
            message.setData(positions);
        }
        return message;

}
}
