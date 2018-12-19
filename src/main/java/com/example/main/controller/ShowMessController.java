package com.example.main.controller;

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
    public List<Position> ShowMess(int page){
    PageRequest pageRequest=PageRequest.of(page,5);
    Page<Position> positionspage=  fp.ShowMess(pageRequest);
    List<Position> positions = null;
    if(positionspage.getContent()!=null){
        positions=positionspage.getContent();

    }
    return positions;
}

@RequestMapping("/querybyinput")
public List<Position> QueryByInput(int page,String input){
        PageRequest pageRequest=PageRequest.of(page,5);
        Page<Position> positionpage=fp.QueryByInput(input,pageRequest);
    System.out.println(positionpage.getContent());
        List<Position> positions=null;
        if (positionpage.getContent()!=null){
            positions=positionpage.getContent();
        }
        return positions;

}
}
