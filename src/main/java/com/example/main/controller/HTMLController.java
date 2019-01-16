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

return (Company) request.getSession().getAttribute("company");
    }
    //修改公司信息页面的ajax请求
    @RequestMapping("/dx")
    @ResponseBody
    public Company Loa(HttpServletRequest request){


        return (Company)request.getSession().getAttribute("company");
    }
    //修改公司信息页面的ajax请求
    @RequestMapping("/look")
    @ResponseBody
    public Company Loaa(HttpServletRequest request){


        return (Company)request.getSession().getAttribute("company");
    }

}

