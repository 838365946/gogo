package com.example.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/12/13.
 */
@Slf4j
@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {
    //记录在线用户
    private static int onlinecount=0;
    //存放客户端对应的websocket
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<String, WebSocketServer>(); //与客户端的连接session，用他主动给客户端发消息
    private Session session;
    private String username;

    //连接成功之后的方法
    @OnOpen
    public void onOpen(@PathParam("username") String param, Session session){

        this.username=param;
        this.session=session;
        webSocketSet.put(param,this);
        System.out.println(username);
        System.out.println(webSocketSet.get(param)+"这个人");
        addonlinecount();
        System.out.println("有新连接加入！在线人数为"+getOnlinecount());
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            System.out.println("websocket出问题了");
        }
    }
    //连接关闭的方法
    @OnClose
    public void onClose(){
        webSocketSet.remove(username);
        subonlinecount();
        System.out.println("有一个连接关闭，当前还有"+getOnlinecount());
    }
//接收客户端的消息
    @OnMessage
    public void onMessage(@Param("message") String message, Session session) {
        System.out.println(message);
        String tousername=message.split("[|]")[1];
        String mess=message.split("[|]")[0];
        System.out.println("客户端："+mess);
        System.out.println("发给"+tousername);
        String sf=new SimpleDateFormat().format(new Date());
        if (webSocketSet.get(tousername)!=null){
            try {
                webSocketSet.get(tousername).sendMessage(sf+"'\n'"+username+":'\t'"+mess);
                webSocketSet.get(username).sendMessage(sf+"\n"+username+":\t"+mess);
            } catch (IOException e) {
                //    for (WebSocketServer item:webSocketServers){
//        try {
//            item.sendMessage(message);
//        } catch (IOException e) {
//            System.out.println("服务端接受消息出问题了");
//        }
//    }
            }
        }else{
            try {
                sendMessage("该用户不在线");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //发生错误调用的方法
@OnError
public void onError(Session session,Throwable error){
    System.out.println("出错了");
error.printStackTrace();
    }

    //群发消息
public static void sendInfo(String message){
    System.out.println(message);
   for (String str:webSocketSet.keySet()){
       try {
           webSocketSet.get(str).sendMessage(message);
       } catch (IOException e) {
       continue;
       }
   }

}


    public static synchronized void addonlinecount(){
        WebSocketServer.onlinecount++;
    }
    public static synchronized int getOnlinecount(){
        return onlinecount;
    }
    public static synchronized void subonlinecount(){
        WebSocketServer.onlinecount--;
    }
    public void sendMessage(String message)throws IOException{
        this.session.getBasicRemote().sendText(message);
    }
}
