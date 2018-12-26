package com.example.main.controller;

import com.example.main.model.Company;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/12/21.
 */
@Controller
public class HTMLController {

    @RequestMapping("/load")
    @ResponseBody
    public Company Load(HttpServletRequest request){
        System.out.println(request.getSession().getAttribute("company"));
return (Company) request.getSession().getAttribute("company");
    }


}

