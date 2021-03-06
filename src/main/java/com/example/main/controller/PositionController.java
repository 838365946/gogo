package com.example.main.controller;


import com.example.main.model.Company;
import com.example.main.model.Message;
import com.example.main.model.Position;
import com.example.main.service.CompanyService;
import com.example.main.service.PositionService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PositionController {
    @Autowired
private PositionService positionService;
    @Autowired
    private CompanyService companyService;
    private CompanyIO companyIO=new CompanyIO();

@RequestMapping("/addPosition")
@ResponseBody
public ModelAndView AddPosition(Position position, HttpServletRequest request){
    Message message=new Message();
    ModelAndView modelAndView=new ModelAndView();
Company company= (Company) request.getSession().getAttribute("company");
   if(company.getC_check_status().equals("正常")){
       company.setC_des(String.valueOf(companyService.findByid(company.getC_id()).getC_des()));
       position.setCompany(company);

       if(positionService.addposition(position)!=null){
           modelAndView.setViewName("home.html");
       }else {
           message.setB(false);
           message.setDes("false");
           modelAndView.addObject(message);
       }
   }else if (company.getC_check_status().equals("未审核")){
       message.setB(false);
       message.setDes("公司的资料还没通过审核");
        modelAndView.addObject(message);
   }else if(company.getC_check_status().equals("封禁")){
        message.setB(false);
        message.setDes("公司已被管理员封禁");
        modelAndView.addObject(message);
   }

return modelAndView;
}

@RequestMapping("/getpositionbycompany")
    @ResponseBody
    public Message QueryByCompany(Company company){
    Message message=new Message();
    List<Position> positions=positionService.QueryByCompany(company.getC_id());
    Set<Company> companies=new HashSet<Company>();
    for(int i=0;i<positions.size();i++){
        companies.add(positions.get(i).getCompany());
        positions.get(i).setP_des(String.valueOf(companyIO.ReadDes(positions.get(i).getP_des())));
    }
    System.out.println(companies.toString());
    for(Company c:companies){
        c.setC_des(String.valueOf(companyIO.ReadDes(c.getC_des())));
    }
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
@RequestMapping("/getbyid")
    @ResponseBody
    public Message QueryById(Position position)throws NullPointerException{

        Message message=new Message();
        Position position1=positionService.QueryById(position.getP_id());
    System.out.println(position1.toString());
    position1.setP_des(String.valueOf(companyIO.ReadDes(position1.getP_des())));
        if(position1!=null){
            message.setB(true);
            message.setDes("成功");
            message.setData(position1);
        }else {
            message.setB(false);
        }
        return message;
}
@RequestMapping("/delposition")
    @ResponseBody
public Message DelPosition(Position position){
    Message message=new Message();
    int i=positionService.DelPOsition(position.getP_id());
    if(i>0){
        message.setB(true);
        message.setDes("删除成功");
    }else {
        message.setB(false);
    }
    return message;
}
}
