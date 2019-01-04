package com.example.main.controller;

import com.example.main.model.Company;
import com.example.main.model.Delivery;
import com.example.main.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2019/1/4.
 */
@RestController
public class DeliveryController {

     @Autowired
     private DeliveryService deliveryService;

    @RequestMapping("/resume")
    public List<Delivery> ddd(HttpServletRequest request){
        Company company= (Company) request.getSession().getAttribute("company");
     return deliveryService.selectByCompany(company.getC_id());

    }
}
