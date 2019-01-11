package com.example.main.controller;

import com.example.main.model.Company;
import com.example.main.model.Delivery;
import com.example.main.model.Message;
import com.example.main.model.Position;
import com.example.main.service.CompanyService;
import com.example.main.service.DeliveryService;
import com.example.main.service.PositionService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;
@Autowired
    private DeliveryService deliveryService;
@Autowired
private PositionService positionService;
    private CompanyIO companyIO = new CompanyIO();

    private Message message = new Message();

    @RequestMapping(value = "/addcompanydata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Message companyRegistered(@Param("files") MultipartFile[] files, @Param("logo") MultipartFile logo, Company company,HttpServletRequest request) {
        String des = company.getC_des();
        company.setC_des("上传中");
        Company company1 = companyService.registered(company);
        String imgpath = companyIO.UploadImg(files, company1.getC_id());
        String logopath = companyIO.LogoUpload(logo, company1.getC_id());
        Company c=null;
        try {
            String despath = companyIO.WriteDes(des, company1.getC_id());
            company1.setC_des(despath);
            company1.setC_img(imgpath);
            company1.setLogopath(logopath);
            company1.setC_check_status("未审核");
          c=   companyService.registered(company1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            companyIO.WriteDes(des, company1.getC_id());
        } catch (IOException e) {
            e.printStackTrace();
        }
       if (c!=null){
           message.setB(true);
           message.setDes("完善资料成功，请等待审核");
           c.setC_des(String.valueOf(companyIO.ReadDes(c.getC_des())));
           return message;
       }else {
           message.setB(false);
           message.setDes("完善资料失败，请等待审核");
       }
       return message;
    }

    @RequestMapping(value = "/updatecompanydata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Message Updatecompanydata(@Param("files") MultipartFile[] files, @Param("logo") MultipartFile logo, Company company,HttpServletRequest request) throws IOException {
        String des=company.getC_des();
        company.setC_des("修改中");
        Company company1= (Company) request.getSession().getAttribute("company");
        String imgpath,despath,logopath;
        if (files!=null){
            String path=System.getProperty("user.dir")+"/src/main/resources/static/img/" + company1.getC_id();
            companyIO.delFolder(path);
            imgpath = companyIO.UploadImg(files, company1.getC_id());
            company1.setC_img(imgpath);
        }
        try {
            despath=companyIO.WriteDes(des,company1.getC_id());
            company1.setC_des(despath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (logo!=null){
            String path=System.getProperty("user.dir")+"/src/main/resources/static/logo/" + company1.getC_id();
            companyIO.delFolder(path);
            logopath=companyIO.LogoUpload(logo,company1.getC_id());
            company1.setLogopath(logopath);
        }

        company1.setC_addr(company.getC_addr());
        company1.setC_name(company.getC_name());
        company1.setC_welfare(company.getC_welfare());
        company1.setC_industry(company.getC_industry());
        company1.setC_scale(company.getC_scale());
        company1.setC_check_status("未审核");
        Company company2=companyService.addcompany(company1);
        if (company2==null){

        message.setB(false);
        message.setDes("修改失败");

        }else {
            company2.setC_des(des);
            request.getSession().removeAttribute("company");
            request.getSession().setAttribute("company",company2);
            message.setB(true);
            message.setDes("修改成功");
            message.setData(company2);
        }
        return message;
    }

    @RequestMapping(value = "/pushViedoListToWeb", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    Map<String, Object> pushViedoListToWeb(@RequestBody Map<String, Object> param) {

        Map<String, Object> result = new HashMap<String, Object>();

        WebSocketServer.sendInfo("新客户呼入" + param);
        result.put("operationResult", true);
        return result;
    }

    @RequestMapping("/Audit_company")
    @ResponseBody
    public Message audit() {
        Message message = new Message();
        List<Company> companies=companyService.CheckCompany("未审核");
        if (companies.size()>0){
            message.setB(true);
            message.setDes("获取未审核公司成功");
            for(Company c:companies){
                c.setC_des(String.valueOf(companyIO.ReadDes(c.getC_des())));
            }
            message.setData(companies);
        }else {
            message.setB(false);
            message.setDes("获取未审核公司失败");
        }
        return message;
    }



    @RequestMapping("/showcompmess")
    @ResponseBody
    public Message show(int page) {
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Company> companies = companyService.findall(pageRequest);
        List<Company> companies1 = null;
        if (companies.getContent() != null) {
            companies1 = companies.getContent();

        }
        if(companies1.size()>0){
            message.setB(true);
            message.setDes("获取成功l");
            for (Company c:companies1){
                c.setC_des(String.valueOf(companyIO.ReadDes(c.getC_des())));
            }
            message.setData(companies1);
        }else {
            message.setB(false);
            message.setDes("获取失败");
        }
        return message;
    }

    @RequestMapping("/clogin")
    public ModelAndView CompanyLogin(Company company, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Company company1 = companyService.CLogin(company);
      if(company1!=null){
          company1.setC_des(String.valueOf(companyIO.ReadDes(company1.getC_des())));
          request.getSession().setAttribute("company",company1);
          modelAndView.addObject("name",company1.getC_name());
          modelAndView.setViewName("main");
      }else {
          modelAndView.setViewName("error");
      }
        return modelAndView;
    }
    @RequestMapping("/creg")
    public ModelAndView Reg(Company company){
        ModelAndView modelAndView=new ModelAndView();
        System.out.println(company.toString());
        Company company1=companyService.addcompany(company);
        if (company1!=null){
            modelAndView.setViewName("login");
        }else {
            modelAndView.setViewName("error");
        }return modelAndView;
    }

    @RequestMapping("/readresume")
    @ResponseBody
    public Message test(HttpServletRequest request){
        Company company= (Company) request.getSession().getAttribute("company");
        List<Position> positions=positionService.QueryByCompany(company.getC_id());
        List<Delivery>deliveries=new ArrayList<Delivery>();
        for(Position p:positions){
           List<Delivery> deliveryList= deliveryService.selectByPid(p.getP_id());
            for(Delivery d:deliveryList){
                deliveries.add(d);
            }
        }
        if(deliveries.size()>0){
            message.setB(true);
            message.setDes("获取成功");
            message.setData(deliveries);
        }else {
            message.setB(false);
            message.setDes("获取失败");
        }

        return message;
    }
    @RequestMapping("/passcheck")
    @ResponseBody
    public Message PassCheck(Company company){
    int i=companyService.PassCheck(company,"审核通过");
    if (i>0){
        message.setB(true);
        message.setDes("成功通过审核");
    }else {
        message.setB(false);
        message.setDes("通过审核失败");
    }
    return  message;
    }
    @RequestMapping("/banned")
    @ResponseBody
    public Message PassChecka(Company company){
        int i=companyService.PassChecka(company,"被封禁");
        if (i>0){
            message.setB(true);
            message.setDes("被封禁");
        }else {
            message.setB(false);
            message.setDes("封禁失败");
        }
        return  message;
    }


    @RequestMapping("/losecheck")
    @ResponseBody
    public Message LoseCheck(Company company){
        int i=companyService.PassCheck(company,"审核失败");
        if (i>0){
            message.setB(true);
            message.setDes("审核失败了，返回成功");
        }else {
            message.setB(false);
            message.setDes("出现未知错误，请联系管理员");
        }
        return  message;
    }
@RequestMapping("/checkcname")
    @ResponseBody
    public  Message CheckCname(Company company){
    List<Company> companies=companyService.CheckCname(company.getC_name());
    if (companies.size()>0){
        message.setB(false);
        message.setDes("公司名已存在");
    }else {
        message.setB(true);
        message.setDes("可以使用");
    }
    return message;
}

    @RequestMapping("/checkcusername")
    @ResponseBody
    public  Message CheckCusername(Company company){
        List<Company> companies=companyService.CheckCname(company.getC_username());
        if (companies.size()>0){
            message.setB(false);
            message.setDes("账户已存在");
        }else {
            message.setB(true);
            message.setDes("可以使用");
        }
        return message;
    }

    @RequestMapping("/getcompbystate")
    @ResponseBody
    public  Message Getcompbystate(Company company){
        List<Company> companies=companyService.QueryBystate(company.getC_check_status());
        if (companies.size()<0){
            message.setB(false);
            message.setDes("获取失败");
        }else {
            message.setB(true);
            message.setDes("获取成功");
            message.setData(companies);
        }
        return message;
    }
}







