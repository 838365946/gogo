package com.example.main.controller;

import com.example.main.model.Company;
import com.example.main.model.Message;
import com.example.main.service.CompanyService;
import com.example.main.util.CompanyIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyIO companyIO;

    private Message message =new Message();

    @RequestMapping(value = "companyRegistered",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Message companyRegistered(@Param("files")MultipartFile[] files, Company company){

        String des=company.getC_des();
        company.setC_des("上传中");
        Company company1=companyService.registered(company);
        String imgpath= companyIO.UploadImg(files,company1.getC_id());

        try {
            String despath=companyIO.WriteDes(des,company1.getC_id());
            company1.setC_des(despath);
            company1.setC_img(imgpath);
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

}


