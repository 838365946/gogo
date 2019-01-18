package com.example.main.controller;

import com.example.main.model.Company;
import com.example.main.model.Message;
import com.example.main.model.Position;
import com.example.main.service.FindPositionService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/12/6.
 */
@RestController
public class ShowMessController {
@Autowired
    private FindPositionService fp;
private CompanyIO companyIO=new CompanyIO();
    @RequestMapping("/showmess")
    public Message ShowMess(@Param("page")Integer page){
    System.out.println("当前页码" +page);
    Message message=new Message();
    PageRequest pageRequest=PageRequest.of(page,10);
    List<Position> positions=  fp.ShowMess(pageRequest);
        Set<Company> companies=new HashSet<Company>();
        for(int i=0;i<positions.size();i++){
            companies.add(positions.get(i).getCompany());
            positions.get(i).setP_des(String.valueOf(companyIO.ReadDes(positions.get(i).getP_des())));
        }
        System.out.println(companies.toString());
        for(Company company:companies){
            company.setC_des(String.valueOf(companyIO.ReadDes(company.getC_des())));
        }
        message.setB(true);
        message.setDes("获取职位成功");
        message.setData(positions);
    return message;
}
@RequestMapping("/querybyinput")
public Message QueryByInput(@Param("page")Integer page,@Param("input") String input){
    System.out.println("当前页码" +page);
    System.out.println(input);
    Message message=new Message();
    PageRequest pageRequest=PageRequest.of(page,10);
    Page<Position> positionspage=  fp.QueryByInput(input,pageRequest);
    List<Position> positions = new ArrayList<Position>();
    CompanyIO companyIO=new CompanyIO();
    if(positionspage.getContent()!=null){
        positions=positionspage.getContent();
        Set<Company> companies=new HashSet<Company>();
        for(int i=0;i<positions.size();i++){
            companies.add(positions.get(i).getCompany());
            positions.get(i).setP_des(String.valueOf(companyIO.ReadDes(positions.get(i).getP_des())));
        }
        System.out.println(companies.toString());
        for(Company company:companies){
            company.setC_des(String.valueOf(companyIO.ReadDes(company.getC_des())));
        }
    }
    message.setB(true);
    message.setDes("获取职位成功");
    message.setData(positions);
    return message;

}
}
