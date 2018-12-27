package com.example.main.controller;

import com.example.main.model.Company;
import com.example.main.model.Message;
import com.example.main.model.Position;
import com.example.main.service.FindPositionService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    PageRequest pageRequest=PageRequest.of(page,10);
    Page<Position> positionspage=  fp.ShowMess(pageRequest);
    List<Position> positions = new ArrayList<Position>();
    List<Company> companies = new ArrayList<Company>();
    CompanyIO companyIO=new CompanyIO();
    int count=0;
    if(positionspage.getContent()!=null){
        positions=positionspage.getContent();
        for(int i=0;i<positions.size();i++){
            if(companies.size()>0){
            for (Company company:companies){
                if (positions.get(i).getCompany()!=company){
                    String des=positions.get(i).getCompany().getC_des();
                    String str= String.valueOf(companyIO.ReadDes(des));
                    positions.get(i).getCompany().setC_des(str);
                    companies.add(positions.get(i).getCompany());
                }
            }}else {
                String des=positions.get(i).getCompany().getC_des();
                String str= String.valueOf(companyIO.ReadDes(des));
                System.out.println(des+"aiyo");
                System.out.println("jap"+str);
                positions.get(i).getCompany().setC_des(str);
                companies.add(positions.get(i).getCompany());
            }
            }
               }
        message.setB(true);
        message.setDes("获取职位成功");
        message.setData(positions);
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
            CompanyIO companyIO=new CompanyIO();
            for (Position p:positions){
                p.getCompany().setC_des(String.valueOf(companyIO.ReadDes(p.getCompany().getC_des())));
                System.out.println(companyIO.ReadDes(p.getCompany().getC_des()));
            }
            message.setData(positions);
        }
        return message;

}
}
