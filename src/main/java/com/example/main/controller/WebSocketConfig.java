package com.example.main.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * Created by Administrator on 2018/12/13.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {
  @Bean
    public ServerEndpointExporter serverEndpointExporter(){
      return new ServerEndpointExporter();
  }
}
