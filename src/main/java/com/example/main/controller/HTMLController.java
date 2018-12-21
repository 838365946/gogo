package com.example.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/12/21.
 */
@Controller
public class HTMLController {
    @RequestMapping("listiframe")
    public String FirstIframe(){
        System.out.println("返回");
        return "nav";
    }
}
