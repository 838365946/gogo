package com.example.main.controller;

import com.example.main.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/1/4.
 */
@RestController
public class DeliveryController {

     @Autowired
     private DeliveryService deliveryService;


}
