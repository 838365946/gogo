package com.example.main.controller;

import com.example.main.model.Company;
import com.example.main.model.Message;
import com.example.main.service.CompanyService;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyIO companyIO;

    private Message message =new Message();

    @RequestMapping(value = "/works_home/companyRegistered",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Message companyRegistered(@Param("files")MultipartFile[] files,@Param("logo") MultipartFile logo, Company company){
        System.out.println("进入companycontroller");
        String des=company.getC_des();
        company.setC_des("上传中");
        Company company1=companyService.registered(company);
        String imgpath= companyIO.UploadImg(files,company1.getC_id());
        String logopath=companyIO.LogoUpload(logo,company1.getC_id());
        try {
            String despath=companyIO.WriteDes(des,company1.getC_id());
            company1.setC_des(despath);
            company1.setC_img(imgpath);
            company1.setLogopath(logopath);
            companyService.registered(company1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            companyIO.WriteDes(des,company1.getC_id());
        } catch (IOException e) {
            e.printStackTrace();
        }
        message.setB(true);
        message.setDes("注册成功，请等待审核");
        return message;
        }
@RequestMapping(value = "/pushViedoListToWeb",method = RequestMethod.POST,consumes = "application/json")
public @ResponseBody Map<String,Object> pushViedoListToWeb(@RequestBody Map<String,Object> param){

    Map<String,Object> result =new HashMap<String,Object>();

  WebSocketServer.sendInfo("新客户呼入"+param);
    result.put("operationResult",true);
    return result;
}

    @RequestMapping("/Audit_company")
    public Message audit(Company company,String str){

        Message message=new Message();
        company.setC_check_status(str);
        Company company1=companyService.registered(company);
        if (company1.getC_check_status().equals("已通过")){
            message.setB(true);
            message.setDes("审核通过");
        }else {
            message.setB(false);
            message.setDes("审核未通过");
        }
        return message;
    }

    @RequestMapping("/Audit_record")
    public List<Company> record(Company company,int page){
        PageRequest pageRequest=PageRequest.of(page,10);
        Page<Company> companies=companyService.findall(pageRequest);
        List<Company> companies1=null;
        if (companies.getContent()!=null){
            companies1=companies.getContent();
        }
        return companies1;
    }



}




